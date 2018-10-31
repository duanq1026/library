package com.hniu.service;


import com.hniu.entity.Permissions;
import com.hniu.entity.vo.Menu;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.exception.NotLoginException;

import java.util.List;

public interface PermissionsService {
     List<Permissions> selectPermissions(Integer roleid);

     List<Menu> selectMenu() throws NotLoginException;

     PageWrap selectAll(Integer pageNum, Integer pageSize);

     Permissions selectPrimaryKey(Integer permissionId);
}
