package com.example.sharedelementtransitionexample

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import com.example.sharedelementtransitionexample.ui.composable.DetailsScreen
import com.example.sharedelementtransitionexample.ui.composable.HomeScreen
import com.example.sharedelementtransitionexample.ui.data.Bikes
import com.example.sharedelementtransitionexample.ui.data.Screens
import com.example.sharedelementtransitionexample.ui.theme.SharedElementTransitionExampleTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedElementTransitionExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.navigationBars
                ) { innerPadding ->

                    SharedTransitionLayout {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = Screens.HOME.name,
                        ) {
                            composable(
                                route = Screens.HOME.name,
                            ) {
                                HomeScreen(
                                    innerPadding,
                                    animatedContentScope = this@composable
                                ) { item ->
                                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                                        set("item", item)
                                    }

                                    navController.navigate(
                                        route = Screens.DETAILS.name
                                    )
                                }
                            }

                            composable(
                                route = Screens.DETAILS.name
                            ) {
                                val item = navController.previousBackStackEntry?.savedStateHandle?.get<Bikes>("item")
                                item?.let { item ->
                                    DetailsScreen(item,
                                        animatedContentScope = this@composable
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedElementTransitionExampleTheme {
        Greeting("Android")
    }
}