package com.hniu.mapper;

import com.hniu.entity.Readers;
import com.hniu.entity.vo.PageVo;
import com.hniu.entity.vo.ReaderVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ReadersMapper{
	Readers selectByWechat(String id);

    int deleteByPrimaryKey(Integer readerId);

    int insert(Readers record);

    ReaderVo selectByPrimaryKey(Integer readerId);

    List<ReaderVo> selectAll();

    int updateByPrimaryKey(Readers record);

	List<ReaderVo> selectByShearch(PageVo<ReaderVo> pageVo);

	List<Map<String, Integer>> selectSexCount();

	//Integer selectAgeCount(Map<String, Integer> map);

	ReaderVo selectByRCode(String readerCode);

	int updateOverdue(Integer readerId);

	List<ReaderVo> selectByOther(Readers reader);

	String selectRcode();

	ReaderVo selectBorrowBooks(String readerCode);
}