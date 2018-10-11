package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.service.BorrowHistoryService;
import com.hniu.service.BorrowsService;
import com.hniu.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowsHistoryController extends Base {

    @Autowired
    private BorrowHistoryService borrowHistoryService;

    @Autowired
    private BorrowsService borrowsService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/borrowsHistory")
    public Object selectAllHistory(Integer pageNum, Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowHistoryService.selectAllBorrowHistorys(pageNum,pageSize));
    }

    @GetMapping("/borrowsHistory/{readerId}")
    public Object selectByNameHistory(@PathVariable("readerId") Integer readerId, Integer pageNum, Integer pageSize){
        return packaging(StateCode.SUCCESS,borrowHistoryService.selectByReaderIdBorrowHistorys(readerId,pageNum,pageSize));
    }

    /**
     * 微信查询历史缴费
     */
    @GetMapping("/wx_borrowsHistory/{token}")
    public Object selectByNameHistory(@PathVariable("token") String token, Integer pageNum, Integer pageSize){
        String object = (String) redisUtil.getObject(token);
        if(object == null){
            return packaging(StateCode.FAIL,"error");
        }
        String[] str = object.split(",");
        return selectByNameHistory(Integer.parseInt(str[2]),pageNum,pageSize);
    }

}
