package com.example.premireappcompose

import android.content.Context
import androidx.room.Room
import com.example.premireappcompose.DAO.FilmDao
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerConverters() : Converters {
        val moshi = Moshi.Builder().build()
        // remplacer ici Converters, par le nom que vous avez donné à votre classe
        // convertisseur de types.
        return Converters(moshi)
    }

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context)
            : FilmDao {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        )
            .addTypeConverter(Converters(Moshi.Builder().build()))
            .fallbackToDestructiveMigration()
            .build().filmDao()
    }

    @Singleton
    @Provides
    fun provideTmdbApi() : TmdbAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TmdbAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: TmdbAPI,db: FilmDao): Repository {
        return Repository(api, db)
    }
}