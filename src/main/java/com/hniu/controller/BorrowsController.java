package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.Borrows;
import com.hniu.service.BorrowsService;
import com.hniu.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BorrowsController extends Base {
    @Autowired
    private BorrowsService borrowsService;


    @GetMapping("/borrows")
    public Object selectAllBorrows(Integer pageNum,Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowsService.selectAllBorrows(pageNum, pageSize));
    }

    @GetMapping("/borrows/{Id}")
    public Object selectByNamesBorrows(@PathVariable("Id") Integer Id, Integer pageNum, Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowsService.selectByIdBorrows(Id, pageNum, pageSize));
    }

    @PutMapping("/borrows/{id}")
    public Object updateBorrows(Borrows borrows, @PathVariable("id") Integer id){
        borrows.setBorrowId(id);
        int i = 0;
        i = borrowsService.updateBorrows(borrows);
        if (i==0){
            return packaging(StateCode.FAIL,"update fail");
        }
        return packaging(StateCode.SUCCESS,null);
    }

    @PostMapping("/borrows")
    public Object AddBorrows(Borrows borrows){
        int i = 0;
        i = borrowsService.AddBorrows(borrows);
        if (i==0){
            return packaging(StateCode.FAIL,"Add fail");
        }
        return packaging(StateCode.SUCCESS,"Add success");
    }

    @DeleteMapping("/borrows/{id}")
    public Object DelteBorrows(@PathVariable("id") Integer borrows_id){
        int i = 0;
        i = borrowsService.DelteBorrows(borrows_id);
        if (i==0){
            return packaging(StateCode.FAIL,"Delete fail");
        }
        return packaging(StateCode.SUCCESS,"Delete success");
    }
}
