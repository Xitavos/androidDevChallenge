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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyDetailScreen(navController: NavController, dog: Doggo) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Adopt ${dog.name}") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack()}) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                )
            },
            content = { PuppyInfo(dog = dog) }
        )
    }
}

@Composable
fun PuppyInfo(dog: Doggo) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = dog.emoji,
            fontSize = 150.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = dog.name, style = MaterialTheme.typography.h4)
        Text(text = dog.age, style = MaterialTheme.typography.subtitle1)
        Divider(color = Color.DarkGray, modifier = Modifier.padding(vertical = 20.dp))
        Text(text = "${dog.name} is a very good doggo. They are available for adoption right now!")
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {  })
        {
            Text(text = "Adopt ${dog.name} now!")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PuppyDetailScreenPreview() {
    MyTheme {
        PuppyInfo(dog = Doggo())
    }
}
