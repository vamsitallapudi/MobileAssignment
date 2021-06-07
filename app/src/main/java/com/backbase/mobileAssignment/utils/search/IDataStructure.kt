package com.backbase.mobileAssignment.utils.search

import com.backbase.mobileAssignment.data.models.City

/**
 * Interface for Data Structure using Strategy Pattern.
 */
interface IDataStructure {
    fun insert(city: City?)
    fun search(city: City?): Boolean
    fun getSuggestions(prefix: String?): List<City?>
    fun fetchAll(): List<City?>
}