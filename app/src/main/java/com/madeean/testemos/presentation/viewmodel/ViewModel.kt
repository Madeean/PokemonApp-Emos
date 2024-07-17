package com.madeean.testemos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeean.testemos.domain.PokemonDomainUseCase
import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.domain.model.PokemonModelPresentation
import com.madeean.testemos.presentation.util.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ViewModel(private val useCase: PokemonDomainUseCase) : ViewModel() {

  private var _listPokemon: MutableStateFlow<RequestState<List<PokemonModelPresentation>>> =
    MutableStateFlow(RequestState.Idle)
  val pokemon: StateFlow<RequestState<List<PokemonModelPresentation>>> = _listPokemon

  private var _detailPokemon: MutableStateFlow<RequestState<DetailPokemonModelDomain>> =
    MutableStateFlow(RequestState.Idle)
  val detailPokemon: StateFlow<RequestState<DetailPokemonModelDomain>> = _detailPokemon

  fun getListPokemon() {
    viewModelScope.launch {
      useCase.getListPokemon().collectLatest {
        _listPokemon.value = it
      }
    }
  }

  fun getDetailPokemon(name: String) {
    viewModelScope.launch {
      useCase.getDetailPokemon(name).collectLatest {
        _detailPokemon.value = it
      }
    }
  }

}