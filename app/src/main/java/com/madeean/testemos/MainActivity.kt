package com.madeean.testemos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.madeean.testemos.di.appModule
import com.madeean.testemos.presentation.navigation.SetupNavigation
import com.madeean.testemos.presentation.viewmodel.ViewModel
import org.koin.compose.currentKoinScope
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

  lateinit var navController: NavHostController
  override fun onCreate(savedInstanceState: Bundle?) {

    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      startKoin {
        modules(appModule)
      }

      val viewModel = koinViewModel<ViewModel>()
      navController = rememberNavController()
      SetupNavigation(
        viewModel = viewModel,
        navController = navController,
      )
    }
  }
}

@Composable
inline fun <reified T : androidx.lifecycle.ViewModel> koinViewModel(): T {
  val scope = currentKoinScope()
  return viewModel {
    scope.get<T>()
  }
}