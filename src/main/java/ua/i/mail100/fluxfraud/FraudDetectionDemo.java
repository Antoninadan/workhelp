package ua.i.mail100.fluxfraud;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FraudDetectionDemo {

    public static void main(String[] args) {
        A a = new A();
        a.m();
    }

    static class A{
    void m() {
        // creditcard stream 1
        Flux<Integer> transactions1 = Flux.just(100, 101, 102, 100, 105, 102, 104, 102)
                .delayElements(Duration.ofMillis(500));

        // creditcard stream 2
        Flux<Integer> transactions2 = Flux.just(101, 200, 201, 300, 102, 301, 100)
                .delayElements(Duration.ofMillis(600));

        // Flux windowing
        Flux.merge(transactions1, transactions2)
                .window(Duration.ofSeconds(2), Duration.ofMillis(500)) // create a flux of 2 seconds every 500 milliseconds
                .doOnNext(this::fraudDetector)
                .subscribe();
    }


    // business rule for fraud detection
    private void fraudDetector(Flux<Integer> transactions) {
        transactions
                .collectList().map(l -> l.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                .doOnNext(map -> map.entrySet().removeIf(entry -> entry.getValue() < 3))
                .filter(map -> !map.isEmpty())
                .map(Map::keySet)
                .subscribe(s -> System.out.println("Fraud Cards :: " + s));
    }}
}
