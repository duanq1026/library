package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.Cost;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.service.CostService;
import com.hniu.util.RedisUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CostController extends Base {
    @Autowired
    private CostService costService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/cost")
//    @RequiresPermissions(value = {"cost:select"})
    public Object selectAllCost(Integer pageNum, Integer pageSize){
        PageWrap data = costService.selectAllCost(pageNum,pageSize);
        return packaging(StateCode.SUCCESS,data);
    }

    @GetMapping("/cost/{readerId}")
//    @RequiresPermissions(value = {"cost:select"})
    public Object selectByReaderId(@PathVariable("readerId") Integer readerId, Integer pageNum, Integer pageSize){
        PageWrap data = costService.selectByIdCost(readerId,pageNum,pageSize);
        return packaging(StateCode.SUCCESS,data);
    }

    /**
     * 微信查询缴费记录根据读者id
     *
     */
    @GetMapping("/wx_cost/{token}")
    public Object selectByNameOfWx(@PathVariable("token") String token, Integer pageNum, Integer pageSize){
        String object = (String) redisUtil.getObject(token);
        if(object == null){
            return packaging(StateCode.FAIL,"error");
        }
        String[] str = object.split(",");
        return selectByReaderId(Integer.parseInt(str[2]),pageNum,pageSize);
    }

    @PostMapping("/cost")
//    @RequiresPermissions(value = {"cost:insert"})
    public Object AddOneCost(Cost cost){
        int i = costService.AddOneCost(cost);
        if(i == 0){
            return packaging(StateCode.FAIL,null);
        }
        if (i>1){
            return packaging(StateCode.FAIL,null);
        }
        return packaging(StateCode.SUCCESS,cost);
    }

}
