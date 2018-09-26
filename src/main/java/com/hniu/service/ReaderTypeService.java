package com.hniu.service;

import com.hniu.entity.ReaderTypes;

import java.util.List;

public interface ReaderTypeService {
    int deleteByPrimaryKey(Integer readerTypeId);

    int insert(ReaderTypes record);

    ReaderTypes selectByPrimaryKey(Integer readerTypeId);

    List<ReaderTypes> selectAll();

    int updateByPrimaryKey(ReaderTypes record);
}