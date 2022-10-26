package com.example.codestats.service

import com.example.codestats.model.bom.GithubRepository
import com.example.codestats.model.common.OrgId

interface GithubDataService {
    fun getLanguageUsageData(orgId: OrgId): List<GithubRepository>
}
