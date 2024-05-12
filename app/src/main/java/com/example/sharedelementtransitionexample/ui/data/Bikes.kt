package com.example.sharedelementtransitionexample.ui.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.versionedparcelable.ParcelField
import com.example.sharedelementtransitionexample.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class Bikes(
    @DrawableRes val bikeImage : Int,
    val name : String,
    val brand : String,
    val description : String
) : Parcelable

//sample data
fun getListOfBikes() = arrayListOf(
    Bikes(
        R.drawable.bajaj_pulser,
        "Bajaj Pulser RS 200",
        "Bajaj",
        "The Bajaj Pulsar RS200 is powered by a 199.5 cc air-cooled engine which produces 24.5 PS @ 9750 rpm of power. It has a fuel tank of 13 L and a claimed mileage of 35 kmpl. The Bajaj Pulsar RS200 starts at Rs 1.73 Lakh Rs (ex-showroom, Delhi). It is available in one variants."
    ),
    Bikes(
        R.drawable.kawasaki,
        "Kawasaki Ninja 300",
        "Kawasaki",
        "The Kawasaki Ninja 300 is powered by a 296 cc air-cooled engine which produces 39 PS @ 11000 rpm of power. It has a fuel tank of 17 L and a claimed mileage of 30 kmpl. The Kawasaki Ninja 300 starts at Rs 3.43 Lakh Rs (ex-showroom, Delhi). It is available in one variants."
    )
)