package com.example.codestats

import com.example.codestats.model.dto.GithubRepoDetailsReply
import com.example.codestats.model.tableRow.RepositoryData

object RepositoryDataFactory {
    fun createRepositoryData(repoDetails: GithubRepoDetailsReply): RepositoryData{
        return RepositoryData()
    }
}