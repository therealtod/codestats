package com.example.codestats

import com.example.codestats.model.RepositoryId
import com.example.codestats.model.tableRow.RepositoryLanguage

object RepositoryLanguageFactory {
    fun createRepositoryLanguage(
        repositoryId: RepositoryId,
        languageId: String,
        bytes: Long
    ): RepositoryLanguage {
        return RepositoryLanguage(
            repositoryId = repositoryId,
            languageId = languageId,
            bytes = bytes
        )
    }
}
