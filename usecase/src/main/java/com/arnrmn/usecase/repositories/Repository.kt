package com.arnrmn.usecase.repositories

data class Repository(
    val name: String,
    val url: String,
    val openIssues: Int,
    val closedIssues: Int,
    val openPullRequests: Int,
    val closedPullRequests: Int
)