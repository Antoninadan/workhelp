package ua.i.mail100.rx;

import rx.Observable;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ObservableExpApp {
    public static void main(String[] args) {
        Observable.just("1","2");

        Observable<String> oneTwo = Observable.just("1","2");


        Observable.from(new String[]{"a", "b"});

        Observable.from(Collections.emptyList());

        Observable<String> hello = Observable.fromCallable(()->"Hello");

        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.from(future);

        Observable.concat(oneTwo, hello, world, Observable.just("!")).forEach(System.out::print);


    }
}
