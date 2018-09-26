package com.hniu.controller;

import com.hniu.constan.StateCode;
import com.hniu.entity.BookTypes;
import com.hniu.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookTypesController extends Base {

	@Autowired
	private BookTypeService bookTypeService;

	// 查询所有角色
	@GetMapping("/book_type")
	public Object selectAll() {
		List<BookTypes> list= bookTypeService.selectAll();
		if(list == null){
			return packaging(StateCode.SUCCESS,list);
		}
		return packaging(StateCode.FAIL,list);
	}

	// 查询指定id的角色
	@GetMapping("/book_type/{bookTypeId}")
	public Object selectBookType(@PathVariable Integer bookTypeId) {
		BookTypes bookTypes= bookTypeService.selectByPrimaryKey(bookTypeId);
        if(bookTypes == null){
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,bookTypes);
	}

	// 修改角色信息
	@PutMapping("/book_type/{bookTypeId}")
	public Object updateBookType(BookTypes bookTypes) {

        if(bookTypeService.updateByPrimaryKey(bookTypes) > 0){
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 新增角色
	@PostMapping("/book_type")
	public Object insertBookType(BookTypes bookTypes) {
        if(bookTypeService.insert(bookTypes) > 0){
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 删除角色
	@DeleteMapping("/book_type/{bookTypeId}")
	public Object deleteBookType(@PathVariable Integer bookTypeId) {
        if(bookTypeService.deleteByPrimaryKey(bookTypeId) > 0){
            return packaging(StateCode.SUCCESS,bookTypeId);
        }
        return packaging(StateCode.FAIL,null);

	}
}
