package com.hniu.service;

import com.hniu.entity.System;

import java.util.List;

public interface SystemService {

    List<System> selectAll();

    int updateByPrimaryKey(System record);
}