package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


public class CompletableFutureResultAsyncApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(CompletableFutureSimpleApp.class);
        // Запуск асинхронной задачи, заданной объектом Supplier
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Результат асинхронной задачи";
            }
        });


        // Блокировка и ожидание завершения Future
        String result = future.get();
        System.out.println(result);

        logger.info("повернулись в основний");
    }


//    Supplier<T> это функциональный интерфейс, представляющий поставщика результатов.
//    У него есть всего один метод get(), в котором можно указать фоновое задание и вернуть результат.
}
