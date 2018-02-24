package ru.oz.tutorials;

import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 24.02.2018.
 */
public class ProductController {

//    @GetMapping("/traditional")
//    public List<Product> getAllProducts() {
//        System.out.println("Traditional way started");
//        List<Product> products = new ArrayList<>(); //; = prodService.getProducts("traditional");
//        System.out.println("Traditional way completed");
//        return products;
//    }
//
//    @GetMapping(value = "/reactive", headers= {"TEXT_EVENT_STREAM_VALUE"})
//    public Flux<Product> getAll() {
//        System.out.println("Reactive way using Flux started");
//        Flux<Product> fluxProducts = new Flux<Product>() {
//        }//  = prodService.getProductsStream("Flux");
//        System.out.println("Reactive way using Flux completed");
//        return fluxProducts;
//    }
}
