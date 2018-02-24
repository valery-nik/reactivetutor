package ru.oz.tutorials;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface PostRepository extends ReactiveMongoRepository<Post, String> {
}