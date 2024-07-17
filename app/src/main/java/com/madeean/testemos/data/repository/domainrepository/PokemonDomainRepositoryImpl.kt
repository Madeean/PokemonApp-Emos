package com.madeean.testemos.data.repository.domainrepository

import com.madeean.testemos.data.network.ApiService
import com.madeean.testemos.data.repository.datastore.PokemonDataStore
import com.madeean.testemos.domain.PokemonDomainRepository
import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.domain.model.PokemonModelPresentation
import com.madeean.testemos.presentation.util.RequestState
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonDomainRepositoryImpl(
  httpClient: HttpClient,
  apiService: ApiService
) : PokemonDomainRepository {
  private val dataStore = PokemonDataStore(httpClient, apiService)

  override fun getDetailPokemon(name: String): Flow<RequestState<DetailPokemonModelDomain>> {
    return flow {
      emit(RequestState.Loading)
      val data = dataStore.getDetailPokemonByName(name)
      emit(data)
    }
  }

  override fun getListPokemon(): Flow<RequestState<List<PokemonModelPresentation>>> {
    return flow {
      emit(RequestState.Loading)
      val data = dataStore.getListPokemonWithImage()
      emit(data)
    }
  }

}