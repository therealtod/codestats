package com.example.codestats

import com.example.codestats.model.RepositoryId
import com.example.codestats.model.tableRow.LanguageUsageBytes

object LanguageUsageBytesFactory {
    fun createLanguageUsageBytes(
        repositoryId: RepositoryId,
        languageId: String,
        bytes: Long
    ): LanguageUsageBytes {
        return LanguageUsageBytes(
            repositoryId = repositoryId,
            languageId = languageId,
            bytes = bytes
        )
    }
}
