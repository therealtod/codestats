package com.example.codestats.repository.implementation

import com.example.codestats.model.tableRow.LanguageUsageBytes
import com.example.codestats.repository.LanguageUsageBytesRepository
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import java.sql.PreparedStatement

@Component
class LanguageUsageBytesRepositoryImpl(private val jdbcTemplate: JdbcTemplate): LanguageUsageBytesRepository {

    override fun deleteAllLanguageUsageBytes() {
        val query = "DELETE FROM $TABLE_NAME"
        jdbcTemplate.queryForObject(query, Int::class.java)
    }

    override fun bulkInsertLanguageUsageBytes(data: Collection<LanguageUsageBytes>) {
        val entities = data.toList()

        val query = "INSERT INTO $TABLE_NAME VALUES (?, ?)"

        jdbcTemplate.batchUpdate(
            query,
            object : BatchPreparedStatementSetter {
                override fun setValues(ps: PreparedStatement, i: Int) {
                    ps.setLong(1, entities[i].languageId)
                    ps.setLong(2, entities[i].bytes)
                }

                override fun getBatchSize(): Int {
                    return data.size
                }
            }
        )
    }

    companion object {
        private const val TABLE_NAME = "language_usage_bytes"
    }
}