package rs.rocketbyte.data.remote.api.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleService {

    @GET("search")
    suspend fun search(@Query("query") query: String): Response<List<String>>

}