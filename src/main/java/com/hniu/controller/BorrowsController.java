package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.BookStates;
import com.hniu.entity.Borrows;
import com.hniu.entity.vo.ReaderVo;
import com.hniu.mapper.BookStatesMapper;
import com.hniu.service.BorrowsService;
import com.hniu.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class BorrowsController extends Base {
    @Autowired
    private BorrowsService borrowsService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private BookStatesMapper bookStatesMapper;

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
            return packaging(StateCode.FAIL,"Update fail");
        }
        return packaging(StateCode.SUCCESS,"Update success");
    }

    @PostMapping("/borrows")
    public Object AddBorrows(Borrows borrows){
        ReaderVo readerVo = readerService.selectByPrimaryKey(borrows.getReaderId());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date date1 = null,date3= null;
        try {
             date1 = format.parse(format.format(date));
        } catch (ParseException e) {
            // TODO Auto-generated catgch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH,readerVo.getBorrowDay());
        Date date2 = cal.getTime();
        try {
             date3 = format.parse(format.format(date2));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        borrows.setBorrowTime(date1);
        borrows.setRepayTime(date2);
        borrows.setRenew(false);
        borrows.setOverdue(false);
        borrows.setFine(new Float(0));
        int i = 0;
        i = borrowsService.AddBorrows(borrows);
        if (i!=0){
            BookStates bookStates = new BookStates();
            bookStates.setBookStateId(borrows.getBookStateId());
            bookStates.setBorrowNumber((short)(bookStatesMapper.selectByPrimaryKey(bookStates.getBookStateId()).getBorrowNumber() + 1));
            bookStates.setState((byte)1);
            bookStatesMapper.updateByPrimaryKey(bookStates);
            return packaging(StateCode.SUCCESS,"Add success");
        }
        return packaging(StateCode.FAIL,"Add fail");
    }

    @DeleteMapping("/borrows/{id}")
    public Object DelteBorrows(@PathVariable("id") Integer borrows_id){
        int i = 0;
        i = borrowsService.DelteBorrows(borrows_id);
        if (i!=0){
            return packaging(StateCode.SUCCESS,"Delete success");
        }
        return packaging(StateCode.FAIL,"Delete fail");
    }
}
