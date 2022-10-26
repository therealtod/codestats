package com.example.codestats.service.implementation

import com.example.codestats.LanguageFactory
import com.example.codestats.LanguageUsageBytesFactory
import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.tableRow.Language
import com.example.codestats.model.tableRow.LanguageUsageBytes
import com.example.codestats.repository.LanguageRepository
import com.example.codestats.service.PersistenceService
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class PersistenceServiceImpl(
    private val languageRepository: LanguageRepository
) : PersistenceService {
    override fun batchWriteRepositoryData(data: Collection<GithubRepository>) {
        val languagesSet = data.flatMap { it.languageUsageMap.keys }.distinct()
        val languagesDBData = languagesSet.map(LanguageFactory::createLanguage)
        val savedLanguages = languageRepository.saveAll(languagesDBData)
        val languageUsageDBData = createLanguageUsageDBEntities(
            data = data,
            languages = savedLanguages
        )
        languageRepository.bulkInsertLanguageUsageBytes(languageUsageDBData)
    }

    override fun clearTables() {
        languageRepository.deleteAllLanguageUsageBytes()
        languageRepository.deleteAll()
    }

    private fun createLanguageUsageDBEntities(
        data: Iterable<GithubRepository>,
        languages: Iterable<Language>
    ): List<LanguageUsageBytes> {
        val languagesMap = languages.associateBy { it.languageName }

        val languageUsageDBData = mutableListOf<LanguageUsageBytes>()
        data.forEach { repo ->
            repo.languageUsageMap.forEach {
                val language = languagesMap[it.key]
                    ?: throw IllegalStateException("No stored language with name ${it.key}")
                val bytes = it.value
                val dbEntry = LanguageUsageBytesFactory.createLanguageUsageBytes(
                    languageId = language.languageId,
                    bytes = bytes
                )
                languageUsageDBData.add(dbEntry)
            }
        }
        return languageUsageDBData
    }
}
