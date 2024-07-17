package com.madeean.testemos.domain.model

data class DetailPokemonModelDomain(
  val height: Int,
  val weight: Int,
  val types: List<DetailPokemonTypesModelDomain>,
  val stats: List<DetailPokemonStatsModelDomain>,
  val sprites: DetailPokemonSpritesModelDomain,
  val species: DetailPokemonSpeciesModelDomain,
  val abilities: List<DetailPokemonAbilitiesModelDomain>
)

data class DetailPokemonAbilitiesModelDomain(
  val ability: DetailPokemonItemAbilityModelDomain,
  val isHidden: Boolean,
  val slot: Int
)

data class DetailPokemonItemAbilityModelDomain(
  val name: String,
  val url: String,
)

data class DetailPokemonSpeciesModelDomain(
  val name: String,
  val url: String
)

data class DetailPokemonSpritesModelDomain(
  val frontDefault: String,
)

data class DetailPokemonStatsModelDomain(
  val baseStat: Int,
  val effort: Int,
  val stat: DetailPokemonItemStatModelDomain
)

data class DetailPokemonItemStatModelDomain(
  val name: String,
  val url: String
)

data class DetailPokemonTypesModelDomain(
  val slot: Int,
  val type: DetailPokemonItemTypeModelDomain
)

data class DetailPokemonItemTypeModelDomain(
  val name: String,
  val url: String,
)
