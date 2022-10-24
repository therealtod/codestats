package com.example.codestats

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@SpringBootTest
@ActiveProfiles("test")
internal class APITest : AbstractSpringBootIntegrationTest() {
    @Test
    fun `Should return language percentages`() {
        val mvcResult = mockMvc.get(uri = URI.create("/codestats/api/v1/languagepercentages"))
            .andDo { print() }
            .andExpect { MockMvcResultMatchers.status().is2xxSuccessful }
            .andReturn()

        val expected = "{\"Ruby\":0.5,\"TypeScript\":0.2,\"Python\":0.3}"
        val actual = mvcResult.response.contentAsString
        Assertions.assertEquals(expected, actual)
    }
}
