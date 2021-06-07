package com.backbase.mobileAssignment.data.models

import com.backbase.mobileAssignment.utils.StringUtils

data class City(val name:String, val country:String = "CA", var normalizedStr: String = StringUtils.normalizeStr(name+country))
