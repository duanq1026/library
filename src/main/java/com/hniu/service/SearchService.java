package com.hniu.service;

import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;

public interface SearchService {
    public void shearch(PageVo<BookVo> pageBookVo) throws Exception;
}
