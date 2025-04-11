package com.ossy.noteapphybrid.ui.aboutapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ossy.noteapphybrid.R
import com.ossy.noteapphybrid.model.NoteEntity
import com.ossy.noteapphybrid.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.*

@Composable
fun AboutAppScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About") },
                modifier = Modifier.padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
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
                .padding(16.dp)
        ) {
            Text(
                text = "About App",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "This is a simple notes app." +
                        " Your notes are backed up online when internet is available." +
                        "You can access your data anytime anywhere" +
                        " We do not collect or use your personal data.",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Privacy Policy Link
            Text(
                text = "Privacy Policy",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline),
                modifier = Modifier
                    .clickable {
                        val url = "https://kizymax.shop/privacy-policy/"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                    .padding(bottom = 16.dp)
            )

            // Website or other link
            Text(
                text = "Visit our website",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    val url = "https://yourdomain.com"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AboutAppScreenPreview() {
    AboutAppScreen(navController = rememberNavController())
}
