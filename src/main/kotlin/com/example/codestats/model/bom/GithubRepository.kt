package com.example.codestats.model.bom

import com.example.codestats.model.common.LanguageUsageMap

data class GithubRepository(
    val gitHubId: Long,
    val languageUsageMap: LanguageUsageMap
)
