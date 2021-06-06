package com.backbase.mobileAssignment.utils

import org.junit.Assert.*
import org.junit.Test

class StringUtilsTest {
    @Test
    fun `test for normalization of special chars in string`() {
        val city = "Verrières -l(Kreis 3) \\/ e- Buisson Shāhrūd"
        assertEquals(StringUtils.normalizeStr(city), "verriereslkreisebuissonshahrud")
    }
}