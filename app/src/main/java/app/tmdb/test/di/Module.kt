package app.tmdb.test.di

import android.content.Context
import androidx.room.Room
import app.tmdb.test.data.LoginRepository
import app.tmdb.test.data.UserData
import app.tmdb.test.data.local.Tmdb
import app.tmdb.test.data.network.Api
import app.tmdb.test.data.network.LoginRemoteDataSource
import app.tmdb.test.data.network.interceptors.ApiKeyInterceptor
import app.tmdb.test.utils.PrefsManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun provideLoginRepository(remoteDataSource: LoginRemoteDataSource): LoginRepository = LoginRepository(remoteDataSource)

    @Provides
    fun provideLoginRemoteDataSource(api: Api, userData: UserData): LoginRemoteDataSource = LoginRemoteDataSource(api, userData)

    @Provides
    @Singleton
    fun provideUserData(prefsManager: PrefsManager): UserData = UserData(prefsManager)

    @Provides
    fun providePrefsManager(@ApplicationContext context: Context): PrefsManager {
        return PrefsManager(context)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Tmdb {
        return Room.databaseBuilder(context, Tmdb::class.java, "Tmdb").build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor)
            .build()
    }

    @Provides
    fun provideApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Api::class.java)
    }
}

private const val BASE_URL = "https://api.themoviedb.org/3/"