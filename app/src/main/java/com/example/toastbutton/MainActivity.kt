package com.example.toastbutton

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toastbutton.ui.theme.ToastButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToastButtonTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF272727)),
    )
    {
        Image(
            painter = painterResource(id = R.drawable.martin_latal_9iv7pk8devo_unsplash),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.0f, 1.0f)
                .alpha(0.6f)
        )

        KnightsButton()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 250.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.knights_of_ni),
                contentDescription = "The Knights of Ni",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun KnightsButton() {
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // triggers the toast to appear when tapping the button
                Toast.makeText(context, "NI!", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom = 120.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D6487)
            )
        ) {
            Text(text = "We are the knights who say...",
                fontSize = 20.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun KnightsPreview() {
    ToastButtonTheme {
        AppContent()
    }
}