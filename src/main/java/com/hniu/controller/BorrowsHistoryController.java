package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.service.BorrowHistoryService;
import com.hniu.service.BorrowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowsHistoryController extends Base {

    @Autowired
    private BorrowHistoryService borrowHistoryService;

    @Autowired
    private BorrowsService borrowsService;

    @GetMapping("/borrowsHistory")
    public Object selectAllHistory(Integer pageNum, Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowHistoryService.selectAllBorrowHistorys(pageNum,pageSize));
    }

    @GetMapping("/borrowsHistory/{readerId}")
    public Object selectByNameHistory(@PathVariable("readerId") Integer readerId, Integer pageNum, Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowHistoryService.selectByReaderIdBorrowHistorys(readerId,pageNum,pageSize));
    }

}
