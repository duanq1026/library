package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.ReaderTypes;
import com.hniu.service.ReaderTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ReaderTypesController extends Base {

	@Autowired
	private ReaderTypeService readerTypeService;

	// 查询所有读者类型
	@GetMapping("/reader_type")
	public Object selectAll() {
		List<ReaderTypes> list= readerTypeService.selectAll();
		if(list == null){
			return packaging(StateCode.SUCCESS,list);
		}
		return packaging(StateCode.FAIL,list);
	}

	// 查询指定id的读者类型
	@GetMapping("/reader_type/{readerTypeId}")
	public Object selectReaderType(@PathVariable Integer readerTypeId) {
		ReaderTypes readerTypes= readerTypeService.selectByPrimaryKey(readerTypeId);
        if(readerTypes == null){
            return packaging(StateCode.SUCCESS,readerTypes);
        }
        return packaging(StateCode.FAIL,readerTypes);
	}
	//@RequiresPermissions("reader_type:update")
	// 修改读者类型信息
	@PutMapping("/reader_type/{readerTypeId}")
	public Object updateReaderType(ReaderTypes readerTypes) {

        if(readerTypeService.updateByPrimaryKey(readerTypes) > 0){
            return packaging(StateCode.SUCCESS,readerTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 新增读者类型
	//@RequiresPermissions("reader_type:insert")
	@PostMapping("/reader_type")
	public Object insertReaderType(ReaderTypes readerTypes) {
        if(readerTypeService.insert(readerTypes) > 0){
            return packaging(StateCode.SUCCESS,readerTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 删除读者类型
	//@RequiresPermissions("reader_type:delete")
	@DeleteMapping("/reader_type/{readerTypeId}")
	public Object deleteReaderType(@PathVariable Integer readerTypeId) {
        if(readerTypeService.deleteByPrimaryKey(readerTypeId) > 0){
            return packaging(StateCode.SUCCESS,readerTypeId);
        }
        return packaging(StateCode.FAIL,null);

	}
}
