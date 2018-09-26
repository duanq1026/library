package com.hniu.service.imp;

import com.hniu.entity.ReaderTypes;
import com.hniu.mapper.ReaderTypesMapper;
import com.hniu.service.ReaderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReaderTypeServiceImpl implements ReaderTypeService {

    @Autowired
    private ReaderTypesMapper readerTypesMapper;
    @Override
    public int deleteByPrimaryKey(Integer readerTypeId) {
        return readerTypesMapper.deleteByPrimaryKey(readerTypeId);
    }

    @Override
    public int insert(ReaderTypes record) {
        return readerTypesMapper.insert(record);
    }

    @Override
    public ReaderTypes selectByPrimaryKey(Integer readerTypeId) {
        return readerTypesMapper.selectByPrimaryKey(readerTypeId);
    }

    @Override
    public List<ReaderTypes> selectAll() {
        return readerTypesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ReaderTypes record) {
        return readerTypesMapper.updateByPrimaryKey(record);
    }
}