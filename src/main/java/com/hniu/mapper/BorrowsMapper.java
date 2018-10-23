package com.hniu.mapper;

import com.hniu.entity.Borrows;
import com.hniu.entity.vo.BorrowsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BorrowsMapper extends Mapper<Borrows> {
    public List<BorrowsVo> selectAllBorrows(Integer pageNum, Integer pageSize);

    public List<BorrowsVo> selectByIdBorrows(@Param("readerId") Integer readerId, Integer pageNum, Integer pageSize);

    public int updateBorrows(Borrows borrows);

    public int AddBorrows(Borrows borrows);

    public int DelteBorrows(Integer borrows_id);

    public int GetBorrowNumByReaderCode(@Param("readerCode") String readerCode);

    public Borrows selectByBorrowsid(Integer borrows_id);
}