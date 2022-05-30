package org.russell.spring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.task.TaskExecutor;


public class RussellSpringTests {

    static ApplicationContext ctx;
    
    public static void main(String[] args) throws Exception {
        
        ctx = new AnnotationConfigApplicationContext(RussellConfig.class);
        
        printMessagesCompletionService();
        
        //printMessagesAsync();
    }
    
    public static void printMessagesCompletionService() throws Exception {
        final Executor executor = ctx.getBean(Executor.class);
        final CompletionService<Boolean> compService = new ExecutorCompletionService<>(executor);
        
        // Futures for all submitted Callables that have not yet been checked
        final Set<Future<Boolean>> futures = new HashSet<>();
         
        for (Callable<Boolean> c: getCallables()) {
            futures.add(compService.submit(c));
        }
        
        Future<Boolean> completedFuture;
        Boolean result;
        
        while (futures.size() > 0) {
            // block until a callable completes
            completedFuture = compService.take();
            futures.remove(completedFuture);
         
            // get the result, if the Callable was able to create it
            try {
                result = completedFuture.get();
            } catch (final ExecutionException e) {
                System.out.println("Task failed");
                cancelTasks(futures);
                break;
            }
            
            // Do something with the result
            System.out.println(result);
        }
        
        
        System.out.println("DONE PRINTING MESSAGES");
    }
    
    private static void cancelTasks(final Set<Future<Boolean>> futures) {
        for (final Future<Boolean> f: futures) {
            // pass true if you wish to cancel in-progress Callables as well as
            // pending Callables
            f.cancel(true);
        }
    }
    
    private static List<Callable<Boolean>> getCallables() {
        final List<Callable<Boolean>> callables = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            callables.add(new MessagePrinterTask("Message" + i));
        }
        return callables;
    }
    
    /**
     * {@link MessagePrinter#printMessage(String)} is annotated with Async
     * Needs AsyncEnabled on Configuration
     */
    public static void printMessagesAsync() {
        final MessagePrinter printer = ctx.getBean(MessagePrinter.class);
        int total = 25;
        
        for(int i = 0; i < total; i++) {
            printer.printMessage("Message" + i);
        }
        
        System.out.println("DONE PRINTING MESSAGES");
    }
    
    public static void printMessagesTask() {
        final TaskExecutor taskExecutor = ctx.getBean(TaskExecutor.class);
        for(int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
        System.out.println("DONE PRINTING MESSAGES");
    }
    
    public static void protoAndSingle() {
                for (int i = 0; i < 15; i++) {
            final SingletonBean s = ctx.getBean(SingletonBean.class);
            final PrototypeBean p = s.getPrototypeBean();

            System.out.format("singleton bean %d: %s%n", i, s);
            System.out.format("prototype bean %d: %s%n", i, p);
            System.out.println(">>>>>>>");
        }
    }
    
    
    static class MessagePrinterTask implements Runnable, Callable<Boolean> {
        private String message;
        public MessagePrinterTask(String message) {
            this.message = message;
        }
        public void run() {
            System.out.println(message);
        }
        @Override
        public Boolean call() throws Exception {
            System.out.println(message);
            return Boolean.TRUE;
        }
    }
}
