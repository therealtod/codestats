package com.example.codestats.repository

import com.example.codestats.model.tableRow.Language
import org.springframework.data.repository.CrudRepository

interface LanguageRepository: CrudRepository<Language, Long>