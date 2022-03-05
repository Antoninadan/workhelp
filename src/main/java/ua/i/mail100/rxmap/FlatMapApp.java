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

public class FlatMapApp {
    public static void main(String[] args) throws InterruptedException {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        final TestScheduler scheduler = new TestScheduler();

        Observable.from(items)
                .flatMap( s -> {
                    final int delay = new Random().nextInt(10);
                    return Observable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, scheduler);
                })
                .toList()
                .doOnNext(System.out::println)
                .subscribe();

        scheduler.advanceTimeBy(10, TimeUnit.MINUTES); // Moves the Scheduler's clock forward by a specified amount of time.
    }
    /*
 [cx, ex, ax, fx, bx, dx]
   no order!
   merges the emissions of these Observables, so that they may interleave (чергуватися).

 */

}
