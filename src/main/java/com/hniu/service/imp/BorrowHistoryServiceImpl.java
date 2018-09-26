package com.hniu.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hniu.entity.vo.BorrowHistorysVo;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.mapper.BorrowHistorysMapper;
import com.hniu.service.BorrowHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowHistoryServiceImpl implements BorrowHistoryService {

    @Autowired
    private com.hniu.entity.System system;

    @Autowired
    private BorrowHistorysMapper borrowHistorysMapper;

    @Override
    public PageWrap selectAllBorrowHistorys(Integer pageNum, Integer pageSize) {
        if (pageSize == null || pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowHistorysVo> borrowHistorysVoList = borrowHistorysMapper.selectAllBorrowHistorys(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(borrowHistorysVoList);
        return new PageWrap(pageInfo);
    }

    @Override
    public PageWrap selectByReaderIdBorrowHistorys(Integer readerId, Integer pageNum, Integer pageSize) {
        if(pageSize == null || pageSize == 0){
            pageSize = system.getPageLine().intValue();
        }
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowHistorysVo> borrowHistorysVos = borrowHistorysMapper.selectByReaderIdBorrowHistorys(readerId,pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(borrowHistorysVos);
        return new PageWrap(pageInfo);
    }
}
