package com.example.codestats

import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.tableRow.RepositoryData

object RepositoryDataFactory {
    fun createRepositoryData(repo: GithubRepository): RepositoryData{
        return RepositoryData(gitHubId = repo.gitHubId)
    }
}
