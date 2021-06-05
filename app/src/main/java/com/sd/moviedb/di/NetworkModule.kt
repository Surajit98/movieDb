package com.sd.moviedb.di

import android.app.Application
import com.sd.moviedb.data.ApiService
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SECS = 30
private const val GET_BASE_URL1 = "https://jsonplaceholder.typicode.com//"


val networkModule = module {


    //single { provideHttpClient() }
    // single { provideRetrofit(get(), get()) }


    fun provideApiService(
        okHttpClient: OkHttpClient?,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory?,
        gson: Gson?
    ): ApiService? {
        return Retrofit.Builder()
            .baseUrl(GET_BASE_URL1)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }


    fun provideCookieManager(): CookieManager? {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        CookieHandler.setDefault(cookieManager)
        return cookieManager
    }


    fun provideOkHttpClient(
        cookieJar: CookieJar,
        loggingInterceptor: HttpLoggingInterceptor,
        headerAuthorizationInterceptor: Interceptor,
        cache: Cache?
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(headerAuthorizationInterceptor)
        httpClient.connectTimeout(
            TIMEOUT_IN_SECS.toLong(),
            TimeUnit.SECONDS
        )
        httpClient.readTimeout(
            TIMEOUT_IN_SECS.toLong(),
            TimeUnit.SECONDS
        )
        httpClient.cookieJar(cookieJar)
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
        val authToken = Credentials.basic("Letstrack", "LTS@NowInIndia")
        return Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            //Headers headers = request.headers().newBuilder().add("Authorization", "Bearer " + token).build();
            val requestBuilder = request.newBuilder()
                .addHeader("Authorization", "Bearer " + "token")// preference.geToken())
            if (request.url.toString().contains("default")) {
                requestBuilder.url(
                    request.url.toString().replace(
                        "http://default.com/",
                        "base_url"
                    )
                )
            }
            //.addHeader("Authorization", authToken);
            request = requestBuilder.build()
            val response = chain.proceed(request)
            if (response.code == 401) {
               // return response
            }
            response
        }
    }

    fun provideGson(): Gson? {
        return GsonBuilder() /* .registerTypeAdapterFactory(tvMazeTypeAdaptorFactory)*/
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()
    }


    fun provideCookieJar(context: Application?): CookieJar? {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
    }


    fun provideCache(context: Application): Cache? {
        val cacheSize = 5 * 1024 * 1024 // 5 MB
        val cacheDir = context.cacheDir
        return Cache(cacheDir, cacheSize.toLong())
    }


    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory? {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }


    single { provideRxJavaCallAdapterFactory() }
    single { provideCache(get()) }
    single { provideCookieJar(get()) }
    single { provideGson() }
    single { provideHeaderAuthorizationInterceptor() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get(), get(), get(), get()) }
    single { provideApiService(get(), get(), get()) }

}


