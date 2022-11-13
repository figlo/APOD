package sk.figlar.apod.api

import retrofit2.http.GET

private const val API_KEY = "ejIN7M9Yj2pqdvwYLGQlEC2rYaCbhDltzkjQcVj1"

interface ApodApi {
    @GET(
        "planetary/apod?api_key=$API_KEY" +
        "&start_date=2022-10-15"
    )
    suspend fun getApiApods(): List<ApodApiModel>
}