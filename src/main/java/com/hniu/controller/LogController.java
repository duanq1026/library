package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController extends Base {

    @Autowired
    LogService logService;

    @GetMapping("/log")
    public Object selectAllLog(Integer pageNum, Integer pageSize){
        PageWrap data = logService.selectAll(pageNum, pageSize);
        return packaging(StateCode.SUCCESS,data);
    }
}
