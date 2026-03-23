package com.ynov.guilde.infrastructure.log;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiLogMongoRepository extends MongoRepository<ApiLog, String> {
}