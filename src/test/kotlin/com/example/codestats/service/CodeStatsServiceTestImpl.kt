package com.example.codestats.service

import com.example.codestats.model.dto.LanguagePercentages
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("test")
internal class CodeStatsServiceTestImpl: CodeStatsService {
    override fun getLanguagePercentages(): LanguagePercentages {
        return mapOf(
            "Ruby" to 0.5,
            "TypeScript" to 0.2,
            "Python" to 0.3
        )
    }
}
