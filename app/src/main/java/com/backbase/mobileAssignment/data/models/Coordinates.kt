package com.backbase.mobileAssignment.data.models

import com.squareup.moshi.Json

data class Coordinates(
    @Json(name = "lat")val lat: Double,
    @Json(name = "lon")val lon: Double
) {
}