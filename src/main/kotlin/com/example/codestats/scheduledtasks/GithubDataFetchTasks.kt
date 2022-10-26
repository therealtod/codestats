package com.example.codestats.scheduledtasks

import com.example.codestats.service.GithubDataService
import com.example.codestats.service.PersistenceService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class GithubDataFetchTasks(
    private val persistenceService: PersistenceService,
    private val githubDataService: GithubDataService
) {

    @Scheduled(cron = "0 0 0 * * ?")
    fun fetchProductboardGithubData() {
        persistenceService.clearTables()
        val data = githubDataService.getLanguageUsageData("productboard")
        persistenceService.batchWriteRepositoryData(data)
    }
}
