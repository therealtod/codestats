package com.example.codestats.repository

import com.example.codestats.model.tableRow.Language
import com.example.codestats.model.tableRow.LanguageUsagePercentages
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository

interface LanguageRepository: CrudRepository<Language, Long>, LanguageUsageBytesRepository{
    @Query("""
        SELECT
            pl.language_name ,
            (SUM(lub.bytes)/(
            SELECT
                SUM(bytes) TOTAL
            FROM
                language_usage_bytes lub)) percentage
        FROM
            prog_language pl
        JOIN language_usage_bytes lub ON
            pl.language_id = lub.language_id
        GROUP BY
            pl.language_name
    """
    )
    fun getLanguageUsagePercentages(): List<LanguageUsagePercentages>
}
