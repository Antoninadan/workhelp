package ua.i.mail100.rxmap;

import rx.Observable;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*Transforming items emitted by an Observable
        into other Observables is vital part of programming in RX.
        https://medium.com/appunite-edu-collection/rxjava-flatmap-switchmap-and-concatmap-differences-examples-6d1f3ff88ee0
        */

public class SwitchMapApp {
    public static void main(String[] args) {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        final TestScheduler scheduler = new TestScheduler();

        Observable.from(items)
                .switchMap( s -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler);
                })
                .toList()
                .doOnNext(System.out::println)
                .subscribe();

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);

    }

/*
    [fx]
    whenever a new item is emitted by the source Observable,
    it will unsubscribe to and stop mirroring the Observable that was generated
    from the previously-emitted item, and begin only mirroring the current one.

 */
}
