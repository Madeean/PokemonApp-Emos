package com.madeean.testemos.presentation.navigation.directions

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.madeean.testemos.util.ConstantNavigator
import com.madeean.testemos.presentation.home.HomeScreen
import com.madeean.testemos.presentation.viewmodel.ViewModel

fun NavGraphBuilder.homeComposable(
  viewModel: ViewModel,
  navigateToDetailScreen: (String) -> Unit,
) {
  composable(
    route = ConstantNavigator.HOME_SCREEN,
  ) {
    HomeScreen(viewModel, navigateToDetailScreen)
  }
}