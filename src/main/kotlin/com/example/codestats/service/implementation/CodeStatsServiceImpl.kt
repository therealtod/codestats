package com.example.codestats.service.implementation

import com.example.codestats.extensions.round
import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.CodeStatsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class CodeStatsServiceImpl(
    private val languageRepository: LanguageRepository,
    @Value("\${codestats.language.percentages.precision.decimals}")
    private val allowedDecimals: Int
) : CodeStatsService {
    override fun getLanguagePercentages(): LanguagePercentages {
        val dbPercentages = languageRepository.getLanguageUsagePercentages()
        return dbPercentages
            .filter { it.percentage >= 1.0/(10*allowedDecimals) }
            .associate{ Pair(it.languageName, it.percentage.round(allowedDecimals)) }.toMap()
    }
}
