package ua.i.mail100.rx;

import rx.Observable;
import rx.Subscriber;

public class ObservableSubApp {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(
                sub -> {
                    sub.onNext("Hello");
                    sub.onError(new RuntimeException("Error"));
                    sub.onCompleted();
                }
        );
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Done!");
            }

            @Override
            public void onError(Throwable e) {
                System.err.println(e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("event " + s);
            }
        };
        observable.subscribe(subscriber);

// skorocheno:
        Observable.create(
                sub -> {
                    sub.onNext("Hello2");
                    sub.onError(new RuntimeException("Error2"));
                    sub.onCompleted();
                }
        ).subscribe(

                (x) -> System.out.println("event2 " + x), // on next
                System.err::println,
                () -> System.err.println("finish2") // on complete
        );
    }
}
