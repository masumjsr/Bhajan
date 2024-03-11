package com.vajan.vajan.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vajan.vajan.model.Category

@Composable
fun CategoryCard(category: Category,onClick:(categoryId:String)->Unit) {
    Card(
        modifier= Modifier.padding(4.dp),
        onClick = { onClick.invoke(category.category)}) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 24.dp),
            text = category.category,
            textAlign = TextAlign.Center

        )

    }

}