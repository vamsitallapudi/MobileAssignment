package com.backbase.mobileAssignment.utils

import com.backbase.mobileAssignment.utils.search.SearchUtils
import com.backbase.mobileAssignment.utils.search.TrieSearch
import org.junit.Assert.assertTrue
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class SearchUtilsTest {
    private var searchUtils: SearchUtils? = null

    @Before
    fun setUp() {
//        Used strategy pattern to dynamically switching the search algo
        searchUtils = SearchUtils.getInstance(TrieSearch.getInstance())
    }

    @Test
    fun `test if word is properly inserted`() {
        val word = "hello"
        searchUtils!!.insert(word.toLowerCase())
        assertTrue(searchUtils!!.searchWord(word))
    }

    @Test
    fun `test if suggestions doesn't contain wrong data`() {
        initDataForSuggestions()
        val suggestionList = searchUtils!!.getSuggestions("hel")
        assertFalse(suggestionList.contains("hellboy"))
    }

    @Test
    fun `test if suggestions are working properly`() {
        initDataForSuggestions()
        val suggestionList = searchUtils!!.getSuggestions("hel")
        assertTrue(suggestionList.contains("hella"))
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        searchUtils = null
        System.gc()
    }

    private fun initDataForSuggestions() {
        searchUtils!!.insert("hell")
        searchUtils!!.insert("hella")
    }
}