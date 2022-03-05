package ua.i.mail100.rx;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ObservableExp2App {
    public static void main(String[] args) throws InterruptedException {
        Observable
                .interval(1
                        , TimeUnit.SECONDS)
                .subscribe(e -> System.out.println("Recieved: " + e));
        Thread.sleep(7000); // щоб головний поток не закрився зразу



//        CountDownLatch externalSignal = new CountDownLatch(5);
//        Subscription subscription = Observable
//                .interval(1, TimeUnit.SECONDS)
//                .subscribe(System.out::print);
//        externalSignal.await(1, TimeUnit.SECONDS);
//        subscription.unsubscribe();

    }
}
