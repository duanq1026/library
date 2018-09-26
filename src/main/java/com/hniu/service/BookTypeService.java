package com.hniu.service;

import com.hniu.entity.BookTypes;

import java.util.List;

public interface BookTypeService {
    int deleteByPrimaryKey(Integer bookTypeId);

    int insert(BookTypes record);

    BookTypes selectByPrimaryKey(Integer bookTypeId);

    List<BookTypes> selectAll();

    int updateByPrimaryKey(BookTypes record);

    List<BookTypes> selectByFatherNode(Integer fatherNode);

}
