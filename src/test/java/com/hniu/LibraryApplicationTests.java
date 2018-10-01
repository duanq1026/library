package com.hniu;

import com.github.pagehelper.PageHelper;
import com.hniu.entity.Admin;
import com.hniu.entity.BookTypes;
import com.hniu.entity.vo.AdminVo;
import com.hniu.entity.vo.PermissionsVo;
import com.hniu.exception.SystemErrorException;
import com.hniu.mapper.AdminMapper;
import com.hniu.mapper.PermissionsMapper;
import com.hniu.service.WxLoginService;
import com.hniu.service.imp.BookTypeServiceImpl;
import com.hniu.util.RedisUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {

    @Autowired
    WxLoginService wx;
    @Test
    public void hello(){
        try {
            wx.wxLogin("001EEmpb1QO9Lu0afRpb1UO1pb1EEmph");
        } catch (SystemErrorException e) {
            e.printStackTrace();
        }
    }
}