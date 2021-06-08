package com.sd.moviedb.di

import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sd.moviedb.BuildConfig
import com.sd.moviedb.data.network.ApiService
import com.sd.moviedb.utils.NetworkConnectionInterceptor
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val TIMEOUT_IN_SECS = 30
const val API_KEY = "69ede9438e11cc0455b25944b5994f80"
const val API_ENDPOINT = "https://api.themoviedb.org/3/"
const val PARAM_API_KEY = "api_key"


val networkModule = module {

    fun provideApiService(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): ApiService? {
        return Retrofit.Builder()
            .baseUrl(API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }


    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerAuthorizationInterceptor: Interceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        cache: Cache?
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(headerAuthorizationInterceptor)
        httpClient.addInterceptor(networkConnectionInterceptor)
        httpClient.connectTimeout(
            TIMEOUT_IN_SECS.toLong(),
            TimeUnit.SECONDS
        )
        httpClient.readTimeout(
            TIMEOUT_IN_SECS.toLong(),
            TimeUnit.SECONDS
        )
        httpClient.cache(cache)
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(loggingInterceptor)
        }
        return httpClient.build()
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor? {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    fun provideHeaderAuthorizationInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter(PARAM_API_KEY, API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    fun provideNetworkInterceptor(context: Context): Interceptor {
        return NetworkConnectionInterceptor(context)

    }

    fun provideGson(): Gson? {
        return GsonBuilder() /* .registerTypeAdapterFactory(tvMazeTypeAdaptorFactory)*/
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()
    }


    fun provideCache(context: Application): Cache {
        val cacheSize = 5 * 1024 * 1024 // 5 MB
        val cacheDir = context.cacheDir
        return Cache(cacheDir, cacheSize.toLong())
    }




    single { provideCache(get()) }
    single { provideGson() }
    single { provideHeaderAuthorizationInterceptor() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get(), get(), get(named("internet")), get()) }
    single { provideApiService(get(), get()) }
    single(named("internet")) { provideNetworkInterceptor(get()) }

}


