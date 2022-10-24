package com.example.codestats.service

import com.example.codestats.model.tableRow.LanguageBytes
import com.example.codestats.repository.GitRepoDataRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Repository

@Repository
@Profile("test")
class GitRepoDataRepositoryTestImpl: GitRepoDataRepository {
    override fun getLanguageBytes(): LanguageBytes {
        return mapOf(
            1L to mapOf(
                "Ruby" to 50,
                "Python" to 10
            ),
            2L to mapOf(
                "TypeScript" to 20,
                "Python" to 20
            )
        )
    }
}
