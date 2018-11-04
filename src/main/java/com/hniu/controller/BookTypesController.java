package com.hniu.controller;

import com.hniu.constan.Operation;
import com.hniu.constan.StateCode;
import com.hniu.entity.Admin;
import com.hniu.entity.BookTypes;
import com.hniu.entity.Logs;
import com.hniu.service.BookTypeService;
import com.hniu.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class BookTypesController extends Base {

	@Autowired
	private BookTypeService bookTypeService;

	@Autowired
	private LogService logService;

	// 查询所有图书类型
	@GetMapping("/book_type")
	public Object selectAll() {
		List<BookTypes> list= bookTypeService.selectAll();
		if(list == null){
			return packaging(StateCode.SUCCESS,list);
		}
		return packaging(StateCode.FAIL,list);
	}

	// 查询指定id的图书类型
	@GetMapping("/book_type/{bookTypeId}")
	public Object selectBookType(@PathVariable Integer bookTypeId) {
		BookTypes bookTypes= bookTypeService.selectByPrimaryKey(bookTypeId);
        if(bookTypes == null){
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,bookTypes);
	}

	// 修改图书类型信息
	//@RequiresPermissions("book_type:update")
	@PutMapping("/book_type/{bookTypeId}")
	public Object updateBookType(BookTypes bookTypes,HttpSession session) {
		Admin currentAdmin = (Admin) session.getAttribute("admin");
		if(currentAdmin == null){
			return packaging(StateCode.LOGINAGAIN,null);
		}
        if(bookTypeService.updateByPrimaryKey(bookTypes) > 0){
			logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.UPD,Operation.BOOK_TYPE ,bookTypes.toString()));
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 新增图书类型
	//@RequiresPermissions("book_type:insert")
	@PostMapping("/book_type")
	public Object insertBookType(BookTypes bookTypes) {
		Session session = SecurityUtils.getSubject().getSession();
		System.out.println(session.getId());
		Admin currentAdmin = (Admin) session.getAttribute("admin");
		if(currentAdmin == null){
			return packaging(StateCode.LOGINAGAIN,null);
		}
        if(bookTypeService.insert(bookTypes) > 0){
			logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.ADD,Operation.BOOK_TYPE ,bookTypes.toString()));
            return packaging(StateCode.SUCCESS,bookTypes);
        }
        return packaging(StateCode.FAIL,null);
	}

	// 删除角色
	//@RequiresPermissions("book_type:delete")
	@DeleteMapping("/book_type/{bookTypeId}")
	public Object deleteBookType(@PathVariable Integer bookTypeId,HttpSession session) {
		Admin currentAdmin = (Admin) session.getAttribute("admin");
		if(currentAdmin == null){
			return packaging(StateCode.LOGINAGAIN,null);
		}
        if(bookTypeService.deleteByPrimaryKey(bookTypeId) > 0){
			logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.DEL,Operation.BOOK_TYPE ,bookTypeId.toString()));
            return packaging(StateCode.SUCCESS,bookTypeId);
        }
        return packaging(StateCode.FAIL,null);

	}
}
