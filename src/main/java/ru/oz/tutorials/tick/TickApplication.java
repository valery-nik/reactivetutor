package ru.oz.tutorials.tick;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        MongoAutoConfiguration.class, MongoRepositoriesAutoConfiguration.class,
        MongoReactiveAutoConfiguration.class, MongoDataAutoConfiguration.class,
        MongoReactiveDataAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = "ru.oz.tutorials.tick")
public class TickApplication {

    public static void main(String[] args) {
        SpringApplication.run(TickApplication.class, args);
    }

    @GetMapping("/ticks/{id}")
    public Mono<Tick> getRandomTick(@PathVariable long id) {
        return Mono.just(Tick.builder().now(LocalDateTime.now()).build());
    }

    @GetMapping(value = "/ticks", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Tick> getTicks() {
        int i = 0;

        List<Tick> tickList = new ArrayList<>();
        while (i < 100) {
            tickList.add(Tick.builder().now(LocalDateTime.now()).build());
            i++;
        }
        return Flux.fromIterable(tickList).delayElements(Duration.ofMillis(200));
    }

}

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class Tick {
    LocalDateTime now;
}
