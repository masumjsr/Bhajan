package com.vajan.vajan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vajan.vajan.ui.icon.Icon
import com.vajan.vajan.ui.navigation.AppNavHost
import com.vajan.vajan.ui.navigation.AppState
import com.vajan.vajan.ui.navigation.isTopLevelDestinationInHierarchy
import com.vajan.vajan.ui.navigation.rememberAppState
import com.vajan.vajan.ui.theme.VajanTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VajanTheme (
                dynamicColor = false
            ){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   MainApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val appState: AppState = rememberAppState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(

        bottomBar = {
            AnimatedVisibility(
                visible = appState.state.not(),
                enter = slideInVertically(
                    initialOffsetY = {
                        it / 2
                    },
                ),
                exit = slideOutVertically(
                    targetOffsetY = {
                        it / 2
                    },
                ),
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {

                    appState.topLevelDestination.forEach {
                        val selected =
                            appState.currentDestination.isTopLevelDestinationInHierarchy(
                                it
                            )


                        NavigationBarItem(selected = selected, onClick = {
                            appState.navigateToLevelDestination(it)
                        }, icon = {

                            when (val icon =
                                if (selected) it.selectedIcon else it.unselectedIcon) {
                                is Icon.ImageVectorIcon -> Icon(
                                    imageVector = icon.imageVector,
                                    contentDescription = null,
                                )

                                is Icon.DrawableResourceIcon -> Icon(
                                    painter = painterResource(id = icon.id),
                                    contentDescription = null,
                                )
                            }
                        }, label = {
                            Text(
                                text = it.titleTextId,
                            )
                        }

                        )
                    }

                }
            }
        },


    ) {


        AppNavHost(
            navController = appState.navHostController,
            modifier = Modifier
                .padding(it)
        ) {
            appState.onBackClick()
        }

    }
}

@Preview
@Composable
private fun PreviewMainApp() {
    
}