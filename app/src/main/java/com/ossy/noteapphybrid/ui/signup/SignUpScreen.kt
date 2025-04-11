package com.ossy.noteapphybrid.ui.signup


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.navigation.Screen

//import com.example.noteapphybrid.R

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SignUpScreen(navController: NavController) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//    var acceptTerms by remember { mutableStateOf(false) }
//
//    var passwordError by remember { mutableStateOf(false) }
//    var confirmPasswordError by remember { mutableStateOf(false) }
//    var acceptTermsError by remember { mutableStateOf(false) }
//
//    // State for showing/hiding passwords
//    var passwordVisible by remember { mutableStateOf(false) }
//    var confirmPasswordVisible by remember { mutableStateOf(false) }
//
//
//    val context = LocalContext.current
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "Sign Up", fontSize = 24.sp, style = MaterialTheme.typography.headlineMedium)
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(text = "Create New Account And backup Your notes", fontSize = 16.sp, style = MaterialTheme.typography.headlineMedium)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Email Field
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            label = { Text("Email") },
//            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Blue,
//                unfocusedBorderColor = Color.Gray
//            ),
//            singleLine = true
//        )
//        if (passwordError) {
//            Text(text = "Email required", color = Color.Red, fontSize = 12.sp)
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Password Field
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Password") },
//            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
////            visualTransformation = PasswordVisualTransformation(),
//            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                val icon = if (passwordVisible) R.drawable.passwordvisibleoff else R.drawable.passwordvisibleon
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        painter = painterResource(id = icon),
//                        contentDescription = "Toggle Password Visibility"
//                    )
//                }
//            },
//            singleLine = true,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Blue,
//                unfocusedBorderColor = Color.Gray
//            ),
//            isError = passwordError
//        )
//        if (passwordError) {
//            Text(text = "Password must be at least 6 characters", color = Color.Red, fontSize = 12.sp)
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Confirm Password Field
//        OutlinedTextField(
//            value = confirmPassword,
//            onValueChange = { confirmPassword = it },
//            label = { Text("Confirm Password") },
//            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
////            visualTransformation = PasswordVisualTransformation(),
//            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                val icon = if (passwordVisible) R.drawable.passwordvisibleoff else R.drawable.passwordvisibleon
//                IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                    Icon(
//                        painter = painterResource(id = icon),
//                        contentDescription = "Toggle Password Visibility"
//                    )
//                }
//            },
//            singleLine = true,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Blue,
//                unfocusedBorderColor = Color.Gray
//            ),
//            isError = confirmPasswordError
//        )
//        if (confirmPasswordError) {
//            Text(text = "Passwords do not match", color = Color.Red, fontSize = 12.sp)
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Terms and Conditions Checkbox
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Checkbox(
//                checked = acceptTerms,
//                onCheckedChange = { acceptTerms = it }
//            )
//            Text("I accept the Terms & Conditions", fontSize = 14.sp)
//        }
//        if (acceptTermsError) {
//            Text(text = "You must accept the Terms & Conditions", color = Color.Red, fontSize = 12.sp)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Sign Up Button
//        Button(
//            onClick = {
//                // Reset errors
//                val isEmailEmpty = email.isBlank()
//                val isPasswordEmpty = password.isBlank()
//                val isConfirmPasswordEmpty = confirmPassword.isBlank()
//
//                // Validation
//                passwordError = password.length < 6
//                confirmPasswordError = password != confirmPassword
//                acceptTermsError = !acceptTerms
//
//                if (isEmailEmpty || isPasswordEmpty || isConfirmPasswordEmpty) {
//                    Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
//                    return@Button
//                }
//
//                if (!passwordError && !confirmPasswordError && !acceptTermsError) {
//                    Toast.makeText(context, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
//                    navController.navigate("login")
//                }
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_color)),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Text("Sign Up")
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Login Link
//        TextButton(onClick = { navController.navigate("login") }) {
//            Text("Already have an account? Login", fontSize = 14.sp)
//        }
//    }
//}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptTerms by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var acceptTermsError by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray
        )

        Text(text = "Sign Up", fontSize = 24.sp, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Create a New Account and Backup Your Notes", fontSize = 16.sp, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            colors = colors,
            singleLine = true,
            isError = emailError
        )
        if (emailError) {
            Text(text = "Email is required", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val icon = if (passwordVisible) R.drawable.passwordvisibleoff else R.drawable.passwordvisibleon
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Toggle Password Visibility"
                    )
                }
            },
            singleLine = true,
            colors = colors,
            isError = passwordError
        )
        if (passwordError) {
            Text(text = "Password must be at least 6 characters", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Confirm Password Field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                val icon = if (confirmPasswordVisible) R.drawable.passwordvisibleoff else R.drawable.passwordvisibleon
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Toggle Confirm Password Visibility"
                    )
                }
            },
            singleLine = true,
            colors = colors,
            isError = confirmPasswordError
        )
        if (confirmPasswordError) {
            Text(text = "Passwords do not match", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Terms and Conditions Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = acceptTerms,
                onCheckedChange = { acceptTerms = it }
            )
            Text("I accept the Terms & Conditions", fontSize = 14.sp)
        }
        if (acceptTermsError) {
            Text(text = "You must accept the Terms & Conditions", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Button
        Button(
            onClick = {
                // Validation
                emailError = email.isBlank()
                passwordError = password.length < 6 || password.isBlank()
                confirmPasswordError = password != confirmPassword || confirmPassword.isBlank()
                acceptTermsError = !acceptTerms

                if (emailError || passwordError || confirmPasswordError || acceptTermsError) {
                    Toast.makeText(context, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Success
                Toast.makeText(context, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.main_color)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Login Link
        TextButton(onClick = {
            navController.navigate(Screen.Login.route)
        }) {
            Text("Already have an account? Login", fontSize = 14.sp)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}
