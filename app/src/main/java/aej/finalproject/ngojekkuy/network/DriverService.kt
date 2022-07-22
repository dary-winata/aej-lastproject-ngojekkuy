package aej.finalproject.ngojekkuy.network

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.data.LoginResponse
import aej.finalproject.ngojekkuy.data.UserResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface DriverService {
    @POST(Endpoint.LOGIN_USER)
        suspend fun login(
            @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @GET(Endpoint.GET_USER)
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): Response<UserResponse>

    companion object {
        fun build(): DriverService {
            return Retrofit.Builder()
                .baseUrl(NetworkVariable.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DriverService::class.java)
        }
    }

    object Endpoint {
        const val GET_USER = "/driver"
        const val LOGIN_USER = "/driver/login"
    }
}