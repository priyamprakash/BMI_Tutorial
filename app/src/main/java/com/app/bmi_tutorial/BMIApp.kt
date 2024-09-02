package com.app.bmi_tutorial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun BMIApp(name: String , modifier: Modifier = Modifier) {

    //input
    //calculation
    //output

    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf<Double?>(null) }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("BMI Calculator")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val heightInMeters = height.toDoubleOrNull()?.div(100) ?: 0.0
            val weightInKg = weight.toDoubleOrNull() ?: 0.0
            bmi = if (heightInMeters > 0 && weightInKg > 0) {
                weightInKg / (heightInMeters * heightInMeters)
            } else {
                null
            }

            category = when (bmi!!) {
                in 0.0..18.4 -> "Underweight"
                in 18.5..24.9 -> "Normal weight"
                in 25.0..29.9 -> "Overweight"
                else -> "Obesity"
            }
        }) {
            Text("Calculate BMI")
        }

        Spacer(modifier = Modifier.height(16.dp))

        bmi?.let {
            Text("Your BMI is %.2f".format(it))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Category: $category")
        } ?: Text("Enter your details and press Calculate")

        Spacer(modifier = Modifier.height(16.dp))

        IconButton(onClick = {
            height = ""
            weight = ""
            bmi = null
            category = ""
        }) {
            Icon(Icons.Filled.Refresh, contentDescription = "Reset")
        }
    }
}