package com.assessment.slotdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.assessment.slotdemo.ui.theme.SlotDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SlotDemo(Modifier.padding(innerPadding)) { ButtonDemo() } }
            }
        }
    }
}

@Composable
fun SlotDemo(modifier: Modifier = Modifier, middleContent: @Composable () -> Unit) {
    Column {
        Text("Top Text")
        middleContent()
        Text("Bottom Text")
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = { }) {
        Text("Click Me")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlotDemoTheme {
        SlotDemo() {ButtonDemo()}
    }
}