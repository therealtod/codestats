package com.example.codestats.service.impl

import com.example.codestats.client.GithubClient
import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.common.OrgId
import com.example.codestats.service.GithubDataService
import org.springframework.stereotype.Service

@Service
class HttpGithubDataService(private val githubClient: GithubClient) : GithubDataService {
    override fun getLanguageUsageData(orgId: OrgId): List<GithubRepository> {
        val response = githubClient.getOrgRepositoriesSummary(orgId)
        val repositoriesWithDetails = response.mapNotNull { repo ->
            githubClient.getRepositoryDetails(repo.url)
        }
        val repositories = mutableListOf<GithubRepository>()

        repositoriesWithDetails.forEach { repo ->
            val id = repo.id
            val reply = githubClient.getRepositoryLanguagesBytes(repo.languagesUrl)
            val repository = GithubRepository(
                gitHubId = id,
                languageUsageMap = reply?.entries.orEmpty().associate { Pair(it.key, it.value) }
            )
            repositories.add(repository)
        }
        return repositories
    }
}
