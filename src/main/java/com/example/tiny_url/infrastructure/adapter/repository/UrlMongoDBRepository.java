package com.example.tiny_url.infrastructure.adapter.repository;

import com.example.tiny_url.infrastructure.adapter.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlMongoDBRepository extends MongoRepository<UrlEntity, String>{

    @Query("{ $or: [ { 'key': ?0 }, { 'alias': ?0 } ] }")
    Optional<UrlEntity> findByKeyOrAlias(String idOrAlias);

    boolean existsByKey(String key);
    boolean existsByAlias(String alias);
}
