package ru.oz.tutorials;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
@EnableMongoAuditing
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

interface PostRepository extends ReactiveMongoRepository<Post, String> {
}

@Slf4j
@Component
class DataInitializer implements CommandLineRunner {

    private final PostRepository posts;

    public DataInitializer(PostRepository posts) {
        this.posts = posts;
    }

    @Override
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.posts
            .deleteAll()
            .thenMany(
                Flux
                    .range(1, 1000)
                    .flatMap(
                            num -> this.posts.save(Post.builder().title("Title" + num).content("content of " + "Title" + num).build())
                    )
            )
            .log()
            .subscribe(
                    null,
                    null,
                    () -> log.info("done initialization...")
            );
    }
}

@Controller
class PostController {

    private final PostRepository posts;

    public PostController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("posts", this.posts.findAll().collectList().block(Duration.ofSeconds(100)));
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "Hi, Freemarker");
        return "hello";
    }
}

@Document
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Post {

    @Id
    private String id;
    private String title;
    private String content;

    @CreatedDate
    private LocalDateTime createdDate;
}