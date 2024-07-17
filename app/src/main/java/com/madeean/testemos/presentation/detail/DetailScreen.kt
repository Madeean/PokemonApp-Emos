package com.madeean.testemos.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.madeean.testemos.domain.model.DetailPokemonModelDomain
import com.madeean.testemos.presentation.util.ErrorView
import com.madeean.testemos.presentation.util.LoaderShow
import com.madeean.testemos.presentation.util.RequestState.Error
import com.madeean.testemos.presentation.util.RequestState.Idle
import com.madeean.testemos.presentation.util.RequestState.Loading
import com.madeean.testemos.presentation.util.RequestState.Success
import com.madeean.testemos.presentation.viewmodel.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
  namePokemon: String,
  viewModel: ViewModel,
  navController: NavController
) {
  val detailPokemon by viewModel.detailPokemon.collectAsState()
  val scrollState = rememberScrollState()

  LaunchedEffect(key1 = true) {
    viewModel.getDetailPokemon(namePokemon)
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
            Text("Detail $namePokemon")
          },
          navigationIcon = {
            IconButton(
              onClick = {
                navController.navigateUp()
              }
            ) {
              Icon(imageVector = Filled.ArrowBack, contentDescription = null)
            }
          }
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

      when (detailPokemon) {
        is Idle -> {
          LoaderShow()
        }

        is Loading -> {
          LoaderShow()
        }

        is Success -> {
          val data = (detailPokemon as Success<DetailPokemonModelDomain>).data

          ContentDetailPokemonScreen(pokemon = data, namePokemon = namePokemon)
        }

        is Error -> {
          ErrorView(
            message = (detailPokemon as Error).error.message
              ?: "Halaman error silahkan cek koneksi anda dan coba lagi"
          ) {
            viewModel.getDetailPokemon(namePokemon)
          }
        }
      }

    }
  }
}

@Composable
fun ContentDetailPokemonScreen(pokemon: DetailPokemonModelDomain, namePokemon: String) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(12.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    AsyncImage(
      model = pokemon.sprites.frontDefault,
      contentDescription = null,
      modifier = Modifier.fillMaxWidth(),
      contentScale = ContentScale.Crop
    )
  }
  Spacer(modifier = Modifier.height(20.dp))
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier.padding(15.dp)
    ) {
      Text("Name", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text(namePokemon, fontSize = 20.sp, color = Color.Black)

      Text("Height", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${pokemon.height}", fontSize = 20.sp, color = Color.Black)

      Text("Weight", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
      Text("${pokemon.weight}", fontSize = 20.sp, color = Color.Black)
    }
  }
  Spacer(modifier = Modifier.height(20.dp))

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        "Types",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
      pokemon.types.forEach {item ->
        Text(
          "${item.slot} ${item.type.name}",
          modifier = Modifier.align(Alignment.Start),
          color = Color.Black,
          fontSize = 16.sp,
        )
      }
    }
  }

  Spacer(modifier = Modifier.height(20.dp))

  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        "Stats",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
      pokemon.stats.forEach {item ->
        Text(
          "${item.stat.name}: ${item.baseStat}",
          modifier = Modifier.align(Alignment.Start),
          color = Color.Black,
          fontSize = 16.sp,
        )
      }
    }
  }
  Spacer(modifier = Modifier.height(20.dp))
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color.White
    ),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        "Abilities",
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
      pokemon.abilities.forEachIndexed {index, item ->
        Text(
          "${index + 1}: ${item.ability.name} \nis hidden: ${item.isHidden}",
          modifier = Modifier.align(Alignment.Start),
          color = Color.Black,
          fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(6.dp))
      }
    }
  }
  Spacer(modifier = Modifier.height(20.dp))

}