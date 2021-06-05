package com.backbase.mobileAssignment.utils.search;

import java.util.List;

public class SearchUtils {
    private static SearchUtils searchUtils;
    private final ISearch iSearch;

    private SearchUtils(ISearch iSearch) {
        this.iSearch = iSearch;
    }

    public static SearchUtils getInstance(ISearch iSearch) {
        if (searchUtils == null) {
            searchUtils = new SearchUtils(iSearch);
        }
        return searchUtils;
    }


    public void insert(String word) {
        if(word == null)
            return;
        iSearch.insert(word.toLowerCase());
    }

    public boolean searchWord(String word) {
        return iSearch.search(word);
    }

    public List<String> getSuggestions(String prefix) {
        return iSearch.getSuggestions(prefix);
    }

}
