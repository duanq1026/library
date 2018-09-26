package com.hniu.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hniu.entity.Cost;
import com.hniu.entity.vo.CostVo;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.mapper.CostMapper;
import com.hniu.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private CostMapper costMapper;

    @Autowired
    private com.hniu.entity.System system;

    @Override
    public PageWrap selectAllCost(Integer pageNum, Integer pageSize) {
        if(pageSize == null||pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum, pageSize);
        List<CostVo> list = costMapper.selectAllCost();
        PageInfo pageInfo = new PageInfo(list);
        return new PageWrap(pageInfo);
    }

    @Override
    public int AddOneCost(Cost cost) {
        return costMapper.AddOneCost(cost);
    }

    @Override
    public PageWrap selectByIdCost(Integer readerId, Integer pageNum, Integer pageSize) {
        if(pageSize == null||pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum,pageSize,false);
        List<CostVo> costVoList = costMapper.selectByIdCost(readerId);
        PageInfo pageInfo = new PageInfo(costVoList);
        return new PageWrap(pageInfo);
    }

    @Override
    public int DeleteOneCost(Integer id) {
        return costMapper.DeleteOneCost(id);
    }

    @Override
    public int UpdateOneCost(Cost cost) {
        return costMapper.UpdateOneCost(cost);
    }


}
