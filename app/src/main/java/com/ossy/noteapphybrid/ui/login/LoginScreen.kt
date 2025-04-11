package com.ossy.noteapphybrid.ui.login


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.navigation.Screen
import com.ossy.noteapphybrid.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val viewModel: AuthViewModel = koinViewModel()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray
        )

        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = colors,
            isError = emailError
        )
        if (emailError) {
            Text(text = "Email is required", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
            trailingIcon = {
                val icon = if (passwordVisible) R.drawable.passwordvisibleoff else R.drawable.passwordvisibleon
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(painter = painterResource(id = icon), contentDescription = "Toggle Password Visibility")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = colors,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = passwordError
        )
        if (passwordError) {
            Text(text = "Password is required", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                emailError = email.isBlank()
                passwordError = password.isBlank()

                if (emailError || passwordError) {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                isLoading = true  // Show loader

//                viewModel.login(email, password) { success, message, token, refreshToken, userEmail ->
//                    isLoading = false  // Hide loader after login attempt
//
//                    if (success) {
//                        Toast.makeText(context, "Login successful!${token}", Toast.LENGTH_SHORT).show()
//                        navController.navigate("main")
//                    } else {
//                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                    }
//                }
                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                navController.navigate("main")
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_color)),
            enabled = !isLoading  // Disable button while loading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.width(24.dp).height(24.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Please wait, logging in...")
            } else {
                Text("Login")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            TextButton(onClick = {
                navController.navigate(Screen.SignUp.route)
            }) {
                Text("Don't have an account? Sign Up", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            TextButton(onClick = { }) {
                Text("Forgot Password?", fontSize = 14.sp)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
