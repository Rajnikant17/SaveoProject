package com.example.saveo.di

import com.example.saveo.repository.MoviesDataSource
import com.example.saveo.repository.MoviesRepository
import com.example.saveo.repository.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class MoviesModule {
    @Provides
    @Singleton
    fun provideRepository(moviesDataSource: MoviesDataSource): MoviesRepository {
        return MoviesRepositoryImpl(moviesDataSource)
    }
}