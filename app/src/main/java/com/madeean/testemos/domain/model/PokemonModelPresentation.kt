package com.madeean.testemos.domain.model


data class PokemonModelPresentation(
  val name: String,
  val url: String,
  val sprites: DetailPokemonSpritesModelDomain,
  val height: Int,
  val weight: Int,
)
