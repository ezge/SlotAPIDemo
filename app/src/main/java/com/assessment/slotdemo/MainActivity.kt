package com.assessment.slotdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assessment.slotdemo.ui.theme.SlotDemoTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }
    val onLinearClick = {value: Boolean -> linearSelected = value}
    val onTitleClick = {value: Boolean -> imageSelected = value}

    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onLinearClick = onLinearClick,
        onTitleClick = onTitleClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.baseline_cloud_download_24)
            } else {
                Text("Downloading",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(30.dp))
            }
        },
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(Modifier.size(200.dp),
                strokeWidth = 18.dp)
            }
        }
    )
}

@Composable
fun ScreenContent(linearSelected: Boolean, imageSelected: Boolean,
                  onLinearClick: (Boolean) -> Unit, onTitleClick: (Boolean) -> Unit,
                  titleContent: @Composable () -> Unit, progressContent: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.SpaceBetween){
        titleContent()
        progressContent()
        CheckBoxes(linearSelected, imageSelected,
                   onLinearClick, onTitleClick)

    }
}

@Composable
fun CheckBoxes(linearSelected: Boolean, imageSelected: Boolean,
               onLinearClick: (Boolean) -> Unit, onTitleClick: (Boolean) -> Unit) {

    Row(
        Modifier.padding(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = imageSelected, onCheckedChange = onTitleClick)
        Text("Image Title")
        Spacer(Modifier.width(20.dp))
        Checkbox(checked = linearSelected, onCheckedChange = onLinearClick)
        Text("Linear Progress")
    }
    
}

@Composable
fun TitleImage(drawing: Int){
    Image(painter = painterResource(id = drawing),
          contentDescription = "title image",
          modifier = Modifier.size(150.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DemoPreview() {
    MainScreen()
}