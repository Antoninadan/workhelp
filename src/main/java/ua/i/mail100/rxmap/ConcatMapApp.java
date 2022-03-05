package ua.i.mail100.rxmap;

import rx.Observable;
import rx.schedulers.TestScheduler;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

/*Transforming items emitted by an Observable
        into other Observables is vital part of programming in RX.
        https://medium.com/appunite-edu-collection/rxjava-flatmap-switchmap-and-concatmap-differences-examples-6d1f3ff88ee0
        */

public class ConcatMapApp {
    public static void main(String[] args) {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        final TestScheduler scheduler = new TestScheduler();

        Observable.from(items)
                .concatMap( s -> {
                    final int delay = new Random().nextInt(1);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.MILLISECONDS);//, scheduler);
                })
//                .toList()
                .doOnNext(System.out::println)
                .subscribe();

//        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

/*
    [ax, bx, cx, dx, ex, fx]
    works almost the same as flatMap, but preserves the order of items.
    But concatMap has one big flaw: it waits for each observable to finish all
    the work until next one is processed

 */
}
