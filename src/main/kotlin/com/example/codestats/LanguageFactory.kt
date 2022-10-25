package com.example.codestats

import com.example.codestats.model.LanguageName
import com.example.codestats.model.tableRow.Language

object LanguageFactory {
    fun createLanguage(languageName: LanguageName): Language{
        return Language(languageName)
    }
}