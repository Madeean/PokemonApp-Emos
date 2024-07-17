package com.madeean.testemos.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.madeean.testemos.util.ConstantNavigator
import com.madeean.testemos.presentation.viewmodel.ViewModel
import com.madeean.testemos.presentation.navigation.directions.detailComposable
import com.madeean.testemos.presentation.navigation.directions.homeComposable

@Composable
fun SetupNavigation(
  navController: NavHostController,
  viewModel: ViewModel
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  NavHost(
    navController = navController,
    startDestination = ConstantNavigator.HOME_SCREEN,
  ) {
    homeComposable(viewModel, navigateToDetailScreen = screen.detail)
    detailComposable(viewModel, navController)
  }
}