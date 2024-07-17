package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TestScrollableContentWithButton() {
    var numItems by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .background(Color.Yellow)
            .fillMaxHeight()// Add fill max size
            .verticalScroll(rememberScrollState())
    ) {
        ScrollableContent(numItems = numItems)

        // Spacer to fill up the available space
        Spacer(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { numItems++ }) { Text("Add Item") }
            Button(onClick = { numItems = 0 }) { Text("Clear Items") }
        }
    }
}

@Composable
fun ScrollableContent(modifier: Modifier = Modifier, numItems: Int) {
    repeat(numItems) {
        Text(modifier = Modifier.padding(20.dp), text = "Test Text $it")
    }
}