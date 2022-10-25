package com.example.codestats.model.tableRow

import com.example.codestats.model.RepositoryId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("language_usage_bytes")
data class LanguageUsageBytes(
    @Id
    @Column("id")
    val id: Long,
    @Column("repository_id")
    val repositoryId: RepositoryId,
    @Column("language_id")
    val languageId: String,
    @Column("bytes")
    val bytes: Long
){
    @PersistenceCreator
    constructor(
        repositoryId: RepositoryId,
        languageId: String,
        bytes: Long
    ) : this (0, repositoryId, languageId, bytes)
}
