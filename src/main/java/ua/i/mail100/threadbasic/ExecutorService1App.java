package ua.i.mail100.threadbasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService1App {
    public static void main(String[] args) throws Exception {

        Logger logger = LoggerFactory.getLogger(Runnable2App.EventLoggingTask.class);

        Runnable task = () -> {
            for (int i = 0; i <= 100; i++) {

                logger.info("Message" + i);
            }
        };

        Runnable task2 = () -> {
            logger.info("Message");
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(task);
        executor.submit(task2);
//        executor.shutdown();

    }
}
