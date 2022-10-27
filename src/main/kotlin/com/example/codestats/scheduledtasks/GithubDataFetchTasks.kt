package com.example.codestats.scheduledtasks

import com.example.codestats.service.GithubDataService
import com.example.codestats.service.PersistenceService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class GithubDataFetchTasks(
    private val persistenceService: PersistenceService,
    private val githubDataService: GithubDataService,
    @Value("\${codestats.target.orgid}")
    private val orgId: String?
) {
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    fun fetchProductboardGithubData() {
        val targetOrgId = orgId ?: DEFAULT_ORG_ID
        log.info("clearing DB tables...")
        persistenceService.clearTables()
        log.info("DONE")
        log.info("Fetching data from github...")
        val data = githubDataService.getLanguageUsageData(targetOrgId)
        log.info("DONE")
        log.info("Writing records to DB")
        persistenceService.batchWriteRepositoryData(data)
        log.info("DONE")
    }

    companion object {
        private val log = LogManager.getLogger(GithubDataFetchTasks::class.java)
        private const val DEFAULT_ORG_ID = "productboard"
    }
}
