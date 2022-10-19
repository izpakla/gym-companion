package rs.rocketbyte.data.remote.api

import retrofit2.HttpException
import retrofit2.Response

object ApiHelper {

    suspend fun <T : Any> execute(call: suspend () -> Response<T>): NetworkResult<T> = try {
        val response = call()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }

}