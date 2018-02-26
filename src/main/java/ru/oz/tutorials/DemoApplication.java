package ru.oz.tutorials;

//import lombok.*;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.config.EnableMongoAuditing;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

////@SpringBootApplication
////@EnableMongoAuditing
//public class DemoApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }
//
//    @Bean
//    public CommandLineRunner initRunner () {
//        return (args) -> {
////            Flux.
//            System.out.println("xxx");
//        };
//    }
//}
//
//interface PostRepository extends ReactiveMongoRepository<Post, String> {
//}
//
//@Slf4j
//@Component
//class DataInitializer implements CommandLineRunner {
//
//    private final PostRepository posts;
//
//    public DataInitializer(PostRepository posts) {
//        this.posts = posts;
//    }
//
//    @Override
//    public void run(String[] args) {
//        log.info("start data initialization  ...");
//        this.posts
//            .deleteAll()
//            .thenMany(
//                Flux
//                    .range(1, 1000)
//                    .flatMap(
//                            num -> this.posts.save(Post.builder().title("Title" + num).content("content of " + "Title" + num).build())
//                    )
//            )
//            .log()
//            .subscribe(
//                    null,
//                    null,
//                    () -> log.info("done initialization...")
//            );
//    }
//}
//
//@Controller
//class PostController {
//
//    private final PostRepository postRepository;
//
//    public PostController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
//    @GetMapping("/home")
//    public String home(Model model) {
//        model.addAttribute("postRepository", this.postRepository.findAll().collectList().block(Duration.ofSeconds(100)));
//        return "home";
//    }
//
//    @GetMapping("/hello")
//    public String hello(Model model) {
//        model.addAttribute("hello", "Hi, Freemarker");
//        return "hello";
//    }
//
//    @GetMapping("/traditional")
//    public List<Post> getAllProducts() {
//        System.out.println("Traditional way started");
//        //List<Post> postRepository = prodService.getProducts("traditional");
//        List<Post> posts = new ArrayList<>();
//        System.out.println("Traditional way completed");
//        return posts;
//    }
//
//    @GetMapping(value = "/posts/feed", produces = TEXT_EVENT_STREAM_VALUE)
//    public Flux<Post> getAll() {
//
////        return WebClient.create("http://localhost:8081").get().uri("/quotes").accept(APPLICATION_STREAM_JSON).retrieve()
////                .bodyToFlux(Post.class).share().log("com.example.trading");
//
//
//        System.out.println("Reactive way using Flux started");
//        Flux<Post> fluxProducts = postRepository.findAll();
//        System.out.println("Reactive way using Flux completed");
//        return fluxProducts;
//    }
//}
//
//@Document
//@Data
//@ToString
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//class Post {
//
//    @Id
//    private String id;
//    private String title;
//    private String content;
//
//    @CreatedDate
//    private LocalDateTime createdDate;
//}