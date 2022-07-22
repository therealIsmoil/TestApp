package uz.gita.testapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.testapp.domain.usecase.MainScreenUseCase
import uz.gita.testapp.domain.usecase.impl.MainScreenUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @[Binds Singleton]
    fun bindsUseCase(impl: MainScreenUseCaseImpl): MainScreenUseCase
}