package com.tmobile.dynamicui.di.module

import com.google.gson.Gson
import com.tmobile.TMobileApp
import com.tmobile.dynamicui.BuildConfig
import com.tmobile.dynamicui.util.hasNetwork
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.time.ExperimentalTime
import kotlin.time.days


@Module
class NetworkModule {

    @ExperimentalTime
    @Provides
    fun provideOkHttpClient(
        app:TMobileApp,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val cacheSize = (5*1024*1024).toLong()
        val cache = Cache(app.applicationContext.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(cache)
            /**
             * TODO: can be added other interceptors like
             *  "Token refresh interceptor" - to detect token expiry and to automatic refresh of expired tokens
             *  "encrypt/decrypt interceptors" - to encrypt/decrypt request/responses
             */
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(app.applicationContext))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 10.days.inSeconds).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) BODY else NONE }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

}
