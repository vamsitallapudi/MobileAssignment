package com.backbase.mobileAssignment.utils.search;

import java.util.List;

public interface ISearch {
    void insert(String word);
    boolean search(String word);
    List<String> getSuggestions(String prefix);
}
