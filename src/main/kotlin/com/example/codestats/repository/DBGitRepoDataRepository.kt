package com.example.codestats.repository

import com.example.codestats.model.tableRow.LanguageBytes
import com.example.codestats.model.tableRow.RepositoryData
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.Repository


interface DBGitRepoDataRepository: Repository<RepositoryData, Long>, GitRepoDataRepository {
    @Query("SELECT 1")
    override fun calculateLanguageBytes(): LanguageBytes
}
