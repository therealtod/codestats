package com.example.codestats.scheduledtasks

import com.example.codestats.LanguageFactory
import com.example.codestats.LanguageUsageBytesFactory
import com.example.codestats.RepositoryDataFactory
import com.example.codestats.client.GithubClient
import com.example.codestats.model.tableRow.RepositoryData
import com.example.codestats.repository.GitHubRepoRepository
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.GithubDataService
import com.example.codestats.service.PersistenceService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URI

@Component
class GithubDataFetchTasks(
    private val persistenceService: PersistenceService,
    private val githubDataService: GithubDataService
) {

    @Scheduled(fixedDelay = 20000)
    fun fetchProductboardGithubData() {

        val data = githubDataService.getLanguageUsageData("productboard")
        persistenceService.batchWriteRepositoryData(data)
    }
}
