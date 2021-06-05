package com.backbase.mobileAssignment.data.models

import com.backbase.mobileAssignment.data.database.entity.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResult( @SerializedName("results") val result: List<Movie>)
