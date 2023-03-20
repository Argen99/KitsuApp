package com.example.kitsuapp.di

import com.example.data.core.utils.AuthInterceptor
import com.example.data.local.prefs.TokenManager
import com.example.data.remote.api_service.ApiService
import com.example.kitsuapp.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { AuthInterceptor(get<TokenManager>()) }
    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient().newBuilder()
                    .addInterceptor(get<HttpLoggingInterceptor>())
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(get<AuthInterceptor>())
                    .build()
            )
            .build()
            .create(ApiService::class.java)
    }
}