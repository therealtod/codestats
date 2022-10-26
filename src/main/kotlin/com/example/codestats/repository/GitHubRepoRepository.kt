package com.example.codestats.repository

import com.example.codestats.model.RepositoryId
import com.example.codestats.model.tableRow.RepositoryData
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface GitHubRepoRepository : CrudRepository<RepositoryData, Long>{
    @Query("""
        INSERT INTO language_usage_bytes(repository_id, language_id, bytes) 
        VALUES (:repositoryId, :languageId, :bytes)
    """)
    fun saveLanguageUsageBytes(
        repositoryId: RepositoryId,
        languageId: Long,
        bytes: Long
    )
}
