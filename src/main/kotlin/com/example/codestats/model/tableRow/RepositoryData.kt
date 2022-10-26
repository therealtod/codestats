package com.example.codestats.model.tableRow

import com.example.codestats.model.RepositoryId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("repository")
data class RepositoryData(
    @Id
    @Column("repository_id")
    val repositoryId: RepositoryId,
    @Column("github_id")
    val gitHubId: Long
){
    @PersistenceCreator
    constructor(gitHubId: Long) : this(0, gitHubId)
}
