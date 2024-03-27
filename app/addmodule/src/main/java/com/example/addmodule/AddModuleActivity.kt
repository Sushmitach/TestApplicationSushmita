package com.example.addmodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.example.addmodule.ui.theme.TCSLoyadTestApplicationTheme

class AddModuleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TCSLoyadTestApplicationTheme {
                Alignment.CenterVertically
                ButtonAdd(Color.Magenta)
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@Composable
fun ContentButton() {
    Text(
        modifier = Modifier.testTag("TextAdd"),
        text = "Add"
    )
    Icon(
        modifier = Modifier.testTag("AddIcon"),
        imageVector = Icons.Default.Add,
        contentDescription = "AddIcon"
    )
}

@Composable
fun ButtonAdd(color: Color) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {},
            content = { ContentButton() },
            modifier = Modifier
                .testTag("ButtonAdd"),
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            )
        )
    }
}

