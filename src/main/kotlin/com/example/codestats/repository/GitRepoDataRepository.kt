package com.example.codestats.repository

import com.example.codestats.model.tableRow.LanguageBytes

interface GitRepoDataRepository {
    fun getLanguageBytes(): LanguageBytes
}