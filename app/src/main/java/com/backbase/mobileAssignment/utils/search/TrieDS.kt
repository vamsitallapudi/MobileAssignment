package com.backbase.mobileAssignment.utils.search

import com.backbase.mobileAssignment.data.models.City
import com.backbase.mobileAssignment.data.models.Coordinates
import java.util.*
/**
 * Implemented Trie because it is an efficient data structure while getting the keys with auto suggestion.
 * Time Complexity to insert all words - O(M * N) where M is the average length of the word and N -> No of words
 * Time Complexity to get each Word with prefix - 0(M) where M is the length of the word.
 *
 * Since we are filtering the data with prefix, Trie is the best DS to lookup compared to HashTable/HashMap.
 * Also since large data, collisions can occur with Hashtable which again increases the Time Complexity.
 *
 * */
class TrieDS private constructor() : IDataStructure {
    private val root: TrieNode = TrieNode()

    /**
     * Inserts a word into the trie.
     */
    override fun insert(city: City?) {
        if (city?.name == null) return
        var node: TrieNode? = root
        val word = city.normalizedStr
        for (ch in word) {
            if (!node!!.containsKey(ch)) {
                node.put(ch, TrieNode(ch))
            }
            node = node[ch]
        }
        node?.apply {
//            Storing the data in the word's last node.
            originalStr = city.name
            country = city.country
            id = city.id
            coordinates = city.coordinates
            setEnd()
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    override fun search(city: City?): Boolean {
        if (city?.name == null) return false
        var node: TrieNode? = root
        val word = city.normalizedStr
        for (ch in word) {
            node = if (node!!.containsKey(ch)) {
                node[ch]
            } else {
                return false
            }
        }
        return node!!.isWord
    }

    /**
     * Get suggestions based on prefix entered.
     */
    override fun getSuggestions(prefix: String?): List<City?> {
        val list = ArrayList<City?>()
        if (prefix == null) return list
        var lastNode: TrieNode? = root
        val curr = StringBuffer()
        for (c in prefix.toCharArray()) {
            lastNode = lastNode!![c]
            if (lastNode == null) return list
            curr.append(c)
        }
        fetchRecursively(lastNode, list, curr)
        return list
    }

    override fun fetchAll(): List<City?> {
        val list = ArrayList<City?>()
        val node = root
        fetchRecursively(node, list, StringBuffer())
        return list
    }

//    to recursively fetch the words
    private fun fetchRecursively(node: TrieNode?, list: MutableList<City?>, curr: StringBuffer) {
        if (node!!.isWord) {
//            retrieving the original data from the last trie node of the word
            list.add(City(name= node.originalStr, id = node.id, coordinates = node.coordinates, country = node.country, normalizedStr = curr.toString()))
        }
        if (node.links == null || node.links!!.isEmpty()) return
        for (child in node.links!!) {
            if (child == null) continue
            fetchRecursively(child, list, curr.append(child.c))
            curr.setLength(curr.length - 1)
        }
    }

    internal class TrieNode {
        var links: Array<TrieNode?>?
        private val size = 26
        var isWord = false
            private set
        var originalStr = ""
        var id: Long = 0
        var country: String = "IN"
        var coordinates = Coordinates(78.00,27.00)
        var c = 0.toChar()

        constructor(c: Char) {
            this.c = c
            links = arrayOfNulls(size)
        }

        constructor() {
            links = arrayOfNulls(size)
        }

        fun containsKey(ch: Char): Boolean {
            return links!![ch - 'a'] != null
        }

        operator fun get(ch: Char): TrieNode? {
            return links!![ch - 'a']
        }

        fun put(ch: Char, node: TrieNode?) {
            links!![ch - 'a'] = node
        }

        fun setEnd() {
            isWord = true
        }
    }

    companion object {
        private var trieSearch: TrieDS? = null
        val instance: TrieDS
            get() {
                if (trieSearch == null) {
                    trieSearch = TrieDS()
                }
                return trieSearch!!
            }
    }

}