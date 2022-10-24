package com.example.codestats.repository

import com.example.codestats.model.tableRow.LanguageBytes
import com.example.codestats.model.tableRow.RepositoryData
import org.springframework.data.mongodb.repository.MongoRepository


interface DBGitRepoDataRepository: MongoRepository<Long, RepositoryData>, GitRepoDataRepository {
    override fun getLanguageBytes(): LanguageBytes {
        TODO("Not yet implemented")
    }
}