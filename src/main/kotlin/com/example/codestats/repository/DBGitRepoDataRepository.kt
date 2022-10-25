package com.example.codestats.repository

import com.example.codestats.model.tableRow.LanguageBytes
import com.example.codestats.model.tableRow.RepositoryData
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.Repository


interface DBGitRepoDataRepository: Repository<Long, RepositoryData>, GitRepoDataRepository {
    override fun getLanguageBytes(): LanguageBytes {
        TODO("Not yet implemented")
    }
}