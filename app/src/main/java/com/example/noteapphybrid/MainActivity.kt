package com.example.noteapphybrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapphybrid.ui.home.HomeScreen
import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
import com.example.noteapphybrid.ui.splash.SplashScreen
import com.example.noteapphybrid.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AppNavigation()
//        }
//    }
//}
//
//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    val viewModel: MainViewModel = getViewModel()
//    // Koin ViewModel injection
//    val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState(initial = false)
//
//
//    NavHost(navController = navController, startDestination = "splash") {
//        composable("splash") {
//            SplashScreen {
//                // Navigate based on onboarding completion status
//                if (isOnboardingCompleted) {
//                    navController.navigate("home") {
//                        popUpTo("splash") { inclusive = true }
//                    }
//                } else {
//                    navController.navigate("onboarding") {
//                        popUpTo("splash") { inclusive = true }
//                    }
//                }
//            }
//        }
//        composable("onboarding") {
//            OnboardingScreen {
//                viewModel.completeOnboarding()
//                navController.navigate("home") {
//                    popUpTo("onboarding") { inclusive = true }
//                }
//            }
//        }
//        composable("home") {
//            HomeScreen()
//        }
//    }
//}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = getViewModel()
            val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState(initial = false)

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen {
                        if (isOnboardingCompleted) {
                            navController.navigate("home") {
                                popUpTo("splash") { inclusive = true }
                            }
                        } else {
                            navController.navigate("onboarding") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }
                }
                composable("onboarding") { OnboardingScreen { viewModel.completeOnboarding() } }
                composable("home") { HomeScreen() }
            }
        }
    }
}

annotation class AndroidEntryPoint
