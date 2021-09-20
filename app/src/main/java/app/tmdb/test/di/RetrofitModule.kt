package app.tmdb.test.di

import app.tmdb.test.data.network.Api
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object RetrofitModule {

    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Api::class.java)
    }

    private const val BASE_URL = "https://api.themoviedb.org/3/"
}