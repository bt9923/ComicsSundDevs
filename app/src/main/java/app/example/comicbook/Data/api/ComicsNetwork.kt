package app.example.comicbook.Data.api

import app.example.comicbook.BuildConfig
import app.example.comicbook.Data.model.ComicDetail
import app.example.comicbook.Data.model.ComicsBook
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface ComicsNetwork {

    @GET(".")
    fun getLastComics(): Call<ComicsBook>

    @GET(".")
    fun getDetailComic(): Call<ComicDetail>

    companion object {
        val api by lazy { create() }

        private fun create(): ComicsNetwork {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val originalHttpUrl = original.url()

                    val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", BuildConfig.API_KEY)
                        .addQueryParameter("format", "json")
                        .build()

                    val requestBuilder = original.newBuilder()
                        .url(url)

                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ComicsNetwork::class.java)
        }
    }
}