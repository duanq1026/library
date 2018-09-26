package com.hniu.service.imp;

import com.hniu.entity.BookTypes;
import com.hniu.mapper.BookTypesMapper;
import com.hniu.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypesMapper bookTypesMapper;

    @Override
    public int deleteByPrimaryKey(Integer bookTypeId) {
        List<BookTypes> list= selectByFatherNode(bookTypeId);
        if(list.isEmpty()){
            return bookTypesMapper.deleteByPrimaryKey(bookTypeId);
        }else {
            for(BookTypes bookType:list){
                deleteByPrimaryKey(bookType.getBookTypeId());
            }
        }
        return bookTypesMapper.deleteByPrimaryKey(bookTypeId);

    }

    @Override
    public int insert(BookTypes record) {
        return bookTypesMapper.insert(record);
    }

    @Override
    public BookTypes selectByPrimaryKey(Integer bookTypeId) {
        return bookTypesMapper.selectByPrimaryKey(bookTypeId);
    }

    @Override
    public List<BookTypes> selectAll() {
        return bookTypesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(BookTypes record) {
        return bookTypesMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<BookTypes> selectByFatherNode(Integer fatherNode) {
        return bookTypesMapper.selectByFatherNode(fatherNode);
    }


}
