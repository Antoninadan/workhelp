package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureSimpleApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(CompletableFutureSimpleApp.class);

        CompletableFuture<String> future = CompletableFuture
                .supplyAsync(() -> "1");
        // future исполнится в ForkJoinPool.commonPool(), т.к. мы не указывали ему Executor.
        System.out.println(future.get());

        CompletableFuture<String> future2 = CompletableFuture
                .supplyAsync(() -> "2", Executors.newCachedThreadPool());
        System.out.println(future2.get());

        CompletableFuture<Void> future3 = CompletableFuture
                .runAsync(() -> logger.info("3"));

        CompletableFuture<Void> future4 = CompletableFuture
                .runAsync(() -> logger.info("4"), Executors.newCachedThreadPool());


//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Hi");
//        try {
//            Thread.sleep(500);         // имитируем долгое выполнение
//        } catch (InterruptedException e) {}
//


    }
}
