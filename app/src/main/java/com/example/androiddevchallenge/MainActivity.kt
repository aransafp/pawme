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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.data.puppiesData
import com.example.androiddevchallenge.model.Puppy
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                AppNavigator()
            }
        }
    }

    @Composable
    fun AppNavigator() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            builder = {
                composable("homeScreen") { HomeScreen(navController) }
                composable(
                    "detailScreen/{puppy}",
                    arguments = listOf(
                        navArgument("puppy") {
                            type = NavType.StringType
                        }
                    ),
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("puppy")?.let { json ->
                        val puppy = Gson().fromJson(json, Puppy::class.java)
                        DetailScreen(puppy = puppy)
                    }
                }
            },
        )
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "PawMe")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Pets, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        PuppiesList(Modifier.padding(8.dp), navController, puppiesData)
    }
}

@Composable
fun PuppiesList(
    modifier: Modifier = Modifier,
    navController: NavController,
    listPuppy: List<Puppy>
) {

    fun navigateToDetail(puppy: Puppy) {
        val puppyJson = Gson().toJson(puppy)
        navController.navigate("detailScreen/$puppyJson")
    }

    LazyColumn(
        modifier = modifier,
        content = {
            items(items = listPuppy) { puppy ->
                Card(
                    elevation = 4.dp,
                    modifier = Modifier.clickable {
                        navigateToDetail(puppy = puppy)
                    }
                ) {
                    PuppyCard(puppy)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    )
}

@Composable
fun PuppyCard(puppy: Puppy) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Surface(
            modifier = Modifier.size(100.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = puppy.image),
                contentDescription = "beagle",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = puppy.name, fontWeight = FontWeight.Bold)
            Text(
                text = if (puppy.sex == 'm') "male" else "female",
                style = MaterialTheme.typography.body2
            )
            Text(text = puppy.location, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun DetailScreen(puppy: Puppy) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = puppy.image),
            contentDescription = puppy.name,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Facts About Me",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Name\t\t: ${puppy.name}" +
                    "\nBreed\t\t: ${puppy.breed}" +
                    "\nAge\t\t\t\t: ${puppy.age} months" +
                    "\nWeight\t: ${puppy.weight} lbs" +
                    "\nGender\t: ${if (puppy.sex == 'm') "male" else "female"}" +
                    "\nLocation\t: ${puppy.location}",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}
