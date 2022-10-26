package com.example.codestats.service

import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.tableRow.Language
import com.example.codestats.model.tableRow.LanguageUsageBytes
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.implementation.PersistenceServiceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test



internal class PersistenceServiceImplTest {
    private val languageRepository = mockk<LanguageRepository>(relaxed = true)
    private val service = PersistenceServiceImpl(
        languageRepository = languageRepository
    )

    @Test
    fun `Should generate the right persistence calls for language usage bytes`() {
        every { languageRepository.saveAll<Language>(any()) } returns listOf(
            Language(1, "Ruby"),
            Language(2, "JavaScript"),
            Language(3, "Python"),
            Language(4, "Bash"),
            Language(5, "Perl")
        )

        service.batchWriteRepositoryData(data)

        verify(exactly = 1) {
            languageRepository.saveAll(
                listOf(
                    Language("Ruby"),
                    Language("JavaScript"),
                    Language("Python"),
                    Language("Bash"),
                    Language("Perl")
                )
            )
        }

        verify(exactly = 1) {
            languageRepository.bulkInsertLanguageUsageBytes(
                listOf(
                    LanguageUsageBytes(
                        languageId = 1, bytes = 10
                    ),
                    LanguageUsageBytes(
                        languageId = 2, bytes = 40
                    ),
                    LanguageUsageBytes(
                        languageId = 3, bytes = 5
                    ),
                    LanguageUsageBytes(
                        languageId = 1, bytes = 30
                    ),
                    LanguageUsageBytes(
                        languageId = 2, bytes = 90
                    ),
                    LanguageUsageBytes(
                        languageId = 4, bytes = 40
                    ),
                    LanguageUsageBytes(
                        languageId = 5, bytes = 10
                    ),
                    LanguageUsageBytes(
                        languageId = 3, bytes = 40
                    )
                )
            )
        }
    }

    @Test
    fun `Should throw a IllegalStateException When storing a language usage bytes object for an unknown language`() {
        Assertions.assertThrows(IllegalStateException::class.java){
            service.batchWriteRepositoryData(data)
        }
    }

    companion object {
        private val data = listOf(
            GithubRepository(
                gitHubId = 1,
                languageUsageMap = mapOf(
                    "Ruby" to 10,
                    "JavaScript" to 40,
                    "Python" to 5
                )
            ),
            GithubRepository(
                gitHubId = 2,
                languageUsageMap = mapOf(
                    "Ruby" to 30,
                    "JavaScript" to 90,
                )
            ),
            GithubRepository(
                gitHubId = 3,
                languageUsageMap = mapOf(
                    "Bash" to 40,
                    "Perl" to 10,
                    "Python" to 40
                )
            ),
        )
    }
}