package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class CompletableFutureAsyncApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(CompletableFutureSimpleApp.class);
        // Асинхронно запускаем задачу, заданную объектом Runnable
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Имитация длительной работы
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    throw new IllegalStateException(e);
//                }
                System.out.println("Я буду работать в отдельном потоке, а не в главном.");
        logger.info("це виконується в окремому");

            }
        });

        // Блокировка и ожидание завершения Future
        future.get();

        logger.info("повернулись в основний");
    }
}
