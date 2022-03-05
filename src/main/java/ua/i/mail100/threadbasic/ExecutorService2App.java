package ua.i.mail100.threadbasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService2App {
    public static void main(String[] args) throws Exception {

        Logger logger = LoggerFactory.getLogger(Runnable2App.EventLoggingTask.class);

        Callable task = () -> {
            for (int i = 0; i <= 100; i++) {
                logger.info("Message +" + i);
            }
            return 100;
        };

        Callable task2 = () -> {
            for (int i = 0; i <= 100; i++) {
                logger.info("Message2 +" + i);
            }
            return 200;
        };

        ExecutorService executor = Executors.newCachedThreadPool();
//        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(task);
        executor.submit(task2);

        for (int i = 0; i <= 1000; i++) {
            logger.info(String.valueOf(i));
        }
//        executor.shutdown();

    }
}
