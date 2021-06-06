package com.backbase.mobileAssignment.utils.search

import java.util.*

class TrieSearch private constructor() : ISearch {
    private val root: TrieNode = TrieNode()
    /**
     * Inserts a word into the trie.
     */
    override fun insert(word: String?) {
        if (word == null) return
        var node: TrieNode? = root
        for (ch in word) {
            if (!node!!.containsKey(ch)) {
                node.put(ch, TrieNode(ch))
            }
            node = node[ch]
        }
        node!!.setEnd()
    }

    /**
     * Returns if the word is in the trie.
     */
    override fun search(word: String?): Boolean {
        if (word == null) return false
        var node: TrieNode? = root
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
    override fun getSuggestions(prefix: String?): List<String?> {
        val list: MutableList<String?> = ArrayList()
        if (prefix == null) return list
        var lastNode: TrieNode? = root
        val curr = StringBuffer()
        for (c in prefix.toCharArray()) {
            lastNode = lastNode!![c]
            if (lastNode == null) return list
            curr.append(c)
        }
        suggestRec(lastNode, list, curr)
        return list
    }

    private fun suggestRec(root: TrieNode?, list: MutableList<String?>, curr: StringBuffer) {
        if (list.size >= SEARCH_RATE_LIMITER) return
        if (root!!.isWord) {
            list.add(curr.toString())
        }
        if (root.links == null || root.links!!.isEmpty()) return
        for (child in root.links!!) {
            if (child == null) continue
            suggestRec(child, list, curr.append(child.c))
            curr.setLength(curr.length - 1)
        }
    }

    internal class TrieNode {
        //        R links to node children
        var links: Array<TrieNode?>?
        private val size = 26
        var isWord = false
            private set
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
        const val SEARCH_RATE_LIMITER = 5
        private var trieSearch: TrieSearch? = null
        val instance: TrieSearch?
            get() {
                if (trieSearch == null) {
                    trieSearch = TrieSearch()
                }
                return trieSearch
            }
    }

}