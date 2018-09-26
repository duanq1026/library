package com.hniu.service.imp;


import com.hniu.entity.System;
import com.hniu.mapper.SystemMapper;
import com.hniu.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SystemMapper systemMapper;


    @Override
    public List<System> selectAll() {
        return systemMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(System record) {
        return systemMapper.updateByPrimaryKey(record);
    }
}
