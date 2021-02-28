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
package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Puppy

val puppiesData = listOf<Puppy>(
    Puppy("Liberty", "Beagle", 6, 29.3, 'm', "Texas", R.drawable.beagle),
    Puppy("Eden", "Bulldog", 4, 20.3, 'm', "California", R.drawable.bulldog),
    Puppy("Fluffy", "Chow chow", 5, 28.7, 'f', "Florida", R.drawable.chow_chow),
    Puppy("Rooky", "French bulldog", 6, 21.1, 'm', "New York", R.drawable.french_bulldog),
    Puppy("Roger", "German shepherd", 3, 22.6, 'm', "Pennysylvania", R.drawable.german_shepherd),
    Puppy("Goldy", "Golden retriever", 4, 20.5, 'm', "Illinois", R.drawable.golden_retriever),
    Puppy("Lily", "Labrador retriever", 4, 19.0, 'f', "Ohio", R.drawable.labrador_retriever),
    Puppy("Sally", "Puddle", 7, 14.3, 'f', "North Carolina", R.drawable.puddle),
    Puppy("Choco", "Rottweiler", 4, 23.3, 'm', "Georgia", R.drawable.rottweiler),
    Puppy("Inu", "Shiba", 5, 22.8, 'm', "Michigan", R.drawable.shiba),
)
