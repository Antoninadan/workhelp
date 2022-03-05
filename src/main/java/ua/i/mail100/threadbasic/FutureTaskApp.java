package ua.i.mail100.threadbasic;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskApp {
    public static void main(String []args) throws Exception {
        Callable task = () -> {
            return "Hello, World!";
        };
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        System.out.println(future.get());  // выполнение cинхронное
    }
}
