package com.example.codestats.service

import com.example.codestats.model.LanguageName
import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.model.tableRow.LanguageUsagePercentages
import com.example.codestats.repository.GitHubRepoRepository
import org.springframework.stereotype.Service

@Service
class CodeStatsServiceImpl(
    private val gitRepoDataRepository: GitHubRepoRepository
) : CodeStatsService {
    override fun getLanguagePercentages(): LanguagePercentages {
        val dbPercentages = gitRepoDataRepository.getLanguageUsagePercentages()
        return dbPercentages.associate{ Pair(it.languageName, it.percentage) }.toMap()
    }
}
