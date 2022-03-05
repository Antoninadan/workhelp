package ua.i.mail100.threadbasic;

import java.util.concurrent.*;

public class CallableApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FactorialTask task = new FactorialTask(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(task);

        executorService.shutdown();
        System.out.println(future.get().intValue());
        }

    static class FactorialTask implements Callable<Integer> {
        int number;

        public FactorialTask(int number) {
            this.number = number;
        }

        public Integer call() throws ExceptionInInitializerError {
            int fact = 1;
            for (int count = number; count > 1; count--) {
                fact = fact * count;
            }
            System.out.println(fact);
            return fact;
        }
    }
}
