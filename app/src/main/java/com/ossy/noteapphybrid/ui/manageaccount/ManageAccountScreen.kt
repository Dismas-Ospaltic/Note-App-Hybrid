package com.ossy.noteapphybrid.ui.manageaccount


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Eye
import compose.icons.fontawesomeicons.solid.EyeSlash
import org.koin.androidx.compose.koinViewModel
import java.util.*

//@Composable
//fun ManageAccountScreen(navController: NavController) {
//    // Mutable states to store the input values.
//
//
//    var password by remember { mutableStateOf("") }
//    var confirmPassword by remember { mutableStateOf("") }
//
//
//    var passwordError by remember { mutableStateOf(false) }
//    var confirmPasswordError by remember { mutableStateOf(false) }
//
//    var passwordVisible by remember { mutableStateOf(false) }
//    var confirmPasswordVisible by remember { mutableStateOf(false) }
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Manage Account") },
//                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
//                navigationIcon = {
//                    IconButton(onClick = {
//                        // Use NavController to navigate back
//                        navController.popBackStack()
//                    }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
//                    }
//                },
//                backgroundColor = colorResource(id = R.color.white)
//
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "JaneDoe@gmail.com",
//                style = MaterialTheme.typography.h5,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//
//
//
//
//
//
//        }
//    }
//}

import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun ManageAccountScreen(navController: NavController) {
    var oldPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var oldPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val customButtonColor = colorResource(id = R.color.teal_700)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Account") },
                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = colorResource(id = R.color.white)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "JaneDoe@gmail.com",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Old Password
            OutlinedTextField(
                value = oldPassword,
                onValueChange = { oldPassword = it },
                label = { Text("Old Password") },
                visualTransformation = if (oldPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (oldPasswordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye
                    IconButton(onClick = { oldPasswordVisible = !oldPasswordVisible }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggle visibility",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )

            // New Password
            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (newPasswordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye
                    IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggle visibility",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (confirmPasswordVisible) FontAwesomeIcons.Solid.EyeSlash else FontAwesomeIcons.Solid.Eye
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Toggle visibility",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                modifier = Modifier.fillMaxWidth()
            )

            // Save Button
            Button(
                onClick = {
                    when {
                        oldPassword.isBlank() || newPassword.isBlank() || confirmPassword.isBlank() -> {
                            Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                        }
                        newPassword != confirmPassword -> {
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(context, "Password updated successfully", Toast.LENGTH_SHORT).show()
                            // Proceed with update logic here
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = customButtonColor)
            ) {
                Text("Save", color = Color.White)
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun ManageAccountScreenPreview() {
    ManageAccountScreen(navController = rememberNavController())
}

