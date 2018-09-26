package com.hniu.mapper;

import com.hniu.entity.System;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SystemMapper extends Mapper<System> {
    //查询系统设置
    List<System> selectAll();

    //更新系统设置
    int updateByPrimaryKey(System record);
}