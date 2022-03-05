package ua.i.mail100.flux3;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class WindowBufferApp {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1, 10).log();

        flux
                .doOnNext(s -> log.info("pre-window:{}", s))
                .window(3)
                .subscribe(s -> s.log().buffer().subscribe(t -> log.info("post-window:{}", t)));

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");
        flux
                .doOnNext(s -> log.info("pre-buffer:{}", s))
                .buffer(3)
                .subscribe(t -> log.info("post-buffer:{}", t));

    }
}
