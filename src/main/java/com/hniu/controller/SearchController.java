package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;
import com.hniu.service.SearchBookVoService;
import com.hniu.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SearchController extends Base {

    @Autowired
    private SearchBookVoService searchBookVoService;

    @Autowired
    private SearchService searchService;
    @GetMapping("/import")
    public Object selectAll() {
        String message = searchBookVoService.importAllItems();
        if ("SUCCESS".equals(message))
            return packaging(StateCode.SUCCESS, message);
        else
            return packaging(StateCode.FAIL, message);
    }
    @GetMapping("/shearch")
    public Object shearch(PageVo<BookVo> pageBookVo) {
        String message = searchBookVoService.importAllItems();
        try {
            searchService.shearch(pageBookVo);
            return packaging(StateCode.SUCCESS,pageBookVo);
        }catch (Exception e) {
            e.printStackTrace();
            return packaging(StateCode.FAIL,null);
        }
    }


}
