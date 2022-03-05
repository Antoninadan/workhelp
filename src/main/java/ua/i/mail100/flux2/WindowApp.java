package ua.i.mail100.flux2;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.w3c.dom.ls.LSOutput;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Arrays;

public class WindowApp {
    public static void main(String[] args) {
//        assertThat(Flux.just(1, 2, 3, 4, 5)
//                .window(2)
//                .concatMap(Flux::collectList)
//                .collectList()
//                .block()).containsExactly(
//                Arrays.asList(1, 2),
//                Arrays.asList(3, 4),
//                Arrays.asList(5));

        List<Integer> data= Arrays.asList(1, 2, 3, 4, 5);

//        List<List<Integer>> l =
                Flux.fromIterable(data)
                .window(2)
                .map(Flux::collectList)
                .subscribe(x->x.subscribe(System.out::println));

        Flux.range(1, 10)
                .window(5)
                .doOnNext(flux -> flux.collectList().subscribe(l -> System.out.println("Received :: " + l)))
                .subscribe();

//        l.forEach(x->System.out.println(x));



//        List<List<Integer>> l = Flux.just(1, 2, 3, 4, 5)
//                .window(2)
//                .concatMap(Flux::collectList)
////                .map(Flux::collectList)
//                .collectList()
//                .block();
//        l.forEach(x->System.out.println(x));
    }
}
