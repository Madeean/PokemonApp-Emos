package com.madeean.testemos.di

import com.madeean.testemos.data.network.ApiService
import com.madeean.testemos.data.network.httpClient
import com.madeean.testemos.data.repository.domainrepository.PokemonDomainRepositoryImpl
import com.madeean.testemos.domain.PokemonDomainRepository
import com.madeean.testemos.domain.PokemonDomainUseCase
import com.madeean.testemos.domain.PokemonDomainUseCaseImpl
import com.madeean.testemos.presentation.viewmodel.ViewModel
import org.koin.dsl.module

val appModule = module {
  single { httpClient }
  single { ApiService() }

  single { PokemonDomainRepositoryImpl(get(), get()) }
  single { PokemonDomainUseCaseImpl(get()) }
  single<PokemonDomainUseCase> { PokemonDomainUseCaseImpl(get()) }
  single<PokemonDomainRepository> { PokemonDomainRepositoryImpl(get(), get()) }

  factory { ViewModel(get()) }
}