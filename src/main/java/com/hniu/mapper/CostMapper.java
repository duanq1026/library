package com.hniu.mapper;

import com.hniu.entity.Cost;
import com.hniu.entity.vo.CostVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CostMapper extends Mapper<Cost> {
    //分页查询所有缴费信息
    public List<CostVo> selectAllCost();

    //插入缴费信息
    public int AddOneCost(Cost cost);

    //根据读者姓名查询缴费信息
    public List<CostVo> selectByIdCost(Integer readerId);

    //删除缴费信息
    public int DeleteOneCost(Integer id);

    //更新缴费信息
    public int UpdateOneCost(Cost cost);
}