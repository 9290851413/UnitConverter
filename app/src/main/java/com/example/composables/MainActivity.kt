package com.example.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composables.ui.theme.ComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposablesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var input by remember { mutableStateOf("") }
    var inputDropDown by remember { mutableStateOf(false) }
    var outputDropDown by remember { mutableStateOf(false) }
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Unit Converter",
            textDecoration = TextDecoration.Underline,
            fontSize = 32.sp
        )
        Spacer(Modifier.padding(8.dp))
        Text("Input Value: $inputValue")
        Spacer(Modifier.padding(8.dp))
        Text("Output Value: $outputValue")
        Spacer(Modifier.padding(8.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
            },
            singleLine = true
        )
        Spacer(Modifier.padding(8.dp))
        Row {
            Box {
                Button(
                    onClick = { inputDropDown = true }
                ) {
                    Text("Input")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow DropDown"
                    )
                }
                DropdownMenu(expanded = inputDropDown, onDismissRequest = { inputDropDown = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            inputValue = "Centimeter"
                            inputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            inputValue = "Meter"
                            inputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputValue = "Feet"
                            inputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            inputValue = "Millimeter"
                            inputDropDown = false
                        }
                    )
                }
            }
            Spacer(Modifier.padding(16.dp))
            Box {
                Button(
                    onClick = { outputDropDown = true }
                ) {
                    Text("Output")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow DropDown"
                    )
                }
                DropdownMenu(expanded = outputDropDown, onDismissRequest = { outputDropDown = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            outputValue = "Centimeter"
                            outputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meter") },
                        onClick = {
                            outputValue = "Meter"
                            outputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputValue = "Feet"
                            outputDropDown = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeter") },
                        onClick = {
                            outputValue = "Millimeter"
                            outputDropDown = false
                        }
                    )
                }
            }
        }
        Spacer(Modifier.padding(8.dp))
        Button(
            onClick = { output = converter(input, inputValue, outputValue) }
        ) {
            Text("Calculate")
        }
        Spacer(Modifier.padding(8.dp))
        Text("Result: $output")
    }
}

fun converter(input: String, inputUnit: String, outputUnit: String): String {
    val inputValue = input.toDoubleOrNull() ?: return "Invalid input"

    val valueInMeters = when (inputUnit) {
        "Centimeter" -> inputValue / 100
        "Meter" -> inputValue
        "Feet" -> inputValue * 0.3048
        "Millimeter" -> inputValue / 1000
        else -> 0.0
    }

    val convertedValue = when (outputUnit) {
        "Centimeter" -> valueInMeters * 100
        "Meter" -> valueInMeters
        "Feet" -> valueInMeters / 0.3048
        "Millimeter" -> valueInMeters * 1000
        else -> 0.0
    }

    return convertedValue.toString()
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
