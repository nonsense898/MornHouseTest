package com.non.mornhouse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.non.mornhouse.data.entities.Number

@Composable
fun LazyItem(number: Number, onNumberClick: (Number) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ), onClick = {
            onNumberClick(number)
        }
    ) {
        Text(
            text = number.text,
            maxLines = 1,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(26.dp),
            textAlign = TextAlign.Start, color = MaterialTheme.colorScheme.inverseSurface
        )
    }
}