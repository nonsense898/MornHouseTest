package com.non.mornhouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.non.mornhouse.ui.EmptyPlaceholder
import com.non.mornhouse.ui.LazyItem
import com.non.mornhouse.viewmodel.MainViewModel


@Composable
fun MainScreen(navController: NavController, mainViewModel: MainViewModel) {
    val movieList by mainViewModel.storedFacts.observeAsState(emptyList())
    var isError by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 100.dp)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                isError = newText.isEmpty() || !newText.all { it.isDigit() }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp),
            isError = isError,
            supportingText = { if (isError) Text("Please enter some numbers") },
            label = { Text("Enter number") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.inverseSurface,
                unfocusedBorderColor = MaterialTheme.colorScheme.inverseSurface,
                focusedTextColor = MaterialTheme.colorScheme.inverseSurface,
                focusedSupportingTextColor = MaterialTheme.colorScheme.inverseSurface,
                focusedLabelColor = MaterialTheme.colorScheme.inverseSurface
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                if (text.isEmpty()) {
                    isError = true
                } else {
                    mainViewModel.fetchNumberFact(text.toInt())
                }
            }, colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.inverseSurface,
                containerColor = MaterialTheme.colorScheme.secondary
            )) {
                Text("Get fact")
            }
            Button(onClick = {
                mainViewModel.fetchNumberFact()
            },  colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.inverseSurface,
                containerColor = MaterialTheme.colorScheme.secondary
            )) {
                Text("Get fact about random number")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                ),
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                movieList.ifEmpty {
                    EmptyPlaceholder()
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(
                        items = movieList,
                    ) { index, item ->
                            LazyItem(
                                number = item,
                                onNumberClick = {
                                    mainViewModel.setCurrentIndex(index)
                                    navController.navigate("detailsScreen")
                                }
                            )
                    }
                }
            }
        }
    }
}



