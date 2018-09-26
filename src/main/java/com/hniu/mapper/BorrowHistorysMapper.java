package com.hniu.mapper;

import com.hniu.entity.BorrowHistorys;
import com.hniu.entity.vo.BorrowHistorysVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BorrowHistorysMapper extends Mapper<BorrowHistorys> {

    public List<BorrowHistorysVo> selectAllBorrowHistorys(Integer pageNum, Integer pageSize);

    public List<BorrowHistorysVo> selectByReaderIdBorrowHistorys(@Param("readerId") Integer readerId, Integer pageNum, Integer pageSize);

}