package com.example.codestats.service.implementation

import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.repository.GitHubRepoRepository
import com.example.codestats.service.CodeStatsService
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
