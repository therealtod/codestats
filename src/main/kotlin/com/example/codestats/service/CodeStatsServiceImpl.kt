package com.example.codestats.service

import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.repository.GitRepoDataRepository
import common.model.LanguageId
import org.springframework.stereotype.Service

@Service
class CodeStatsServiceImpl(
    private val gitRepoDataRepository: GitRepoDataRepository
) : CodeStatsService {
    override fun getLanguagePercentages(): LanguagePercentages {
        val perRepoLanguageBytes = gitRepoDataRepository.getLanguageBytes()

        val totalBytes = perRepoLanguageBytes.flatMap { it.value.values }.sum().toDouble()
        val languageBytes = mutableMapOf<LanguageId, Long>()

        perRepoLanguageBytes.flatMap { it.value.entries }.forEach {
            languageBytes[it.key] = languageBytes.getOrDefault(it.key, 0) + it.value
        }

        return languageBytes.mapValues { it.value / totalBytes }
    }
}
