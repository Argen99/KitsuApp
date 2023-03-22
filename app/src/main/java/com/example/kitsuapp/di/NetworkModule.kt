package com.example.kitsuapp.di

import com.example.data.core.utils.AuthInterceptor
import com.example.data.local.prefs.TokenManager
import com.example.data.remote.api_service.*
import com.example.domain.repository.UserRepository
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

    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    factory { provideAnimeApi(get<Retrofit>()) }
    factory { provideAuthApi(get<Retrofit>()) }
    factory { provideMangaApi(get<Retrofit>()) }
    factory { providePostApi(get<Retrofit>()) }
    factory { provideUserApi(get<Retrofit>()) }

    single<MainApiService> {
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
            .create(MainApiService::class.java)
    }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}

fun provideAnimeApi(retrofit: Retrofit): AnimeApiService {
    return retrofit.create(AnimeApiService::class.java)
}

fun provideAuthApi(retrofit: Retrofit): AuthApiService {
    return retrofit.create(AuthApiService::class.java)
}

fun provideMangaApi(retrofit: Retrofit): MangaApiService {
    return retrofit.create(MangaApiService::class.java)
}

fun providePostApi(retrofit: Retrofit): PostApiService {
    return retrofit.create(PostApiService::class.java)
}

fun provideUserApi(retrofit: Retrofit): UserApiService {
    return retrofit.create(UserApiService::class.java)
}

