

package com.ossy.noteapphybrid

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.*
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.ossy.noteapphybrid.data.datastore.UserPreferencesManager
//import com.ossy.noteapphybrid.ui.login.LoginScreen
//import com.ossy.noteapphybrid.ui.mainscreen.MainScreen
//import com.ossy.noteapphybrid.ui.newuser.NewUserScreen
//import com.ossy.noteapphybrid.ui.onboarding.OnboardingScreen
//import com.ossy.noteapphybrid.ui.signup.SignUpScreen
//import com.ossy.noteapphybrid.ui.splash.SplashScreen
//import com.ossy.noteapphybrid.viewmodel.MainViewModel
//import com.ossy.noteapphybrid.viewmodel.NoteViewModel
//import org.koin.android.ext.android.inject
//import org.koin.androidx.compose.getViewModel
//
//class MainActivity : ComponentActivity() {
//
//    private val userPreferences: UserPreferencesManager by inject() // Inject UserPreferencesManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            val mainViewModel: MainViewModel = getViewModel()
//            val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)
//
//            // Observe user login state
//            val userData by userPreferences.userData.collectAsState(initial = null)
//            val isLoggedIn = userData?.authToken?.isNotEmpty() == true // ✅ Check if user is logged in
//
//            val navController = rememberNavController()
//
//            NavHost(navController = navController, startDestination = "splash") {
//                composable("splash") {
//                    SplashScreen( // ✅ Pass the onNavigate function
//                        onNavigate = {
//                            when {
//                                isLoggedIn -> navController.navigate("main") {
//                                    popUpTo("splash") { inclusive = true }
//                                }
//                                isOnboardingCompleted -> navController.navigate("newuser") {
//                                    popUpTo("splash") { inclusive = true }
//                                }
//                                else -> navController.navigate("onboarding") {
//                                    popUpTo("splash") { inclusive = true }
//                                }
//                            }
//                        }
//                    )
//                }
//
//                composable("onboarding") {
//                    OnboardingScreen {
//                        mainViewModel.completeOnboarding()
//                        navController.navigate("newuser") {
//                            popUpTo("onboarding") { inclusive = true }
//                        }
//                    }
//                }
//
//                composable("newuser") {
//                    if (isLoggedIn) {
//                        // ✅ If logged in, redirect to main screen
//                        LaunchedEffect(Unit) {
//                            navController.navigate("main") {
//                                popUpTo("newuser") { inclusive = true }
//                            }
//                        }
//                    } else {
//                        NewUserScreen(navController)
//                    }
//                }
//
//                composable("login") { LoginScreen(navController) }
//                composable("signup") { SignUpScreen(navController) }
//
////                composable("main") {
////                    val noteViewModel: NoteViewModel = getViewModel()
////                    MainScreen(noteViewModel = noteViewModel, userPreferences = userPreferences)
////                }
//                composable("main") {
//                    val noteViewModel: NoteViewModel = getViewModel()
//                    MainScreen(noteViewModel = noteViewModel, userPreferences = userPreferences) // ✅ Correct type
//                }
//
//            }
//        }
//    }
//}


import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.compose.ui.res.painterResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ossy.noteapphybrid.navigation.AppNavHost
import com.ossy.noteapphybrid.navigation.Screen
import com.ossy.noteapphybrid.ui.components.HomeFAB

import java.util.Locale

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure full-screen layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberAnimatedNavController()

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Define screens where the bottom bar should be hidden
            val hideBottomBarScreens = listOf(Screen.AddNote.route, Screen.Login.route, Screen.SignUp.route, Screen.Splash.route, Screen.Onboarding.route, Screen.EditNoteDetail.route)

            Scaffold(
                bottomBar = {
//                    BottomNavigationBar(navController)
                    if (currentRoute !in hideBottomBarScreens) {
                        BottomNavigationBar(navController)
                    }
                },
//                floatingActionButton = {
//                    if (currentRoute == Screen.Home.route) { // Show FAB only on Home
//                        HomeFAB()
//                    }
//                }

            ) { paddingValues ->
                AppNavHost(navController, Modifier.padding(paddingValues))
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    val screens = listOf(Screen.Home, Screen.Account)

    val backgroundColor = colorResource(id = R.color.bottom_bar_background)
    val selectedColor = colorResource(id = R.color.tab_selected)
    val unselectedColor = colorResource(id = R.color.tab_unselected)
    val tabIndicatorColor = colorResource(id = R.color.tab_indicator)

    Column{
        // Top Divider
        HorizontalDivider(
            thickness = 1.dp, // Adjust thickness as needed
            color = colorResource(id = R.color.border_color)
        )
        NavigationBar(
            containerColor = backgroundColor
        ) {
            screens.forEach { screen ->
                val isSelected = currentDestination == screen.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentDestination != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(
                                id = when (screen) {
                                    is Screen.Home -> R.drawable.note_gray  // Home icon from res
                                    is Screen.Account -> R.drawable.user_gray // Account icon from res
                                    else -> R.drawable.note_gray   // Fallback icon
                                }
                            ),
                            contentDescription = screen.route,
                            tint = if (isSelected) selectedColor else unselectedColor // Apply custom colors
                        )
                    },
                    label = {
                        Text(
                            text = screen.route.replaceFirstChar { it.titlecase(Locale.ROOT) },
                            color = if (isSelected) selectedColor else unselectedColor // Apply custom colors
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        selectedTextColor = selectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = tabIndicatorColor // Change the background color of selected tab
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}

