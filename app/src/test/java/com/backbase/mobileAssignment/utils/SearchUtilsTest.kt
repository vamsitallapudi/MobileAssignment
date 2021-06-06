package com.backbase.mobileAssignment.utils

import com.backbase.mobileAssignment.data.database.entity.City
import com.backbase.mobileAssignment.data.database.entity.Movie
import com.backbase.mobileAssignment.utils.search.SearchUtils
import com.backbase.mobileAssignment.utils.search.TrieDS
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SearchUtilsTest {
    private var searchUtils: SearchUtils? = null

    @Before
    fun setUp() {
//        Used strategy pattern to dynamically switch between the search algo
        searchUtils = SearchUtils.getInstance(TrieDS.instance)
        insertData()
    }

    @Test
    fun `test size of all the fetched cities`() {
        val citiesList = searchUtils!!.fetchAll()
        assertEquals(citiesList.size, 2)
    }

    @Test
    fun `test if the city is contained in fetched list`() {
        val citiesList = searchUtils!!.fetchAll()
        assertTrue(citiesList.contains(City("Helrrières -l(Kreis 3) \\/ e- Buisson Shāhrūd")))
    }

    @Test
    fun `test if word is properly inserted`() {
        val city = City("Hello")
        searchUtils!!.insert(city)
        assertTrue(searchUtils!!.searchWord(city))
    }

    @Test
    fun `test inserting null`() {
        val word = null
        searchUtils!!.insert(word)
        assertFalse(searchUtils!!.searchWord(word))
    }

    @Test
    fun `test search implementation for wrong word`() {
        assertFalse(searchUtils!!.searchWord(City("Hey")))
    }

    @Test
    fun `test search implementation for null`() {
        assertFalse(searchUtils!!.searchWord(null))
    }

    @Test
    fun `test if suggestions are working properly`() {
        val suggestionList = searchUtils!!.getSuggestions("hel")
        assertTrue(suggestionList.contains(City("hella")))
    }

    @Test
    fun `test if suggestions doesn't contain wrong data`() {
        insertData()
        val suggestionList = searchUtils!!.getSuggestions("hel")
        assertFalse(suggestionList.contains(City("hellboy")))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        searchUtils = null
        System.gc()
    }

    private fun insertData() {
        searchUtils!!.insert(City("Helrrières -l(Kreis 3) \\/ e- Buisson Shāhrūd"))
        searchUtils!!.insert(City("hella"))
    }
}