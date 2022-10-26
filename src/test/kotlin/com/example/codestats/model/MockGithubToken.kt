package com.example.codestats.model

import com.example.codestats.model.common.GithubToken
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
class MockGithubToken: GithubToken{
    override val tokenString = "token"
}
