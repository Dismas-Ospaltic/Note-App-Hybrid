
package com.ossy.noteapphybrid.ui.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.data.datastore.UserData
import com.ossy.noteapphybrid.data.datastore.UserPreferencesManager
import com.ossy.noteapphybrid.navigation.Screen
import com.ossy.noteapphybrid.ui.newuser.continueWithGoogle
import com.ossy.noteapphybrid.utils.DynamicStatusBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController) {


    val userPreferences = UserPreferencesManager(navController.context)
    val backgroundColor = colorResource(id = R.color.white)
    DynamicStatusBar(backgroundColor)

//    val coroutineScope = rememberCoroutineScope() // ✅ Correct way to create a coroutine scope
//    // Collect user email from DataStore
//    val userData by userPreferences.userData.collectAsState(initial = UserData("", "", "", false))

    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = { androidx.compose.material.Text("Account") },
                backgroundColor = colorResource(id = R.color.white),
                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                    bottom = 110.dp
                )
//                .padding(bottom = paddingValues)
                .verticalScroll(rememberScrollState()) // ✅ Enable scrolling
        ) {
            // Top Section with Background and Profile Picture
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(colorResource(id = R.color.light_bg_color)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.user), // Profile Picture
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
//                        text = if (userData.isLoggedIn) userData.userEmail else "Log In / Register",
                        text = "Log In / Register",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Choose to Continue",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Login Button
                    Button(
                        onClick = {
                            navController.navigate(Screen.Login.route)
                        },
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkLight)),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp) // Add padding for better layout
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Login")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Google Sign-In Button
                    Button(
                        onClick = {
                            navController.navigate(Screen.SignUp.route)
                        },
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.shadeBlue)),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp) // Add padding for better layout
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.adduser),
                            contentDescription = "Google Icon",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("SignUp")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                }
            }

            // Menu Items with Vector Assets from res/drawable
            AccountMenuItem(
                iconRes = R.drawable.ic_person,  // Personal Info Icon
                text = "Personal Info",
                onClick = { /* Navigate to Personal Info */
                    navController.navigate(Screen.ManageAccount.route)
                }
            )
//            AccountMenuItem(
//                iconRes = R.drawable.ic_notifications,  // Notifications Icon
//                text = "Notifications",
//                onClick = { /* Navigate to Notifications */ }
//            )
            AccountMenuItem(
                iconRes = R.drawable.ic_info,  // About Icon
                text = "About",
                onClick = { /* Navigate to About */
                    navController.navigate(Screen.About.route)
                }
            )
//            AccountMenuItem(
//                iconRes = R.drawable.ic_theme,  // Theme Preferences Icon
//                text = "Theme Preferences",
//                onClick = { /* Open Theme Settings */ }
//            )
//            Spacer(modifier = Modifier.weight(1f)) // Pushes the logout button to the bottom

            // Logout Button
            Button(
                onClick = {
//                    coroutineScope.launch {
//                        userPreferences.clearUserData() // ✅ Clear user session data
//
//                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Logout", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }


        }
    }
}

@Composable
fun AccountMenuItem(iconRes: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
    }
}
