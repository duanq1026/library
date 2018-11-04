package com.hniu.controller;

import com.hniu.constan.Operation;
import com.hniu.constan.StateCode;
import com.hniu.entity.Admin;
import com.hniu.entity.Logs;
import com.hniu.entity.Roles;
import com.hniu.service.LogService;
import com.hniu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@RestController
public class RoleController extends Base {

    @Autowired
    RoleService rs;
    @Autowired
    LogService logService;

    // 查询所有角色
    @GetMapping("/roles")
    public Object selectAll(Integer pageNum,Integer pageSize) {
        return packaging(StateCode.SUCCESS, rs.selectAll(pageNum, pageSize));
    }

    // 查询指定id的角色
    @GetMapping("/roles/{roleId}")
    public Object selectRole(@PathVariable Integer roleId) {
        return packaging(StateCode.SUCCESS, rs.selectByPrimaryKeyVo(roleId));
    }

    // 修改角色信息
    @PutMapping("/roles/{roleId}")
    public Object updateRole(@PathVariable Integer roleId, @RequestBody @Valid Roles role,HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if(currentAdmin == null){
            return packaging(StateCode.LOGINAGAIN,null);
        }
        role.setRoleId(roleId);
        rs.updateByPrimaryKey(role);
        logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.UPD,Operation.ROLE ,role.toString()));
        return packaging(StateCode.SUCCESS, role);
    }

    // 新增角色
    @PostMapping("/roles")
    public Object insertRole(@Valid @RequestBody Roles role,HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if(currentAdmin == null){
            return packaging(StateCode.LOGINAGAIN,null);
        }
        rs.insertRole(role);
        logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.ADD,Operation.ROLE ,role.toString()));
        return packaging(StateCode.SUCCESS,role );
    }

    // 删除角色
    @DeleteMapping("/roles/{roleId}")
    public Object deleteRole(@PathVariable Integer roleId,HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("admin");
        if(currentAdmin == null){
            return packaging(StateCode.LOGINAGAIN,null);
        }
        rs.deleteRole(roleId);
        logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.DEL,Operation.ROLE ,roleId.toString()));
        return packaging(StateCode.SUCCESS, null);
    }
}
