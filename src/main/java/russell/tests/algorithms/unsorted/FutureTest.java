package russell.tests.algorithms.unsorted;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    final static ExecutorService executor = Executors.newFixedThreadPool(4);
    final static CompletionService<List<Integer>> completionService = new ExecutorCompletionService<>(executor);

    public static void main(String[] args) {

        // 4 tasks
        for (int i = 0; i < 4; i++) {
            completionService.submit(() -> {
                final List<Integer> result = new ArrayList<>();
                final Random random = new Random();
                while (result.size() < 10) {
                    final Integer wait = random.nextInt(1000);
                    result.add(wait);
                    Thread.sleep(wait);
                }
                return result;
            });
        }

        (new Thread() {
            @Override
            public void run() {
                try {
                    int received = 0;
                    boolean errors = false;

                    while (received < 4 && !errors) {
                        System.out.println("waiting for future to finish");
                        final Future<List<Integer>> resultFuture = completionService.take(); // blocks if none available

                        try {
                            final List<Integer> result = resultFuture.get();
                            System.out.println(result);
                            received++;
                        } catch (Exception e) {
                            errors = true;
                        }
                    }
                } catch (final InterruptedException e) {
                    System.out.println(e);
                } finally {

                }
            }
        }).start();

        executor.shutdown();
        System.out.println("main done");
    }

}
