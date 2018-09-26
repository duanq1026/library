package com.hniu.service;

import com.hniu.entity.wrap.PageWrap;
import org.apache.ibatis.annotations.Param;

public interface BorrowHistoryService {
    public PageWrap selectAllBorrowHistorys(Integer pageNum, Integer pageSize);

    public PageWrap selectByReaderIdBorrowHistorys(@Param("readerId") Integer readerId, Integer pageNum, Integer pageSize);
}