package com.hniu.service;

import com.hniu.entity.Logs;
import com.hniu.entity.wrap.PageWrap;
import org.springframework.stereotype.Service;


public interface LogService {

    Logs addLog(Logs logs);


    PageWrap selectAll(Integer pageNum, Integer pageSize);
}
