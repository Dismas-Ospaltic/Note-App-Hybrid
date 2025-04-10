package com.ossy.noteapphybrid.ui.newuser

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R

@Composable
fun NewUserScreen(navController: NavController) {
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
                onClick = { navController.navigate("login") },
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
                onClick = { continueWithGoogle() },
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.shadeBlue)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp) // Add padding for better layout
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue with Google")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Facebook Sign-In Button
            Button(
                onClick = { /* Facebook Login */ },
                shape = RoundedCornerShape(2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal_700)),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp) // Add padding for better layout

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.facebook1),
                    contentDescription = "Facebook Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue With Facebook")
            }
        }
    }
}

// Dummy Functions (Replace with actual logic)
fun continueWithGoogle() {
    // Google Sign-in Logic
}

@Preview(showBackground = true)
@Composable
private fun NewUserScreenPreview() {
    NewUserScreen(navController = rememberNavController())
}