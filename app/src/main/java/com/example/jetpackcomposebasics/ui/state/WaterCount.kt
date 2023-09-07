package com.example.jetpackcomposebasics.ui.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable { mutableStateOf(0) }


    StatelessCounter(
        count = waterCount,
        onAdd = { waterCount++ },
        onClear = { waterCount = 0 },
        modifier = modifier
    )
}

@Composable
fun StatelessCounter(
    count: Int,
    onAdd: () -> Unit,
    onClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Column {
            StatelessWellnessTask(modifier = modifier)
            if (count > 0) {
                Text("You've had $count glasses.")
            }
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = onAdd, enabled = count < 10) {
                Text("Add one")
            }
            if (count > 0) {
                Button(
                    onClick = onClear,
                    Modifier.padding(start = 8.dp)
                ) {
                    Text("Clear water count")
                }
            }
        }
    }
}

@Composable
fun StatelessWellnessTask(modifier: Modifier = Modifier) {
    var showTask by rememberSaveable { mutableStateOf(true) }
    if (showTask) {
        WellnessTaskBanner(
            onClose = { showTask = false },
            taskName = "Have you taken your 15 minute walk today?",
            modifier = modifier
        )
    }
}

@Composable
fun WellnessTaskBanner(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
