package ua.i.mail100.threadbasic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runnable2App {
    public static void main(String []args){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new EventLoggingTask());
        executorService.shutdown();

    }



    static class EventLoggingTask implements Runnable{
        private Logger logger = LoggerFactory.getLogger(EventLoggingTask.class);

        @Override
        public void run() {
            logger.info("Message");
        }
    }

}
