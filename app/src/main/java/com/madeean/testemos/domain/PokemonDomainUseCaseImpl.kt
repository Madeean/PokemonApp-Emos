package com.madeean.testemos.domain

import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.domain.model.PokemonModelPresentation
import com.madeean.testemos.presentation.util.RequestState
import kotlinx.coroutines.flow.Flow

class PokemonDomainUseCaseImpl(
  private val repository: PokemonDomainRepository
) : PokemonDomainUseCase {

  override fun getDetailPokemon(name: String): Flow<RequestState<DetailPokemonModelDomain>> {
    return repository.getDetailPokemon(name)
  }

  override fun getListPokemon(): Flow<RequestState<List<PokemonModelPresentation>>> {
    return repository.getListPokemon()
  }
}