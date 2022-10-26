package com.example.codestats.model.tableRow

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("language_usage_bytes")
data class LanguageUsageBytes(
    @Id
    @Column("id")
    val id: Long,
    @Column("language_id")
    val languageId: Long,
    @Column("bytes")
    val bytes: Long
){
    @PersistenceCreator
    constructor(
        languageId: Long,
        bytes: Long
    ) : this (0, languageId, bytes)
}
