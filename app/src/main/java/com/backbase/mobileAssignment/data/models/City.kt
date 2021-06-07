package com.backbase.mobileAssignment.data.models

import com.backbase.mobileAssignment.utils.StringUtils
import com.squareup.moshi.Json

data class City(
    @Json(name = "name") val name: String,
    @Json(name = "country") var country: String,
    @Json(name = "_id") var id: Long,
    @Json(name = "coord") var coordinates: Coordinates,
    var normalizedStr: String = StringUtils.normalizeStr(name + country)
)
