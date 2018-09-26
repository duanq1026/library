package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.Cost;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CostController extends Base {
    @Autowired
    private CostService costService;

    @GetMapping("/cost")
    public Object selectAllCost(Integer pageNum, Integer pageSize){
        PageWrap data = costService.selectAllCost(pageNum,pageSize);
        return packaging(StateCode.SUCCESS,data);
    }

    @GetMapping("/cost/{readerId}")
    public Object selectByName(@PathVariable("readerId") Integer readerId, Integer pageNum, Integer pageSize){
        PageWrap data = costService.selectByIdCost(readerId,pageNum,pageSize);
        return packaging(StateCode.SUCCESS,data);
    }

    @PostMapping("/cost")
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

    @DeleteMapping("/cost/{id}")
    public Object DeleteOneCost(@PathVariable("id") Integer id){
        int i = costService.DeleteOneCost(id);
        if(i==0){
            return packaging(StateCode.FAIL,null);
        }
        return packaging(StateCode.SUCCESS,null);
    }

    @PutMapping("/cost/{id}")
    public Object UpdateOneCost(@PathVariable("id") Integer id, Cost cost){
        cost.setCostId(id);
        int i = costService.UpdateOneCost(cost);
        if(i==0){
            return packaging(StateCode.FAIL,null);
        }
        return packaging(StateCode.SUCCESS,null);
    }


}
