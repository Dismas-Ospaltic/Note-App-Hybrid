

package com.example.noteapphybrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapphybrid.data.datastore.UserPreferencesManager
import com.example.noteapphybrid.ui.login.LoginScreen
import com.example.noteapphybrid.ui.mainscreen.MainScreen
import com.example.noteapphybrid.ui.newuser.NewUserScreen
import com.example.noteapphybrid.ui.onboarding.OnboardingScreen
import com.example.noteapphybrid.ui.signup.SignUpScreen
import com.example.noteapphybrid.ui.splash.SplashScreen
import com.example.noteapphybrid.viewmodel.MainViewModel
import com.example.noteapphybrid.viewmodel.NoteViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    private val userPreferences: UserPreferencesManager by inject() // Inject UserPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = getViewModel()
            val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)

            // Observe user login state
            val userData by userPreferences.userData.collectAsState(initial = null)
            val isLoggedIn = userData?.authToken?.isNotEmpty() == true // ✅ Check if user is logged in

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen( // ✅ Pass the onNavigate function
                        onNavigate = {
                            when {
                                isLoggedIn -> navController.navigate("main") {
                                    popUpTo("splash") { inclusive = true }
                                }
                                isOnboardingCompleted -> navController.navigate("newuser") {
                                    popUpTo("splash") { inclusive = true }
                                }
                                else -> navController.navigate("onboarding") {
                                    popUpTo("splash") { inclusive = true }
                                }
                            }
                        }
                    )
                }

                composable("onboarding") {
                    OnboardingScreen {
                        mainViewModel.completeOnboarding()
                        navController.navigate("newuser") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                }

                composable("newuser") {
                    if (isLoggedIn) {
                        // ✅ If logged in, redirect to main screen
                        LaunchedEffect(Unit) {
                            navController.navigate("main") {
                                popUpTo("newuser") { inclusive = true }
                            }
                        }
                    } else {
                        NewUserScreen(navController)
                    }
                }

                composable("login") { LoginScreen(navController) }
                composable("signup") { SignUpScreen(navController) }

//                composable("main") {
//                    val noteViewModel: NoteViewModel = getViewModel()
//                    MainScreen(noteViewModel = noteViewModel, userPreferences = userPreferences)
//                }
                composable("main") {
                    val noteViewModel: NoteViewModel = getViewModel()
                    MainScreen(noteViewModel = noteViewModel, userPreferences = userPreferences) // ✅ Correct type
                }

            }
        }
    }
}
