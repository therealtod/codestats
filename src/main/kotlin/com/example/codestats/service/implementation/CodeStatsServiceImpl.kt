package com.example.codestats.service.implementation

import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.CodeStatsService
import org.springframework.stereotype.Service

@Service
class CodeStatsServiceImpl(
    private val languageRepository: LanguageRepository
) : CodeStatsService {
    override fun getLanguagePercentages(): LanguagePercentages {
        val dbPercentages = languageRepository.getLanguageUsagePercentages()
        return dbPercentages.associate{ Pair(it.languageName, it.percentage) }.toMap()
    }
}
