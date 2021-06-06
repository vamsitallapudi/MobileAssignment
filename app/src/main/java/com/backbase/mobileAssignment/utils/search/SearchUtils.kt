package com.backbase.mobileAssignment.utils.search

import com.backbase.mobileAssignment.data.database.entity.City

class SearchUtils private constructor(private val iDataStructure: IDataStructure) {
    fun insert(city: City?) {
        iDataStructure.insert(city)
    }

    fun searchWord(city: City?): Boolean {
        return iDataStructure.search(city)
    }

    fun getSuggestions(prefix: String?): List<City?> {
        return iDataStructure.getSuggestions(prefix)
    }

    fun fetchAll(): List<City?> {
        return iDataStructure.fetchAll()
    }

    companion object {
        private var searchUtils: SearchUtils? = null
        fun getInstance(iDataStructure: IDataStructure): SearchUtils {
            if (searchUtils == null) {
                searchUtils = SearchUtils(iDataStructure)
            }
            return searchUtils!!
        }
    }
}