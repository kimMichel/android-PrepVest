package br.com.prepvest.model

import com.squareup.moshi.Json

data class CourseModel(
    @Json(name = "course") val course: String
)
