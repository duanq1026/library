package com.hniu.controller;

import com.hniu.constan.Operation;
import com.hniu.constan.StateCode;
import com.hniu.entity.Admin;
import com.hniu.entity.Logs;
import com.hniu.entity.System;
import com.hniu.service.LogService;
import com.hniu.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class SystemController extends Base {

    @Autowired
    private SystemService systemService;
    @Autowired
    private LogService logService;

    @GetMapping("/system")
    public Object systemList() {
        return packaging(StateCode.SUCCESS, systemService.selectAll());
    }

    @PutMapping("/system/{id}")
    public Object updateByPrimaryKey(System record, @PathVariable("id") Integer id, HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if (currentAdmin == null) {
            return packaging(StateCode.LOGINAGAIN, null);
        }
        record.setSysId(id);
        if (systemService.updateByPrimaryKey(record) == 0) {
            logService.addLog(new Logs(currentAdmin.getAdminId(), Operation.UPD, Operation.SYSTEM, record.toString()));
            return packaging(StateCode.FAIL, record);
        }
        return packaging(StateCode.SUCCESS, record);
    }
}