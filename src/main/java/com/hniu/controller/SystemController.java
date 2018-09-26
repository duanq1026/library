package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.System;
import com.hniu.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SystemController extends Base {

    @Autowired
    private SystemService systemService;

    @GetMapping("/system")
    public Object systemList(){
        return packaging(StateCode.SUCCESS,systemService.selectAll());
    }

    @PutMapping("/system/{id}")
    public Object updateByPrimaryKey(System record, @PathVariable("id") Integer id){
        record.setSysId(id);
        if (systemService.updateByPrimaryKey(record) == 0){
            return packaging(StateCode.FAIL,record);
        }
        return packaging(StateCode.SUCCESS,record);
    }
}