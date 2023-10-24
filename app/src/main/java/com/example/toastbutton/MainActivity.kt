package com.example.toastbutton

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.toastbutton.ui.theme.ToastButtonTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.Constants.TAG
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt, token)
            Log.d(TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
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
        .background(color = Color(0xFFF6BBFA)),
    )
    {
        ButterButton()

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.bread),
                contentDescription = "bread",
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(0f),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun ButterButton() {
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                // triggers the toast to appear when tapping the button
                Toast.makeText(context, "Your toast has been buttered.", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier
                .padding(20.dp)
                .shadow(20.dp, shape = CircleShape) // Add a shadow with a 4dp elevation
                .background(Color(0xFFFFEBAD)), // Set the background color
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFEBAD)
            )
        ) {
            Text(
                text = "BUTTER",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BreadPreview() {
    ToastButtonTheme {
        AppContent()
    }
}