package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CompletableFutureGet2App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//        String result = completableFuture.get();
//        System.out.println(result);
//        Метод get() блокирует поток до тех пор, пока Future не завершится.
//        Таким образом, этот вызов заблокирует поток навсегда, потому что Future
//        никогда не завершается.


//        Чтобы завершить CompletableFuture вручную, можно использовать метод complete()
        CompletableFuture<String> completableFuture2 = new CompletableFuture<String>();
       completableFuture2.complete("Результат Future");
        String result2 = completableFuture2.get();
        System.out.println(result2);




    }
}
