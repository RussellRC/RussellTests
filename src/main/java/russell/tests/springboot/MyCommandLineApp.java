package russell.tests.springboot;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.http.HttpClientTransportOverHTTP;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import russell.tests.springboot.app.JettyClientTest;

@Configuration
@SpringBootApplication
public class MyCommandLineApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyCommandLineApp.class, args);
	}

	@Autowired
	private JettyClientTest jettyClientTest;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("yo!");
		jettyClientTest.run();
	}

	@Bean
	public HttpClient httpClient() throws Exception {
		final SslContextFactory sslContextFactory = new SslContextFactory.Client(true);
		final HttpClient httpClient = new HttpClient(new HttpClientTransportOverHTTP(), sslContextFactory);
		httpClient.setFollowRedirects(false);
		httpClient.start();
		return httpClient;
	}
}
