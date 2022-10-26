package com.example.codestats

import com.example.codestats.model.tableRow.LanguageUsageBytes

object LanguageUsageBytesFactory {
    fun createLanguageUsageBytes(
        languageId: Long,
        bytes: Long
    ): LanguageUsageBytes {
        return LanguageUsageBytes(
            languageId = languageId,
            bytes = bytes
        )
    }
}
