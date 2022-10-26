package com.example.codestats.repository

import com.example.codestats.model.RepositoryId
import com.example.codestats.model.tableRow.LanguageUsagePercentages
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

    @Query("""
        SELECT
            pl.language_name ,
            (SUM(lub.bytes)/(
            SELECT
                SUM(bytes) TOTAL
            FROM
                language_usage_bytes lub)) percentage
        FROM
            prog_language pl
        JOIN language_usage_bytes lub ON
            pl.language_id = lub.language_id
        GROUP BY
            pl.language_name

    """)
    fun getLanguageUsagePercentages(): Collection<LanguageUsagePercentages>
}
