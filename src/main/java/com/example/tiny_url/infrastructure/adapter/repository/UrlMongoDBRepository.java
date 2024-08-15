package com.example.tiny_url.infrastructure.adapter.repository;

import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMongoDBRepository extends MongoRepository<UrlEntity, String>{
}
