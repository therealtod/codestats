package com.example.codestats.model.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.net.URL

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GitHubRepoSummary(
    val url: URL
)
