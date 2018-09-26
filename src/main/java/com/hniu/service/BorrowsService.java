package com.hniu.service;

import com.hniu.entity.Borrows;
import com.hniu.entity.wrap.PageWrap;
import org.apache.ibatis.annotations.Param;

public interface BorrowsService {

    public PageWrap selectAllBorrows(Integer pageNum, Integer pageSize);

    public PageWrap selectByIdBorrows(@Param("readerId") Integer readerId, Integer pageNum, Integer pageSize);

    public int updateBorrows(Borrows borrows);

    public int AddBorrows(Borrows borrows);

    public int DelteBorrows(Integer borrows_id);
}
