package com.hniu.service.imp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hniu.entity.Readers;
import com.hniu.entity.System;
import com.hniu.exception.SystemErrorException;
import com.hniu.service.ReaderService;
import com.hniu.service.WxLoginService;
import com.hniu.util.EncryptUtil;
import com.hniu.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class WxLoginServiceImpl implements WxLoginService {

    @Autowired
    System system;

    RestTemplate restTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ReaderService rs;

    public WxLoginServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String wxLogin(String code) throws SystemErrorException {
        String uuid = null;
        //根据code去微信服务器获取openid和sessionkey
        ResponseEntity<String> entity = restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + system.getAppid() +
                "&secret=" + system.getAppsecret() +
                "&js_code=" + code +
                "&grant_type=authorization_code", String.class);
        try {
            JsonNode jsonNode = objectMapper.readTree(entity.getBody());
            String errcode = jsonNode.findPath("errcode").toString();
            //解析返回的数据如果包含errcode就没能获取成功
            if (!StringUtils.isEmpty(errcode)) {
                throw new SystemErrorException("登录失败");
            } else {
                String session_key = jsonNode.findPath("session_key").toString().replace("\"", "");
                String openid = jsonNode.findPath("openid").toString().replace("\"", "");
                uuid = UUID.randomUUID().toString().replace("-", "");
                //将数据保存到redis
                redisUtil.setObject(uuid,session_key+","+openid,3*24l);
                Subject subject = SecurityUtils.getSubject();
                //查询数据库是否有这个微信号登录过
                Readers readers = rs.selectByWechat(openid);
                //没有就生成一个
                if(readers == null){
                    Calendar rightNow = Calendar.getInstance();
                    rightNow.setTime(new Date());
                    rightNow.add(Calendar.YEAR, 3);
                    Date time = rightNow.getTime();
                    readers = new Readers(1, 3, "微信用户", EncryptUtil.encryption("123",openid ).get("password"), openid, "","" , "", null,new Byte("0") , new Date(), time,new Byte("0"),new Byte("0"), "" ,"");
                    rs.insert(readers);
                }
                //登录
                subject.login(new UsernamePasswordToken(readers.getWechat(),readers.getPassword()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid;

    }
}
