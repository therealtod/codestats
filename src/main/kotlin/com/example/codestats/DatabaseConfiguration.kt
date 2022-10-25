package com.example.codestats

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
@EnableJdbcRepositories
class DatabaseConfiguration: AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "db"
    }

    override fun mongoClient(): MongoClient {
        return super.mongoClient()
    }
}