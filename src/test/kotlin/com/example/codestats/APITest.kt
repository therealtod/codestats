package com.example.codestats

import com.example.codestats.common.ObjectMappers
import com.example.codestats.model.LanguageName
import com.example.codestats.model.dto.LanguagePercentages
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.net.URI

@SpringBootTest
internal class APITest : AbstractSpringBootIntegrationTest() {
    private val mapper = ObjectMappers.defaultObjectMapper

    @Test
    @Sql("classpath:populate_test_db.sql")
    fun `Should return language percentages`() {
        val mvcResult = mockMvc.get(uri = URI.create("/codestats/api/v1/languagepercentages"))
            .andDo { print() }
            .andExpect { MockMvcResultMatchers.status().is2xxSuccessful }
            .andReturn()

        val expected = mapOf(
            "Ruby" to 0.5,
            "Python" to 0.3,
            "TypeScript" to 0.2
        )
        val responseContent = mvcResult.response.contentAsString
        assertEquals(expected, responseContent)
    }

    private fun assertEquals(expected: Map<LanguageName, Double>, responseContent: String) {
        val actual = mapper.readValue<LanguagePercentages>(responseContent)

        Assertions.assertEquals(expected, actual)
    }
}
