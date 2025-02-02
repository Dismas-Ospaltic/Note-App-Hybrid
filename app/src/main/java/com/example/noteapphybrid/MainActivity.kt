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
import com.example.noteapphybrid.ui.login.LoginScreen
import com.example.noteapphybrid.ui.newuser.NewUserScreen
import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
import com.example.noteapphybrid.ui.signup.SignUpScreen
import com.example.noteapphybrid.ui.splash.SplashScreen
import com.example.noteapphybrid.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel


//@AndroidEntryPoint
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val viewModel: MainViewModel = getViewModel()
//            val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState(initial = false)
//
//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = "splash") {
//                composable("splash") {
//                    SplashScreen {
//                        if (isOnboardingCompleted) {
//                            navController.navigate("newuser") {
//                                popUpTo("splash") { inclusive = true }
//                            }
//                        } else {
//                            navController.navigate("onboarding") {
//                                popUpTo("splash") { inclusive = true }
//                            }
//                        }
//                    }
//                }
//                composable("onboarding") { OnboardingScreen { viewModel.completeOnboarding() } }
//                composable("home") { HomeScreen() }
//                composable("newuser") { NewUserScreen(navController) }
//            }
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
                        // After splash, navigate to either "newuser" or "onboarding" based on onboarding completion
                        if (isOnboardingCompleted) {
                            navController.navigate("newuser") {
                                popUpTo("splash") { inclusive = true }
                            }
                        } else {
                            navController.navigate("onboarding") {
                                popUpTo("splash") { inclusive = true }
                            }
                        }
                    }
                }

                composable("onboarding") {
                    OnboardingScreen {
                        // Complete onboarding when button is clicked
                        viewModel.completeOnboarding()
                        // Navigate to newuser after completing onboarding
                        navController.navigate("newuser") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                }

                composable("newuser") {
                    // Pass the navController to the NewUserScreen for navigation
                    NewUserScreen(navController)
                }

                composable("home") {
                    HomeScreen(navController = navController)
                }

                composable("login") {
                    LoginScreen(navController)
                }


                composable("signup") {
                   SignUpScreen(navController)
                }
            }
        }
    }
}


annotation class AndroidEntryPoint
