package com.ossy.noteapphybrid.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ossy.noteapphybrid.data.datastore.UserPreferencesManager
import com.ossy.noteapphybrid.ui.aboutapp.AboutAppScreen
import com.ossy.noteapphybrid.ui.account.AccountScreen
import com.ossy.noteapphybrid.ui.addnote.AddNoteScreen
import com.ossy.noteapphybrid.ui.editnote.EditNoteScreen
import com.ossy.noteapphybrid.ui.home.HomeScreen
import com.ossy.noteapphybrid.ui.login.LoginScreen
import com.ossy.noteapphybrid.ui.manageaccount.ManageAccountScreen
import com.ossy.noteapphybrid.ui.onboarding.OnboardingScreen
import com.ossy.noteapphybrid.ui.signup.SignUpScreen
import com.ossy.noteapphybrid.ui.splash.SplashScreen
import com.ossy.noteapphybrid.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Account : Screen("account")
    object AddNote : Screen("add_note")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object About : Screen("about")
    object ManageAccount : Screen("manage_account")


    object EditNoteDetail : Screen("edit_note/{noteId}") {
        fun createRoute(noteId: String) = "edit_note/$noteId"
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {
//
//      val context = LocalContext.current
//    val userPreferences = UserPreferencesManager(/* required args here */ context)
//    private val userPreferences: UserPreferencesManager by inject() // Inject UserPreferencesManager

//                // Observe user login state
//            val userData by userPreferences.userData.collectAsState(initial = null)
//            val isLoggedIn = userData?.authToken?.isNotEmpty() == true // ✅ Check if user is logged in


                val mainViewModel: MainViewModel = getViewModel()
            val isOnboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState(initial = false)

    AnimatedNavHost(
        navController,
        startDestination = Screen.Splash.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Home.route) {  HomeScreen(navController) }
        composable(Screen.Account.route) { AccountScreen(navController) }  //TO_DO fix later
        composable(Screen.AddNote.route) {  AddNoteScreen(navController) }
        composable(Screen.Login.route) {  LoginScreen(navController) }
        composable(Screen.SignUp.route) {  SignUpScreen(navController) }
        composable(Screen.About.route) {  AboutAppScreen(navController) }
        composable(Screen.ManageAccount.route) {  ManageAccountScreen(navController) }

        composable(Screen.EditNoteDetail.route) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: "Unknown"
            EditNoteScreen(navController, noteId)
        }

        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigate = {
                    when {
                        isOnboardingCompleted -> navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }

                        else -> navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }

                })//TO_DO fix later
        }

        composable(Screen.Onboarding.route) {  OnboardingScreen( onCompleteOnboarding = {
            mainViewModel.completeOnboarding()
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Onboarding.route) { inclusive = true }

            }
        }
        )
        }
        //TO_DO fix later

    }
}