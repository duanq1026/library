package com.hniu.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hniu.entity.Borrows;
import com.hniu.entity.System;
import com.hniu.entity.vo.BorrowsVo;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.mapper.BorrowsMapper;
import com.hniu.service.BorrowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowsServiceImpl implements BorrowsService {

    @Autowired
    private BorrowsMapper borrowsMapper;

    @Autowired
    private System system;

    @Override
    public PageWrap selectAllBorrows(Integer pageNum, Integer pageSize) {
        if(pageSize == null || pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowsVo> borrowsVos = borrowsMapper.selectAllBorrows(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(borrowsVos);
        return new PageWrap(pageInfo);
    }

    @Override
    public PageWrap selectByIdBorrows(Integer readerId, Integer pageNum, Integer pageSize) {
        if(pageSize == null || pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowsVo> borrowsVos = borrowsMapper.selectByIdBorrows(readerId, pageNum, pageSize);
        PageInfo pageInfo = new PageInfo(borrowsVos);
        return new PageWrap(pageInfo);
    }

    @Override
    public int updateBorrows(Borrows borrows) {
        int i = 0;
        i = borrowsMapper.updateBorrows(borrows);
        return i;
    }

    @Override
    public int AddBorrows(Borrows borrows) {
        int i = 0;
        i = borrowsMapper.AddBorrows(borrows);
        return i;
    }

    @Override
    public int DelteBorrows(Integer borrows_id) {
        int i = 0;
        i = borrowsMapper.DelteBorrows(borrows_id);
        return i;
    }

    @Override
    public int GetBorrowNumByReaderCode(String readerCode) {
        return borrowsMapper.GetBorrowNumByReaderCode(readerCode);
    }

    @Override
    public Borrows selectByBorrowsid(Integer borrows_id) {
        return borrowsMapper.selectByBorrowsid(borrows_id);
    }
}
