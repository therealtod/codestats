package com.example.codestats.scheduledtasks

import com.example.codestats.client.GithubClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class GithubDataFetchTasks(
    private val githubClient: GithubClient
) {

    @Scheduled(fixedDelay = 1000)
    fun fetchGithubData() {
        TODO()
    }

    private val log: Logger = LoggerFactory.getLogger(GithubDataFetchTasks::class.java)
}