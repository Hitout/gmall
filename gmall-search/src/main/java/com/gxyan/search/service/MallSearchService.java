package com.gxyan.search.service;

import com.gxyan.search.vo.SearchParam;
import com.gxyan.search.vo.SearchResult;

/**
 * @author gxyan
 * @date 2020/11/22 23:06
 */
public interface MallSearchService {
    SearchResult search(SearchParam param);
}
