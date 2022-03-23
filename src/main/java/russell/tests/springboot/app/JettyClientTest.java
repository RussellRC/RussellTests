package russell.tests.springboot.app;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import java.net.HttpCookie;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class JettyClientTest {

	private static final ExecutorService executor = Executors.newFixedThreadPool(20);
	private final HttpClient httpClient;

	@Inject
	public JettyClientTest(@Nonnull final HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public void run() throws Exception {

		final URI uri = URI.create("https://pkgdentest.medallia.com/oauth/pkgdentest/tokeninfo");
		for (int i = 0; i < 10; i++) {
			final int id = i;
				//URI=https://pkgdentest.medallia.com/oauth/pkgdentest/tokeninfo;
				// Headers={Accept=application/json, Accept-Charset=UTF-8, Accept-Encoding=gzip;q=1.0, compress;q=0.5, Content-Type=application/x-www-form-urlencoded};
				// Body=token=703957704d11ef21dcc5ab7b6d1d358;

				final Request req = httpClient.newRequest(uri)
					.agent("Medallia-CloudInstaller-Builder/0.1alpha")
					.method("POST")
					.header(HttpHeader.ACCEPT, MediaType.APPLICATION_JSON)
					.header(HttpHeader.ACCEPT_CHARSET, "UTF-8")
					.header(HttpHeader.ACCEPT_ENCODING, "gzip;q=1.0, compress;q=0.5")
					.header(HttpHeader.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED)
					.content(new StringContentProvider("token=16c0d3c6bd744718f15dc3d6aa3f6be3"));
				try {
					final ContentResponse response = req.send();
					final StringBuilder sb = new StringBuilder(response.getHeaders().toString());
					sb.append("\n");
					sb.append(response.getContentAsString());
					sb.append("\n");
					sb.append(String.format("=== THREAD %d FINISHED ===%n%n", id));
					synchronized (System.out) {
						System.out.println(sb);
					}
				} catch (final InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				} catch (ExecutionException | TimeoutException e) {
					e.printStackTrace();
				}
			//			Thread.sleep(RandomUtils.nextLong(10, 20));
		}

//		executor.shutdown();
//		executor.awaitTermination(30, TimeUnit.SECONDS);
		httpClient.stop();
	}

}
