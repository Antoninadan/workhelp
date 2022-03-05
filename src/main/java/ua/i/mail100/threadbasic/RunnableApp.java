package ua.i.mail100.threadbasic;

public class RunnableApp {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Hello World");
        };
        new Thread(task).start();

    }

}
