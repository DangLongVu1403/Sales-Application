package com.example.androidnangcao.Interface
import com.example.androidnangcao.model.Request.LoginRequest
import com.example.androidnangcao.model.Request.RegisterRequest
import com.example.androidnangcao.model.Response.LoginResponse
import com.example.androidnangcao.model.Response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
interface ApiService {
    @POST("api/v1/auth/signin")
    fun signin(@Body request: LoginRequest): Call<LoginResponse>
    @POST("api/v1/auth/signup")
    fun signup(@Body request: RegisterRequest): Call<RegisterResponse>
}