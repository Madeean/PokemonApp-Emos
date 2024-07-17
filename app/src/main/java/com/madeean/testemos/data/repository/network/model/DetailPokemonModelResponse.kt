package com.madeean.testemos.data.repository.network.model

import com.madeean.testemos.domain.model.DetailPokemonAbilitiesModelDomain
import com.madeean.testemos.domain.model.DetailPokemonItemAbilityModelDomain
import com.madeean.testemos.domain.model.DetailPokemonItemStatModelDomain
import com.madeean.testemos.domain.model.DetailPokemonItemTypeModelDomain
import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.domain.model.DetailPokemonSpeciesModelDomain
import com.madeean.testemos.domain.model.DetailPokemonSpritesModelDomain
import com.madeean.testemos.domain.model.DetailPokemonStatsModelDomain
import com.madeean.testemos.domain.model.DetailPokemonTypesModelDomain
import com.madeean.testemos.util.requireIfNull
import kotlinx.serialization.Serializable

@Serializable
data class DetailPokemonModelResponse(
  val height: Int?,
  val weight: Int?,
  val types: List<DetailPokemonTypesModelResponse>?,
  val stats: List<DetailPokemonStatsModelResponse>?,
  val sprites: DetailPokemonSpritesModelResponse?,
  val species: DetailPokemonSpeciesModelResponse?,
  val abilities: List<DetailPokemonAbilitiesModelResponse>?
) {
  companion object {
    fun transform(model: DetailPokemonModelResponse?): DetailPokemonModelDomain {
      return DetailPokemonModelDomain(
        height = model?.height.requireIfNull(),
        weight = model?.weight.requireIfNull(),
        species = DetailPokemonSpeciesModelDomain(
          name = model?.species?.name.requireIfNull(),
          url = model?.species?.url.requireIfNull()
        ),
        types = model?.types?.map {
          DetailPokemonTypesModelDomain(
            type = DetailPokemonItemTypeModelDomain(
              url = it.type?.url.requireIfNull(),
              name = it.type?.name.requireIfNull(),
            ),
            slot = it.slot.requireIfNull()
          )
        } ?: listOf(),
        stats = model?.stats?.map {
          DetailPokemonStatsModelDomain(
            stat = DetailPokemonItemStatModelDomain(
              url = it.stat?.url.requireIfNull(),
              name = it.stat?.name.requireIfNull()
            ),
            baseStat = it.base_stat.requireIfNull(),
            effort = it.effort.requireIfNull()
          )
        } ?: listOf(),
        sprites = DetailPokemonSpritesModelDomain(
          frontDefault = model?.sprites?.front_default.requireIfNull()
        ),
        abilities = model?.abilities?.map {
          DetailPokemonAbilitiesModelDomain(
            ability = DetailPokemonItemAbilityModelDomain(
              name = it.ability?.name.requireIfNull(),
              url = it.ability?.url.requireIfNull()
            ),
            isHidden = it.is_hidden.requireIfNull(),
            slot = it.slot.requireIfNull()
          )
        } ?: listOf()
      )
    }
  }
}

@Serializable
data class DetailPokemonAbilitiesModelResponse(
  val ability: DetailPokemonItemAbilityModelResponse?,
  val is_hidden: Boolean?,
  val slot: Int?
)

@Serializable
data class DetailPokemonItemAbilityModelResponse(
  val name: String?,
  val url: String?,
)

@Serializable
data class DetailPokemonSpeciesModelResponse(
  val name: String?,
  val url: String?
)

@Serializable
data class DetailPokemonSpritesModelResponse(
  val front_default: String?,
)

@Serializable
data class DetailPokemonStatsModelResponse(
  val base_stat: Int?,
  val effort: Int?,
  val stat: DetailPokemonItemStatModelResponse?
)

@Serializable
data class DetailPokemonItemStatModelResponse(
  val name: String?,
  val url: String?
)

@Serializable
data class DetailPokemonTypesModelResponse(
  val slot: Int?,
  val type: DetailPokemonItemTypeModelResponse?
)

@Serializable
data class DetailPokemonItemTypeModelResponse(
  val name: String?,
  val url: String?,
)
