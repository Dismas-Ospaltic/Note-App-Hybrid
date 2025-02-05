//package com.example.noteapphybrid
//
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.noteapphybrid.ui.login.LoginScreen
//import com.example.noteapphybrid.ui.mainscreen.MainScreen
//import com.example.noteapphybrid.ui.newuser.NewUserScreen
//import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
//import com.example.noteapphybrid.ui.signup.SignUpScreen
//import com.example.noteapphybrid.ui.splash.SplashScreen
//import com.example.noteapphybrid.viewmodel.MainViewModel
//import org.koin.androidx.compose.getViewModel
//
//
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val viewModel: MainViewModel = getViewModel()
//
//            val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState(initial = false)
//
//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = "splash") {
//                composable("splash") {
//                    SplashScreen {
//                        // After splash, navigate to either "newuser" or "onboarding" based on onboarding completion
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
//
//                composable("onboarding") {
//                    OnboardingScreen {
//                        // Complete onboarding when button is clicked
//                        viewModel.completeOnboarding()
//                        // Navigate to newuser after completing onboarding
//                        navController.navigate("newuser") {
//                            popUpTo("onboarding") { inclusive = true }
//                        }
//                    }
//                }
//
//                composable("newuser") {
//                    // Pass the navController to the NewUserScreen for navigation
//                    NewUserScreen(navController)
//                }
//                composable("login") {
//                    LoginScreen(navController)
//                }
//
//
//                composable("signup") {
//                    SignUpScreen(navController)
//                }
//
//                // Wrap Home, ToDo, and Account inside MainScreen
//                composable("main") {
//                    MainScreen()
//                }
//
//            }
//        }
//    }
//}
//
//

//package com.example.noteapphybrid
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.noteapphybrid.data.local.AppDatabase
//import com.example.noteapphybrid.repository.NoteRepository
//import com.example.noteapphybrid.ui.login.LoginScreen
//import com.example.noteapphybrid.ui.mainscreen.MainScreen
//import com.example.noteapphybrid.ui.newuser.NewUserScreen
//import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
//import com.example.noteapphybrid.ui.signup.SignUpScreen
//import com.example.noteapphybrid.ui.splash.SplashScreen
//import com.example.noteapphybrid.viewmodel.MainViewModel
//import com.example.noteapphybrid.viewmodel.NoteViewModel
//import org.koin.androidx.compose.getViewModel
//
//class MainActivity : ComponentActivity() {
//
//    private lateinit var noteRepository: NoteRepository
//    private lateinit var noteViewModel: NoteViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val noteDao = AppDatabase.getDatabase(applicationContext).noteDao()
//        noteRepository = NoteRepository(noteDao)
//        noteViewModel = NoteViewModel(noteRepository)
//
//        setContent {
//            val viewModel: MainViewModel = getViewModel()
//
//            val isOnboardingCompleted by viewModel.isOnboardingCompleted.collectAsState(initial = false)
//
//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = "splash") {
//                composable("splash") {
//                    SplashScreen {
//                        // After splash, navigate to either "newuser" or "onboarding" based on onboarding completion
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
//
//                composable("onboarding") {
//                    OnboardingScreen {
//                        // Complete onboarding when button is clicked
//                        viewModel.completeOnboarding()
//                        // Navigate to newuser after completing onboarding
//                        navController.navigate("newuser") {
//                            popUpTo("onboarding") { inclusive = true }
//                        }
//                    }
//                }
//
//                composable("newuser") {
//                    // Pass the navController to the NewUserScreen for navigation
//                    NewUserScreen(navController)
//                }
//                composable("login") {
//                    LoginScreen(navController)
//                }
//
//                composable("signup") {
//                    SignUpScreen(navController)
//                }
//
//                // Wrap Home, ToDo, and Account inside MainScreen
//                composable("main") {
//                    MainScreen(noteViewModel = noteViewModel)
//                }
//            }
//        }
//    }
//}

package com.example.noteapphybrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapphybrid.ui.login.LoginScreen
import com.example.noteapphybrid.ui.mainscreen.MainScreen
import com.example.noteapphybrid.ui.newuser.NewUserScreen
import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
import com.example.noteapphybrid.ui.signup.SignUpScreen
import com.example.noteapphybrid.ui.splash.SplashScreen
import com.example.noteapphybrid.viewmodel.MainViewModel
import com.example.noteapphybrid.viewmodel.NoteViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = getViewModel()

            val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)

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
                        mainViewModel.completeOnboarding()
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
                composable("login") {
                    LoginScreen(navController)
                }

                composable("signup") {
                    SignUpScreen(navController)
                }

                // Use Koin to get the NoteViewModel and pass it to MainScreen
                composable("main") {
                    val noteViewModel: NoteViewModel = getViewModel()
                    MainScreen(noteViewModel = noteViewModel)
                }
            }
        }
    }
}

