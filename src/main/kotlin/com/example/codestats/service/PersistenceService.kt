package com.example.codestats.service

import com.example.codestats.model.bom.GithubRepository

interface PersistenceService {
    fun batchWriteRepositoryData(data: Collection<GithubRepository>)

    fun clearTables()
}
