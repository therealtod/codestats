package com.example.codestats.service

import com.example.codestats.model.dto.LanguagePercentages

interface CodeStatsService {
    fun getLanguagePercentages(): LanguagePercentages
}