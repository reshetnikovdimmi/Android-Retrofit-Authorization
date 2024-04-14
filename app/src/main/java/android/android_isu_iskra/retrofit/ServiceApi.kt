package android.android_isu_iskra.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ServiceApi {
    @GET("/LoginAndroid")
    suspend fun getAllShop(): Shop

    @POST("/identification")
    suspend fun auth(@Body shop: Shop): Response<Shop>

    @GET("products")
    suspend fun getAllProducts(): String

    @GET("Liststring")
    suspend fun getAllCourses(): List<String>

}