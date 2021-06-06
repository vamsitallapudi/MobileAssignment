package com.backbase.mobileAssignment.utils

import com.backbase.mobileAssignment.utils.search.SearchUtils
import com.backbase.mobileAssignment.utils.search.TrieDS
import org.junit.Assert.assertTrue
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class SearchUtilsTest {
    private var searchUtils: SearchUtils? = null

    @Before
    fun setUp() {
//        Used strategy pattern to dynamically switch between the search algo
        searchUtils = SearchUtils.getInstance(TrieDS.instance)
    }

    @Test
    fun `test if word is properly inserted`() {
        val word = "Hello"
        searchUtils!!.insert(word)
        assertTrue(searchUtils!!.searchWord(word))
    }

    @Test
    fun `test inserting null`() {
        val word = null
        searchUtils!!.insert(word)
        assertFalse(searchUtils!!.searchWord(word))
    }

    @Test
    fun `test search implementation for wrong word`() {
        assertFalse(searchUtils!!.searchWord("Hey"))
    }

    @Test
    fun `test search implementation for null`() {
        assertFalse(searchUtils!!.searchWord(null))
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