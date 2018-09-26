package com.hniu.service;

import com.hniu.entity.Cost;
import com.hniu.entity.wrap.PageWrap;

public interface CostService {
    //分页查询所有缴费信息
    public PageWrap selectAllCost(Integer pageNum, Integer pageSize);

    //插入缴费信息
    public int AddOneCost(Cost cost);

    //根据读者id分页查询缴费信息
    public PageWrap selectByIdCost(Integer readerId, Integer pageNum, Integer pageSize);

    //删除缴费信息
    public int DeleteOneCost(Integer id);

    //更新缴费信息
    public int UpdateOneCost(Cost cost);
}