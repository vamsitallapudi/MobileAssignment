package com.backbase.mobileAssignment.utils.search;

import java.util.ArrayList;
import java.util.List;

public class TrieSearch implements ISearch {
    public static final int SEARCH_RATE_LIMITER = 5;
    private static TrieSearch trieSearch;
    private TrieNode root;

    private TrieSearch() {
        root = new TrieNode();
    }

    public static TrieSearch getInstance() {
        if (trieSearch == null) {
            trieSearch = new TrieSearch();
        }
        return trieSearch;
    }

    /**
     * Inserts a word into the trie.
     */
    @Override
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode(ch));
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    /**
     * Returns if the word is in the trie.
     */
    @Override
    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return false;
            }
        }
        return node.isWord();
    }

    /**
     * Get suggestions based on prefix entered.
     * */
    @Override
    public List<String> getSuggestions(String prefix) {
        List<String> list = new ArrayList<>();
        TrieNode lastNode = root;
        StringBuffer curr = new StringBuffer();
        for (char c : prefix.toCharArray()) {
            lastNode = lastNode.get(c);
            if (lastNode == null)
                return list;
            curr.append(c);
        }
        suggestRec(lastNode, list, curr);
        return list;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.containsKey(ch)) {
                node = node.get(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    private void suggestRec(TrieNode root, List<String> list, StringBuffer curr) {
        if (list.size() >= SEARCH_RATE_LIMITER)
            return;
        if (root.isWord) {
            list.add(curr.toString());
        }

        if (root.links == null || root.links.length <= 0)
            return;

        for (TrieNode child : root.links) {
            if (child == null)
                continue;
            suggestRec(child, list, curr.append(child.c));
            curr.setLength(curr.length() - 1);
        }
    }

    static class TrieNode {
        //        R links to node children
        private TrieNode[] links;

        private final int size = 26;

        private boolean isWord;

        char c;

        public TrieNode(char c) {
            this.c = c;
            links = new TrieNode[size];
        }

        public TrieNode() {
            links = new TrieNode[size];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isWord = true;
        }

        public boolean isWord() {
            return isWord;
        }
    }
}

