package client

import com.example.codestats.model.dto.GitHubRepoSummary
import com.example.codestats.model.dto.GithubOrgReposReply

interface GithubClient {
    fun getOrgRepositoriesSummary(orgId: String): GithubOrgReposReply
}