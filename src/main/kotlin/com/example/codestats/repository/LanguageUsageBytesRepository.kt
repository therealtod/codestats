package com.example.codestats.repository

import com.example.codestats.model.tableRow.LanguageUsageBytes
import com.example.codestats.model.tableRow.LanguageUsagePercentages

interface LanguageUsageBytesRepository {
    fun deleteAllLanguageUsageBytes()

    fun bulkInsertLanguageUsageBytes(data: Collection<LanguageUsageBytes>)
}