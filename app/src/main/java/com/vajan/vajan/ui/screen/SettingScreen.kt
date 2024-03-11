package com.vajan.vajan.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingScreenRoute(onDrawerClick: () -> Unit) {

    SettingScreen(onDrawerClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(onDrawerClick: () -> Unit) {
Scaffold(
    topBar = {
        TopAppBar(
            title = {

                Text(
                    text = "Setting",
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onDrawerClick.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "menu icon"
                    )
                }
            },
        )
    }
) {
    Column (
        modifier= Modifier.padding(it),
    ){

    }
}
}

@Preview
@Composable
fun previewSettingScreen() {
    SettingScreen({})

}