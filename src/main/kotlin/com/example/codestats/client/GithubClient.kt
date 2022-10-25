package com.example.codestats.client

import com.example.codestats.model.dto.GithubOrgReposReply
import com.example.codestats.model.dto.GithubRepoDetailsReply
import com.example.codestats.model.dto.RepositoryLanguagesBytesReply
import java.net.URL

interface GithubClient {
    fun getOrgRepositoriesSummary(orgId: String): GithubOrgReposReply

    fun getRepositoryDetails(url: URL): GithubRepoDetailsReply

    fun getRepositoryLanguagesBytes(url: URL): RepositoryLanguagesBytesReply
}