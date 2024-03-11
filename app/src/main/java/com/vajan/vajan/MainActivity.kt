package com.vajan.vajan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vajan.vajan.ui.component.DrawerItem
import com.vajan.vajan.ui.icon.Icon
import com.vajan.vajan.ui.navigation.AppNavHost
import com.vajan.vajan.ui.navigation.AppState
import com.vajan.vajan.ui.navigation.isTopLevelDestinationInHierarchy
import com.vajan.vajan.ui.navigation.rememberAppState
import com.vajan.vajan.ui.theme.VajanTheme
import com.vajan.vajan.utils.findActivity
import com.vajan.vajan.utils.openUrl
import com.vajan.vajan.utils.shareApk
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
    val context = LocalView.current.context
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.padding(end = 60.dp)) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        modifier = Modifier
                            .height(200.dp)
                            .width(100.dp),
                        painter = painterResource(id = R.drawable.placeholder),
                        contentDescription = null
                    )
                }
                HorizontalDivider()

                DrawerItem(

                    text = "Privacy Policy",
                    imageVector = Icons.Default.Policy,
                    drawerState = drawerState
                ) { context.openUrl("https://www.privecyurl.com") }



                DrawerItem(

                    text = "Share App",
                    imageVector = Icons.Default.Share,
                    drawerState = drawerState
                ) { context.shareApk() }





                DrawerItem(

                    text = "Exit",
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    drawerState = drawerState
                ) { context.findActivity().finish() }


                // ...other drawer items
            }
        },
    ) {
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
                    .padding(it),
                onBackClick = {appState.onBackClick()},
                onDrawerClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )

        }
    }
}

@Preview
@Composable
private fun PreviewMainApp() {
    
}