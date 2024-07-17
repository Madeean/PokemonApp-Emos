package com.madeean.testemos.data.repository.network.model

import com.madeean.testemos.domain.model.PokemonDetailModelDomain
import com.madeean.testemos.util.requireIfNull
import kotlinx.serialization.Serializable


@Serializable
data class PokemonModelResponse(
  val results: List<PokemonDetailModelResponse>?
)

@Serializable
data class PokemonDetailModelResponse(
  val name: String?,
  val url: String?,
) {
  companion object {
    fun transforms(models: List<PokemonDetailModelResponse>?): List<PokemonDetailModelDomain> {
      return models?.map {
        transform(
          PokemonDetailModelResponse(
            it.name.requireIfNull(),
            it.url.requireIfNull()
          )
        )
      }
        ?: listOf()
    }

    private fun transform(model: PokemonDetailModelResponse?): PokemonDetailModelDomain {
      return PokemonDetailModelDomain(
        name = model?.name.requireIfNull(),
        url = model?.url.requireIfNull()
      )
    }
  }
}
