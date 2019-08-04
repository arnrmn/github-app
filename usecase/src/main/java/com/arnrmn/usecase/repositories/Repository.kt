package com.arnrmn.usecase.repositories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    val name: String,
    val url: String,
    val openIssues: Int,
    val closedIssues: Int,
    val openPullRequests: Int,
    val closedPullRequests: Int
): Parcelable