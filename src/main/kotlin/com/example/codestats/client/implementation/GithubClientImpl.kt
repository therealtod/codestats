package com.example.codestats.client.implementation

import com.example.codestats.client.GithubClient
import com.example.codestats.model.common.GithubToken
import com.example.codestats.model.dto.GithubOrgReposReply
import com.example.codestats.model.dto.GithubRepoDetailsReply
import com.example.codestats.model.dto.RepositoryLanguagesBytesReply
import io.netty.handler.codec.http.HttpHeaderNames
import kotlinx.coroutines.reactive.awaitFirst
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.net.URL

@Component
class GithubClientImpl(
    webClientBuilder: WebClient.Builder,
    githubToken: GithubToken
): GithubClient {
    private val _builder = webClientBuilder.clone()

    private val webClient = _builder
        .baseUrl(BASE_URL)
        .defaultHeader(HttpHeaderNames.ACCEPT.toString(), MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaderNames.AUTHORIZATION.toString(), "$TOKEN_PREFIX ${githubToken.tokenString}" )
        .build()

    override fun getOrgRepositoriesSummary(orgId: String): GithubOrgReposReply {
        val request = webClient
            .get()
            .uri(ORG_SUMMARY_URI) {
                it.build(
                    mapOf("org_id" to orgId)
                )
            }

        return request
            .retrieve()
            .bodyToMono<GithubOrgReposReply>()
            .block() ?: emptyList()
    }

    override suspend fun getRepositoryDetails(url: URL): GithubRepoDetailsReply? {
        val request = webClient
            .get()
            .uri(url.toURI())


        return request
            .retrieve()
            .bodyToMono<GithubRepoDetailsReply>()
            .awaitFirst()
    }

    override suspend fun getRepositoryLanguagesBytes(url: URL): RepositoryLanguagesBytesReply? {
        val request = webClient
            .get()
            .uri(url.toURI())


        return request
            .retrieve()
            .bodyToMono<RepositoryLanguagesBytesReply>()
            .awaitFirst()
    }

    companion object{
        private const val TOKEN_PREFIX = "Bearer"
        private const val ORG_SUMMARY_URI = "/orgs/{org_id}/repos"
        private const val BASE_URL = "https://api.github.com"
    }
}