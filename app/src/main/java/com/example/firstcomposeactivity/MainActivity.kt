package com.example.firstcomposeactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.firstcomposeactivity.ui.theme.FirstComposeActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp("You!", "How's going")
                }
            }
        }
    }
}

@Composable
fun MyApp(appName: String,appSubName: String) {
    Column() {
        Text(text = "Hey $appName!", fontSize = 20.sp, textAlign = TextAlign.Center)
        Text(text = "$appSubName?",fontSize = 10.sp, textAlign = TextAlign.Center)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstComposeActivityTheme {
        MyApp("You!", "How's going")
    }
}