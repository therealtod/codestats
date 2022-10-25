package com.example.codestats.model.tableRow

import com.example.codestats.model.LanguageName
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("prog_language")
data class Language(
    @Id
    @Column("language_id")
    val languageId: Long,
    @Column("language_name")
    val languageName: LanguageName
){
    @PersistenceCreator
    constructor(languageName: LanguageName) : this(0, languageName)
}