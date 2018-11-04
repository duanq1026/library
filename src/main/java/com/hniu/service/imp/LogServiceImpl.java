package com.hniu.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hniu.entity.Logs;
import com.hniu.entity.wrap.PageWrap;
import com.hniu.mapper.LogsMapper;
import com.hniu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogsMapper lm;
    @Autowired
    com.hniu.entity.System system;

    @Override
    public Logs addLog(Logs logs) {
        logs.setTime(new Date());
        lm.insert(logs);
        return logs;
    }

    @Override
    public PageWrap selectAll(Integer pageNum, Integer pageSize) {
        if(pageSize == null)
            pageSize = system.getPageLine().intValue();
        if (pageNum == null)
            pageNum = 1;
        PageHelper.startPage(pageNum, pageSize);
        List<Logs> list = lm.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return new PageWrap(pageInfo);

    }
}
