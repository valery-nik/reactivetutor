package ru.oz.tutorials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = MyTestConfig.class)
public class TestFluxApi {

    @Test
    public void simpleFluxApiTest() throws Exception {

        Flux<String> noData = Flux.empty();
        Flux<String> data = Flux.just("Java", "Sample", "Approach", ".com");
        Flux<Integer> intData = Flux.range(1, 100);
        Flux.error(new RuntimeException());

        System.out.println(Thread.currentThread().getName() + ": Begin....");
        Flux<Long> counter = Flux.interval(Duration.ofMillis(100)); // производит тики с интервалом 100мс
        counter.subscribe((tick) -> {
            System.out.printf("[%s]: tick - %d \n", Thread.currentThread().getName(), tick);
        });

        Thread.sleep(1500);
        System.out.printf("[%s]: end. ", Thread.currentThread().getName());

        Flux<Integer> intStream = Flux.just(1, 2, 3, 4, 5);
        intStream
                .map(e -> e * 100)
                .subscribe(e -> System.out.printf("[%s]: %d \r", Thread.currentThread().getName(), e));
        //counter.log();
        Thread.sleep(1500);

    }

    @Test
    public void manySamples() throws Exception {
        System.out.println("=== Empty Mono ===");
        Mono.empty().subscribe(System.out::println);

        System.out.println("=== Mono.just ===");
        Mono.just("JSA")
                .map(item -> "Mono item: " + item)
                .subscribe(System.out::println);

        System.out.println("=== Empty Flux ===");
        Flux.empty()
                .subscribe(System.out::println);

        System.out.println("=== Flux.just ===");
        Flux.just("Java", "Sample", "Approach", ".com")
                .map(item -> item.toUpperCase())
                .subscribe(System.out::print);

        System.out.println("\n=== Flux from List ===");
        List<String> list = Arrays.asList("JAVA", "SAMPLE", "APPROACH", ".COM");
        Flux.fromIterable(list)
                .map(item -> item.toLowerCase())
                .subscribe(System.out::print);

        System.out.println("\n=== Flux emits increasing values each 100ms ===");
        Flux.interval(Duration.ofMillis(100))
                .map(item -> "tick: " + item)
                .take(10)
                .subscribe(System.out::println);

        Thread.sleep(1500);

        System.out.println("=== Mono emits an Exception ===");
        Mono.error(new RuntimeException("Mono"))
                .doOnError(e -> {System.out.println("inside Mono doOnError()");})
                .subscribe(System.out::println);

//        System.out.println("=== Flux emits an Exception ===");
//        Flux.error(new RuntimeException("Flux"))
//                .subscribe(System.out::println);
    }

    @Test
    public void logTest() throws Exception {

        Flux<Integer> flux = Flux.range(1, 10)
                .log()
                .take(3);
        flux.subscribe();

    }
}
