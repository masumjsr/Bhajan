package com.vajan.vajan.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun DrawerItem(text: String, imageVector: ImageVector, drawerState: DrawerState, onClick: () -> Unit) {
    val scope = rememberCoroutineScope()

    NavigationDrawerItem(
        label = {
            Row(modifier = Modifier) {
                Icon(
                    modifier = Modifier.padding(end = 8.dp),
                    imageVector = imageVector, contentDescription = null
                )
                Text(
                    text = text,

                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        selected = false,
        onClick = {
            scope.launch {
                drawerState.apply {

                    if (isClosed) open() else close()
                }
            }
            onClick.invoke()

        }
    )
}