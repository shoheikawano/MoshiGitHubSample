package com.shoheikawano.sample.github.data.remote.api

import com.shoheikawano.sample.github.data.remote.response.RepositoriesResponse
import retrofit2.http.GET

interface GitHubApiService {

    @GET("search/repositories?q=topic:Android&language:kotlin")
    suspend fun searchAndroidKotlinRepos(): RepositoriesResponse
}
