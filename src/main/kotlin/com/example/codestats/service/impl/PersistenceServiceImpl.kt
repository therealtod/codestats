package com.example.codestats.service.impl

import com.example.codestats.LanguageFactory
import com.example.codestats.LanguageUsageBytesFactory
import com.example.codestats.RepositoryDataFactory
import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.tableRow.Language
import com.example.codestats.model.tableRow.LanguageUsageBytes
import com.example.codestats.model.tableRow.RepositoryData
import com.example.codestats.repository.GitHubRepoRepository
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.PersistenceService
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class PersistenceServiceImpl(
    private val repoRepository: GitHubRepoRepository,
    private val languageRepository: LanguageRepository
) : PersistenceService {
    override fun batchWriteRepositoryData(data: Collection<GithubRepository>) {
        val repositoriesDBData = data.map(RepositoryDataFactory::createRepositoryData)
        val languagesSet = data.flatMap { it.languageUsageMap.keys }.distinct()
        val languagesDBData = languagesSet.map(LanguageFactory::createLanguage)
        val savedRepositoryData = repoRepository.saveAll(repositoriesDBData)
        val savedLanguages = languageRepository.saveAll(languagesDBData)
        val languageUsageDBData = createLanguageUsageDBEntities(
            data = data,
            repositoryData = savedRepositoryData,
            languages = savedLanguages
        )
        languageUsageDBData.forEach {
            repoRepository.saveLanguageUsageBytes(
                repositoryId = it.repositoryId,
                languageId = it.languageId,
                bytes = it.bytes
            )
        }
    }

    override fun clearTables() {
        repoRepository.deleteAllLanguageUsageBytes()
        languageRepository.deleteAll()
        repoRepository.deleteAll()
    }

    private fun createLanguageUsageDBEntities(
        data: Iterable<GithubRepository>,
        repositoryData: Iterable<RepositoryData>,
        languages: Iterable<Language>
    ): List<LanguageUsageBytes> {
        val repositoriesMap = repositoryData.associateBy { it.gitHubId }
        val languagesMap = languages.associateBy { it.languageName }

        val languageUsageDBData = mutableListOf<LanguageUsageBytes>()
        data.forEach { repo ->
            val repository = repositoriesMap[repo.gitHubId]
                ?: throw IllegalStateException("No stored repository with id ${repo.gitHubId}")
            repo.languageUsageMap.forEach {
                val language = languagesMap[it.key]
                    ?: throw IllegalStateException("No stored language with name ${it.key}")
                val bytes = it.value
                val dbEntry = LanguageUsageBytesFactory.createLanguageUsageBytes(
                    repositoryId = repository.repositoryId,
                    languageId = language.languageId,
                    bytes = bytes
                )
                languageUsageDBData.add(dbEntry)
            }
        }
        return languageUsageDBData
    }
}
