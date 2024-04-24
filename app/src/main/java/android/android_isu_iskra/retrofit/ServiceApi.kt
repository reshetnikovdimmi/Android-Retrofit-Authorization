package android.android_isu_iskra.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ServiceApi {
    @GET("/api/v1/auth/LoginAndroid")
    suspend fun getAllShop(): Shop

    @POST("/api/v1/auth/authenticate")
    suspend fun auth(@Body shop: Shop): Response<Shop>

    @GET("/promo")
    suspend fun getAllProducts(): Response<Shop>

    @GET("/api/v1/auth/LoginAndroid")
    suspend fun getAllCourses(): List<String>

    @Headers( "Content-Type: application/json;charset=UTF-8")
    @GET("/promo")
    suspend fun getAllProducts(@Header("Authorization") token: String): Response<List<Promo>>
}