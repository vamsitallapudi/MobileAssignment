package com.backbase.mobileAssignment.utils.search;

import java.util.List;

public class SearchUtils {
    private static SearchUtils searchUtils;
    private final IDataStructure iDataStructure;

    private SearchUtils(IDataStructure iDataStructure) {
        this.iDataStructure = iDataStructure;
    }

    public static SearchUtils getInstance(IDataStructure iDataStructure) {
        if (searchUtils == null) {
            searchUtils = new SearchUtils(iDataStructure);
        }
        return searchUtils;
    }


    public void insert(String word) {
        if(word == null)
            return;
        iDataStructure.insert(word.toLowerCase());
    }

    public boolean searchWord(String word) {
        if(word == null)
            return false;
        return iDataStructure.search(word.toLowerCase());
    }

    public List<String> getSuggestions(String prefix) {
        return iDataStructure.getSuggestions(prefix);
    }

}
