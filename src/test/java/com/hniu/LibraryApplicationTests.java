package com.hniu;

import com.github.pagehelper.PageHelper;
import com.hniu.entity.Admin;
import com.hniu.entity.BookTypes;
import com.hniu.entity.vo.AdminVo;
import com.hniu.entity.vo.PermissionsVo;
import com.hniu.mapper.AdminMapper;
import com.hniu.mapper.PermissionsMapper;
import com.hniu.service.imp.BookTypeServiceImpl;
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
    RedisTemplate redisTemplate;

    @Test
    public void hello(){
        redisTemplate.opsForValue().set("ss","ss" );
    }
}
