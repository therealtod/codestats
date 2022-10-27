package com.example.codestats

import com.example.codestats.client.GithubClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
@ActiveProfiles("test")
abstract class AbstractSpringBootIntegrationTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockBean
    protected lateinit var githubClient: GithubClient
}