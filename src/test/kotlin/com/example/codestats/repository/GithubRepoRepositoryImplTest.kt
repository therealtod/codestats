package com.example.codestats.repository

import com.example.codestats.AbstractSpringBootIntegrationTest
import com.example.codestats.model.tableRow.LanguageUsagePercentages
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql

@SpringBootTest
class GithubRepoRepositoryImplTest: AbstractSpringBootIntegrationTest() {
    @Autowired
    private lateinit var repository: LanguageRepository

    @Test
    @Sql("classpath:populate_test_db.sql")
    fun `Should calculate the right language percentages`() {
        val expected = listOf(
            LanguageUsagePercentages(languageName = "Python", percentage = 0.3),
            LanguageUsagePercentages(languageName = "Ruby", percentage = 0.5),
            LanguageUsagePercentages(languageName = "TypeScript", percentage = 0.2),
        )
        val actual = repository.getLanguageUsagePercentages()

        Assertions.assertEquals(expected, actual)
    }
}