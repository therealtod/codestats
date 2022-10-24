package com.example.codestats

import com.mongodb.client.MongoClient
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class DatabaseConfiguration: AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "db"
    }

    override fun mongoClient(): MongoClient {
        return super.mongoClient()
    }
}