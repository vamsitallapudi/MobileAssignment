package com.backbase.mobileAssignment.utils

import java.text.Normalizer
import java.util.*

object StringUtils {
    fun normalizeStr(str: String) : String {
        val convertedString = Normalizer
            .normalize(str, Normalizer.Form.NFD)
            .replace("[^\\p{ASCII}]".toRegex(), "")
        return convertedString.toLowerCase(Locale.getDefault()).replace(" ", "").replace("[^a-z]".toRegex(), "")
    }
}