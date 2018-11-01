package com.hniu.controller;

import com.hniu.constan.Operation;
import com.hniu.constan.StateCode;
import com.hniu.entity.Admin;
import com.hniu.entity.Logs;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.exception.NotLoginException;
import com.hniu.exception.PassWordErrorException;
import com.hniu.exception.SystemErrorException;
import com.hniu.exception.UserNameExistException;
import com.hniu.service.AdminService;
import com.hniu.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController
public class AdminController extends Base {

    @Autowired
    AdminService as;

    @Autowired
    LogService logService;

    @GetMapping("/admins")
    public Object selectAll(Integer pageNum,  Integer pageSize) {
        System.out.println(SecurityUtils.getSubject().getSession().getId());
        PageWrap data = as.selectAllVo(pageNum, pageSize);
        return packaging(StateCode.SUCCESS, data);
    }

    @GetMapping("/admins/{id}")
    public Object selectByPrimaryKey(@PathVariable("id") Integer id) {
        return packaging(StateCode.SUCCESS, as.selectByPrimaryKeyVo(id));
    }

    @PostMapping("/admins")
    public Object insert(@RequestBody Admin admin, HttpSession session) {
        try {
            Admin currentAdmin = (Admin) session.getAttribute("admin");
            if(currentAdmin == null){
                throw new NotLoginException();
            }
            admin = as.insert(admin);
            logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.ADD,Operation.ADMIN ,admin.toString()));
            return packaging(StateCode.SUCCESS, admin);
        } catch (UserNameExistException e) {
            return packaging(StateCode.USERNAMEEXIST, admin);
        } catch (SystemErrorException e) {
            return packaging(StateCode.FAIL, admin);
        } catch (NotLoginException e) {
            return packaging(StateCode.LOGINAGAIN, admin);
        }
    }

    @PutMapping("admins/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody Admin admin,HttpSession session) {
        admin.setAdminId(id);
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if(currentAdmin == null){
            return packaging(StateCode.LOGINAGAIN, null);
        }
        int i = as.update(admin);
        logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.UPD,Operation.ADMIN ,admin.toString()));
        return packaging(StateCode.SUCCESS, i);
    }

    @PutMapping("/admins/update_password")
    public Object updatePassword(@RequestBody Map<String,String> map,HttpSession session) {
        try {
            as.changePassword(map);
            return packaging(StateCode.SUCCESS, null);
        } catch (NotLoginException e) {
            return packaging(StateCode.LOGINAGAIN, null);
        } catch (PassWordErrorException e) {
            return packaging(StateCode.PASSWORDMISTAKE, map.get("oldoldPassword"));
        }

    }

    @DeleteMapping("/admins/{id}")
    public Object delete(@PathVariable("id") Integer id,HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if(currentAdmin == null){
            return packaging(StateCode.LOGINAGAIN, null);
        }
        int i = as.delete(id);
        logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.DEL,Operation.ADMIN ,id.toString()));
        if (i > 0)
            return packaging(StateCode.SUCCESS, null);
        else
            return packaging(StateCode.FAIL, null);

    }


}
