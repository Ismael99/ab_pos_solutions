package com.isma.absolutions.di

import com.isma.absolutions.core.UtilsOrder
import com.isma.absolutions.data.network.OrderApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Alcance de la instancia, a nivel de aplicacion
object NetworkModule {
    @Singleton //Para manejar la misma instancia
    @Provides
    fun provideRetrofit(): Retrofit {
        //Creacion del objeto retrofit con el url base
        return Retrofit.Builder()
            .baseUrl("https://dev-graphql.azurewebsites.net/api/test/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiOrdersClient(retrofit: Retrofit): OrderApiClient {
        return retrofit.create(OrderApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideUtilOrder(): UtilsOrder {
        return UtilsOrder
    }
}