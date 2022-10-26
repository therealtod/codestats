package com.example.codestats.model.tableRow

import org.springframework.data.relational.core.mapping.Column

data class LanguageUsagePercentages(
    @Column("language_name")
    val languageName: String,
    @Column("percentage")
    val percentage: Double
)
