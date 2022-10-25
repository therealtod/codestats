package com.example.codestats.model.tableRow

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("repository")
data class RepositoryData(
    @Id
    @Column("repository_id")
    val repositoryId: Long
){
    @PersistenceCreator
    constructor() : this(0)
}
