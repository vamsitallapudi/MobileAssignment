package com.backbase.mobileAssignment.utils.search

/**
 * Interface for Data Structure using Strategy Pattern.
 */
interface IDataStructure {
    fun insert(word: String?)
    fun search(word: String?): Boolean
    fun getSuggestions(prefix: String?): List<String?>?
}