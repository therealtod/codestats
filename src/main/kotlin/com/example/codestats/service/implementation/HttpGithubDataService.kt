package com.example.codestats.service.implementation

import com.example.codestats.client.GithubClient
import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.common.OrgId
import com.example.codestats.model.dto.GitHubRepoSummary
import com.example.codestats.service.GithubDataService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class HttpGithubDataService(private val githubClient: GithubClient) : GithubDataService {
    override fun getLanguageUsageData(orgId: OrgId): List<GithubRepository> {
        val response = githubClient.getOrgRepositoriesSummary(orgId)

        return runBlocking { executeGithubCalls(response) }
    }

    private suspend fun executeGithubCalls(
        repoSummary: Collection<GitHubRepoSummary>
    ) = coroutineScope {
        val repositories = mutableListOf<GithubRepository>()
        val repositoriesWithDetails = repoSummary.map { repo ->
            async { githubClient.getRepositoryDetails(repo.url) }
        }

        repositoriesWithDetails.forEach { repo ->
            val r = repo.await() ?: throw Exception("AAAAAAA")
            val id = r.id
            val languageUrl = r.languagesUrl
            val reply = async { githubClient.getRepositoryLanguagesBytes(languageUrl) }
            val r2 = reply.await() ?: throw Exception("BBBBBB")
            val repository = GithubRepository(
                gitHubId = id,
                languageUsageMap = r2.entries.associate { Pair(it.key, it.value) }
            )
            repositories.add(repository)
        }
        repositories
    }
}
