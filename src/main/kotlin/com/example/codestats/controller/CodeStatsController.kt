package com.example.codestats.controller

import com.example.codestats.model.dto.LanguagePercentages
import com.example.codestats.service.CodeStatsService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    value = ["codestats/api/v1"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class CodeStatsController(
    private val codeStatsService: CodeStatsService
) {
    @GetMapping(
        path = ["/languagepercentages"]
    )
    fun getLanguagePercentages(): LanguagePercentages {
        return codeStatsService.getLanguagePercentages()
    }
}
