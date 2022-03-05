package ua.i.mail100.completablefuture;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

//колбэк на CompletableFuture, используя методы:
//  thenApply()
//  thenAccept()
//  thenRun()

public class CompletableFutureCallbackApp {
    static Logger logger = LoggerFactory.getLogger(CompletableFutureCallbackApp.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        testThenApply();
        System.out.println();

        testManyThenApply();
        System.out.println();

        testThenAccept();
        System.out.println();

        testThenRun();
        System.out.println();

        testThenApply2();
        System.out.println();

        testThenApplyAsync();

    }

    static void testThenApply() throws ExecutionException, InterruptedException {
        // Создаём CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Jhon";
        });

        // Добавляем колбэк к Future, используя thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            return "Привет," + name;
        });

           // Блокировка и получение результата Future
        System.out.println(greetingFuture.get()); // Привет, Rajeev
    }

    static void testManyThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Jhon";
        }).thenApply(name -> {
            return "Привет," + name;
        }).thenApply(greeting -> {
            return greeting + ". Добро пожаловать в блог CalliCoder";
        });

        System.out.println(welcomeText.get());
    }

    static void testThenAccept(){
        CompletableFuture welcomeText = CompletableFuture.supplyAsync(() -> {
            return "St";
        }).thenAccept(text -> {
            System.out.println("Получена информация о  " + text);
        });
    }

    static void testThenRun() {
        CompletableFuture welcomeText = CompletableFuture.supplyAsync(() -> {
            // Выполняем некоторые расчёты
            return 78678;
        }).thenRun(() -> {
            // Расчёты завершены
        });
    }

    static void testThenApply2() throws ExecutionException, InterruptedException {
        CompletableFuture welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            logger.info("запуск");
            return "Некоторый результат";
        }).thenApply(result -> {
    /*
      Выполняется в том же потоке, где и задача supplyAsync()
      или в главном потоке, если задача supplyAsync() завершается сразу(чтобы проверить это удалите sleep())
    */
            logger.info("в callback");
            return "Обработанный результат";
        });
        logger.info("main");
        System.out.println(welcomeText.get());
    }

    static void testThenApplyAsync() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(2);

        CompletableFuture welcomeText = CompletableFuture.supplyAsync(() -> {
            logger.info("запуск");
            return "Асинхронно";
        }).thenApplyAsync(result -> {
            // Выполняется в потоке, полученном от Executor
            logger.info("в callback");
            return "Обработанный результат";
        }, executor);

        logger.info("main");
        System.out.println(welcomeText.get());
    }

}
