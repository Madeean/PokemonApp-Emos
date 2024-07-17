package com.madeean.testemos.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.madeean.testemos.domain.model.PokemonModelPresentation
import com.madeean.testemos.presentation.util.ErrorView
import com.madeean.testemos.presentation.util.LoaderShow
import com.madeean.testemos.presentation.util.RequestState.Error
import com.madeean.testemos.presentation.util.RequestState.Idle
import com.madeean.testemos.presentation.util.RequestState.Loading
import com.madeean.testemos.presentation.util.RequestState.Success
import com.madeean.testemos.presentation.viewmodel.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  viewModel: ViewModel,
  navigateToDetailScreen: (String) -> Unit,
) {
  val listPokemon by viewModel.pokemon.collectAsState()
  val scrollState = rememberScrollState()

  LaunchedEffect(key1 = true) {
    viewModel.getListPokemon()
  }

  Scaffold(
    topBar = {
      Surface(
        shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp),
      ) {
        CenterAlignedTopAppBar(
          colors = TopAppBarColors(
            containerColor = Color.Gray,
            scrolledContainerColor = Color.Gray,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
          ),
          title = {
            Text("Home")
          },
        )
      }
    },
    modifier = Modifier.fillMaxSize()
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .padding(horizontal = 20.dp)
        .verticalScroll(scrollState),
    ) {

      when (listPokemon) {
        is Idle -> {
          LoaderShow()
        }

        is Loading -> {
          LoaderShow()
        }

        is Success -> {
          val dataList = (listPokemon as Success<List<PokemonModelPresentation>>).data

          Column(
            modifier = Modifier.fillMaxWidth()
          ) {
            dataList.forEach {
              ItemListPokemon(it, navigateToDetailScreen)
            }
          }
        }

        is Error -> {
          ErrorView(
            message = (listPokemon as Error).error.message
              ?: "Halaman error silahkan cek koneksi dan coba lagi"
          ) {
            viewModel.getListPokemon()
          }
        }
      }

    }
  }
}

@Composable
fun ItemListPokemon(
  pokemon: PokemonModelPresentation,
  navigateToDetailScreen: (String) -> Unit,
) {

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 5.dp),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(5.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    onClick = { navigateToDetailScreen(pokemon.name) }
  ) {
    Row(
      modifier = Modifier.padding(10.dp)
    ) {
      AsyncImage(
        model = pokemon.sprites.frontDefault,
        contentDescription = null,
        modifier = Modifier.size(120.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Column {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = pokemon.name, color = Color.Black,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Height: ${pokemon.height}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Weight: ${pokemon.weight}")
      }
    }
  }
}