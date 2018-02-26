package ru.oz.tutorials;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Test
    public void declare() throws InterruptedException {
        Mono<String> mono1 = Mono.just("JavaSampleApproach.com");
        Mono<String> mono2 = Mono.just("|Java Technology");
        Mono<String> mono3 = Mono.just("|Spring Framework");

        Flux<String> flux1 = Flux.just("{1}", "{2}", "{3}", "{4}");
        Flux<String> flux2 = Flux.just("|A|", "|B|", "|C|");

        // FLux emits item each 500ms
        Flux<String> intervalFlux1 = Flux
                .interval(Duration.ofMillis(500))
                .zipWith(flux1, (i, string) -> string);

        // FLux emits item each 700ms
        Flux<String> intervalFlux2 = Flux
                .interval(Duration.ofMillis(700))
                .zipWith(flux2, (i, string) -> string);

//        //
//
//        Flux.concat(mono1, mono3, mono2).subscribe(System.out::print);
//        // JavaSampleApproach.com|Spring Framework|Java Technology
//
//        Flux.concat(flux2, flux1).subscribe(System.out::print);
//        // |A||B||C|{1}{2}{3}{4}
//
//        Flux.concat(intervalFlux2, flux1).subscribe(System.out::print);
//        Thread.sleep(3000);
//        // |A||B||C|{1}{2}{3}{4}
//        // each of |A|,|B|,|C| emits each 700ms, then {1},{2},{3},{4} emit immediately
//
        Flux.concat(intervalFlux2, intervalFlux1).subscribe( e -> {
            System.out.printf("%s elem : %s \n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")), e);
        }); //System.out::println);
        Thread.sleep(5000);
        // |A||B||C|{1}{2}{3}{4}
        // each of |A|,|B|,|C| emits each 700ms, then each of {1},{2},{3},{4} emits each 500ms
//        Flux.concat(mono1, mono3, mono2).subscribe(System.out::print);
//        // JavaSampleApproach.com|Spring Framework|Java Technology
//
//        Flux.concat(flux2, flux1).subscribe(System.out::print);
//        // |A||B||C|{1}{2}{3}{4}
//
//        Flux.concat(intervalFlux2, flux1).subscribe(System.out::print);
//        Thread.sleep(3000);
//        // |A||B||C|{1}{2}{3}{4}
//        // each of |A|,|B|,|C| emits each 700ms, then {1},{2},{3},{4} emit immediately
//
//        Flux.concat(intervalFlux2, intervalFlux1).subscribe(System.out::print);
//        Thread.sleep(5000);
//        // |A||B||C|{1}{2}{3}{4}
//        // each of |A|,|B|,|C| emits each 700ms, then each of {1},{2},{3},{4} emits each 500ms

    }
}
