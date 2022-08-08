package com.example.proje.di

import com.example.proje.data.repo.YemeklerDaoRepository
import com.example.proje.retrofit.ApiUtils
import com.example.proje.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton

    fun provideYemeklerDaoRepository(ymkdao:YemeklerDao) : YemeklerDaoRepository{
        return YemeklerDaoRepository(ymkdao)
    }

    @Provides
    @Singleton

    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
}}