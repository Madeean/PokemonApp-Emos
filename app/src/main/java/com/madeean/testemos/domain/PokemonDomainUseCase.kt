package com.madeean.testemos.domain

import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.domain.model.PokemonModelPresentation
import com.madeean.testemos.presentation.util.RequestState
import kotlinx.coroutines.flow.Flow

interface PokemonDomainUseCase {
  fun getDetailPokemon(name: String): Flow<RequestState<DetailPokemonModelDomain>>

  fun getListPokemon(): Flow<RequestState<List<PokemonModelPresentation>>>
}