package sk.figlar.apod.api

import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

private const val API_KEY = "ejIN7M9Yj2pqdvwYLGQlEC2rYaCbhDltzkjQcVj1"

interface ApodApi {

    @GET(
        "planetary/apod?api_key=$API_KEY"
    )
    suspend fun getApodApiModels(@Query("start_date") startDate: String): List<ApodApiModel>
}