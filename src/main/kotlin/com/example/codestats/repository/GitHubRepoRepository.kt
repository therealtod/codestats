package com.example.codestats.repository

import com.example.codestats.model.tableRow.RepositoryData
import org.springframework.data.repository.CrudRepository

interface GitHubRepoRepository : CrudRepository<RepositoryData, Long>
