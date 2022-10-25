package com.example.codestats.model.tableRow

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.net.URL

data class RepositoryData(
    @Id
    val id: Long
)
