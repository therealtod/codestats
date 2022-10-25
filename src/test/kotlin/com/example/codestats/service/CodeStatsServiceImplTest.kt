package com.example.codestats.service

import com.example.codestats.repository.GitRepoDataRepository
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

    private val repository: GitRepoDataRepository = mockk()
    private val service = CodeStatsServiceImpl(repository)

    @BeforeAll
    fun setUp() {
        every { repository.calculateLanguageBytes() } returns mockData
    }

    @Test
    fun `Should produce the right Language percentages`() {
        val expected = mapOf(
            "language_a" to 0.1,
            "language_b" to 0.6,
            "language_c" to 0.3
        )
        val actual = service.getLanguagePercentages()

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        val mockData = mapOf(
            1L to mapOf(
                "language_a" to 10L,
                "language_b" to 40L,
            ),
            2L to mapOf(
                "language_b" to 20L,
                "language_c" to 30L
            )
        )
    }
}
