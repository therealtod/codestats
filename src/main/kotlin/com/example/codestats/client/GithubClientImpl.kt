package com.example.codestats.client

import com.example.codestats.model.dto.GithubOrgReposReply
import com.example.codestats.model.dto.GithubRepoDetailsReply
import com.example.codestats.model.dto.RepositoryLanguagesBytesReply
import com.example.codestats.scheduledtasks.GithubDataFetchTasks
import io.netty.handler.codec.http.HttpHeaderNames
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import java.net.URL

@Component
class GithubClientImpl(
    webClientBuilder: WebClient.Builder,
    environment: Environment
): GithubClient {
    private val _builder = webClientBuilder.clone()
    private val githubToken = environment.getProperty("GITHUB_TOKEN")
        ?: throw IllegalStateException("This client needs a GITHUB_TOKEN variable to be set in your environment")

    private val webClient = _builder
        .baseUrl("https://api.github.com")
        .defaultHeader(HttpHeaderNames.ACCEPT.toString(), MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaderNames.AUTHORIZATION.toString(), "$TOKEN_PREFIX $githubToken" )
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

    override fun getRepositoryDetails(url: URL): GithubRepoDetailsReply? {
        val request = webClient
            .get()
            .uri(url.toURI())


        return request
            .retrieve()
            .bodyToMono<GithubRepoDetailsReply>()
            .block()
    }

    override fun getRepositoryLanguagesBytes(url: URL): RepositoryLanguagesBytesReply? {
        val request = webClient
            .get()
            .uri(url.toURI())


        return request
            .retrieve()
            .bodyToMono<RepositoryLanguagesBytesReply>()
            .block()
    }

    companion object{
        private val log: Logger = LoggerFactory.getLogger(GithubClientImpl::class.java)
        private const val TOKEN_PREFIX = "Bearer"
        private const val ORG_SUMMARY_URI = "/orgs/{org_id}/repos"
    }
}