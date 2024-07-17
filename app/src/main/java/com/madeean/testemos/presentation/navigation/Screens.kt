package com.madeean.testemos.presentation.navigation

import androidx.navigation.NavHostController

class Screens(navController: NavHostController) {

  val detail: (String) -> Unit = {
    navController.navigate(route = "detail_screen/${it}")
  }
}