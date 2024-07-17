package com.madeean.testemos.domain.model


data class PokemonModelDomain(
  val results: List<PokemonDetailModelDomain>
)

data class PokemonDetailModelDomain(
  val name: String,
  val url: String,
)
