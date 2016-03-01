package russell.tests.spring;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class MessagePrinter {

    @Async
    public Future<Void> printMessage(String msg) {
        System.out.println(msg);
        return new AsyncResult<Void>(null);
    }
}
