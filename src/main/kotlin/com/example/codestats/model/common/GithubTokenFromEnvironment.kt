package com.example.codestats.model.common

import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
@Profile("!test")
class GithubTokenFromEnvironment(
    private val environment: Environment
) : GithubToken {
    override val tokenString: String
        get() = environment.getProperty("GITHUB_TOKEN")
            ?: throw IllegalStateException("This client needs a GITHUB_TOKEN variable to be set in your environment")
}
