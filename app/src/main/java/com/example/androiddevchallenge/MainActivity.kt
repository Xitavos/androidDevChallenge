/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlin.random.Random

data class Doggo(
    val name: String = dogNames.random(),
    val emoji: String = dogEmojis.random(),
    val age: String = "${Random.nextInt(19)} months"
)

val dogNames = listOf(
    "Max",
    "Fred",
    "Maxine",
    "Alfred",
    "Poppy",
    "Chuck",
    "Rover",
    "Good Boi",
    "Suzy",
    "Thomas",
    "Barky McBarkface",
    "Barry",
    "Spot",
    "Bella",
    "Lucy",
    "Woofer",
    "Buddy"
)
val dogEmojis = listOf("🐶", "🐕", "🦮", "🐩", "🐕‍🦺", "🌭", "🐈")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "dogslist") {
        composable("dogslist") { DogsListScreen(navController) }
        composable(
            route = "dogdetail/name={name},emoji={emoji},age={age}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("emoji") { type = NavType.StringType },
                navArgument("age") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val emoji = backStackEntry.arguments?.getString("emoji") ?: ""
            val age = backStackEntry.arguments?.getString("age") ?: ""
            PuppyDetailScreen(navController, Doggo(name = name, emoji = emoji, age = age))
        }
    }
}

@Composable
fun DogsListScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Adopt-a-Doggo") })
            },
            content = {
                PuppyList(
                    dogs = List(25) { Doggo() },
                    onItemClicked = { dog ->
                        navController.navigate("dogdetail/name=${dog.name},emoji=${dog.emoji},age=${dog.age}")
                    }
                )
            }
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
