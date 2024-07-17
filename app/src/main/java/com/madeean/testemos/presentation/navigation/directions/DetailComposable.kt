package com.madeean.testemos.presentation.navigation.directions

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.madeean.testemos.util.ConstantNavigator
import com.madeean.testemos.presentation.detail.DetailScreen
import com.madeean.testemos.presentation.viewmodel.ViewModel

fun NavGraphBuilder.detailComposable(
  viewModel: ViewModel,
  navController: NavController
) {
  composable(
    route = ConstantNavigator.DETAIL_SCREEN,
    arguments = listOf(navArgument(ConstantNavigator.DETAIL_SCREEN_ARGUMENT_KEY) {
      type = NavType.StringType
    })
  ) {
    val detailUrl = it.arguments?.getString(ConstantNavigator.DETAIL_SCREEN_ARGUMENT_KEY) ?: ""
    DetailScreen(detailUrl, viewModel, navController)
  }
}