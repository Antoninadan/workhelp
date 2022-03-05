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

public class ConcatMap2App {
    public static void main(String[] args) {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        final TestScheduler scheduler1 = new TestScheduler();
        final TestScheduler scheduler2 = new TestScheduler();

        Observable.from(items)
                .flatMap(s -> Observable.just(s + "x")
                        .delay(5, TimeUnit.SECONDS, scheduler1)
                        .doOnNext(str -> System.out.print(scheduler1.now() + " ")))
                .toList()
                .doOnNext(strings -> System.out.println("\nEND:" + scheduler1.now()))
                .subscribe();

        scheduler1.advanceTimeBy(1, TimeUnit.MINUTES);

        System.out.println();

        Observable.from(items)
                .concatMap(s -> Observable.just(s + "x")
                        .delay(5, TimeUnit.SECONDS, scheduler2)
                        .doOnNext(str -> System.out.print(scheduler2.now() + " ")))
                .toList()
                .doOnNext(strings -> System.out.println("\nEND:" + scheduler2.now()))
                .subscribe();

        scheduler2.advanceTimeBy(1, TimeUnit.MINUTES);

    }

/*
    [ax, bx, cx, dx, ex, fx]
    works almost the same as flatMap, but preserves the order of items.
    But concatMap has one big flaw: it waits for each observable to finish all
    the work until next one is processed

 */
}
