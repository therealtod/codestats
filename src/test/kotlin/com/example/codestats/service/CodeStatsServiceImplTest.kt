package com.example.codestats.service

import com.example.codestats.model.tableRow.LanguageUsagePercentages
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.implementation.CodeStatsServiceImpl
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CodeStatsServiceImplTest {

    private val repository: LanguageRepository = mockk()
    private val service = CodeStatsServiceImpl(repository)

    @BeforeAll
    fun setUp() {
        every { repository.getLanguageUsagePercentages() } returns mockData
    }

    @Test
    fun `Should produce the right Language percentages`() {
        val expected = mapOf(
            "language_a" to 0.71,
            "language_b" to 0.04,
            "language_c" to 0.25
        )
        val actual = service.getLanguagePercentages()

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        val mockData = listOf(
            LanguageUsagePercentages(
                languageName = "language_a",
                percentage = 0.71
            ),
            LanguageUsagePercentages(
                languageName = "language_b",
                percentage = 0.04
            ),
            LanguageUsagePercentages(
                languageName = "language_c",
                percentage = 0.25
            )
        )
    }
}
