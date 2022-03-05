package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

//Более приемлемым способом обработать результат работы CompletableFuture есть callback.
// Если после выполенения задачи мы хотим вывести ее на екран, это будет выглядеть так

public class CompletableFutureGetApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger logger = LoggerFactory.getLogger(CompletableFutureGetApp.class);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hi");

        future.thenAccept(result -> System.out.println(result));

        future.get();

    }
}
