package com.backbase.mobileAssignment.utils.search

/**
 * Interface for Search using Strategy Pattern
 */
interface ISearch {
    fun insert(word: String?)
    fun search(word: String?): Boolean
    fun getSuggestions(prefix: String?): List<String?>?
}