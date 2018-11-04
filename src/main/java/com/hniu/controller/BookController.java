package com.hniu.controller;

import com.hniu.constan.Operation;
import com.hniu.constan.StateCode;
import com.hniu.entity.Admin;
import com.hniu.entity.BookStates;
import com.hniu.entity.Books;
import com.hniu.entity.Logs;
import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;
import com.hniu.exception.NotLoginException;
import com.hniu.service.BookService;
import com.hniu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 
 * @ClassName: ReaderController
 * @Description: 读者控制层
 * @author yangf
 * @date 2018年5月2日 上午10:15:24
 * @version 1.0
 */
@RestController
public class BookController extends Base {
	
	@Autowired
    private BookService bookService;

	@Autowired
	private LogService logService;
	
	/**
     * 
     * Title: selectAll
     * Description: 分页查询书列表
     * @param page
     * @return
	 */
	@GetMapping(value="/books")
	public Object findPage(PageVo<BookVo> page) {
		bookService.getBookList(page);
		if(page.getDateList()!=null) {
			return packaging(StateCode.SUCCESS,page);
		}
		return packaging(StateCode.FAIL,null);
	}

    /**
     * 
     * Title: insert
     * Description: 添加书
     * @param books
     * @return
     */
	@PostMapping(value="/books")
	public Object insert(Books books, String barCode,HttpSession session) {
		Admin currentAdmin = (Admin) session.getAttribute("admin");
		if(currentAdmin == null){
			return packaging(StateCode.LOGINAGAIN,null);
		}
		if(bookService.insertBook(books,barCode)>0) {
			logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.ADD,Operation.BOOK ,books.getBookName()));
			return packaging(StateCode.SUCCESS,books);
		}
		return packaging(StateCode.FAIL,null);
	}
    /**
     * 
     * Title: selectByPrimaryKey
     * Description: 根据id查询
     * @return
     */
	@GetMapping(value="/books/{bookId}")
	public Object selectByPrimaryKey(@PathVariable Integer bookId) {
		
		Books books = bookService.getBookById(bookId);
		if(books!=null) {
			return packaging(StateCode.SUCCESS,books);
		}
		return packaging(StateCode.FAIL,null);
	}
    
    /**
     * Title: updateByPrimaryKey
     * Description: 根据id主键修改信息
     * @param books
     * @return
	 */
	@PutMapping(value="/books/{bookId}")
	public Object updateByPrimaryKey(Books books,HttpSession session) {
		Admin currentAdmin = (Admin) session.getAttribute("admin");
		if(currentAdmin == null){
			return packaging(StateCode.LOGINAGAIN,null);
		}
		if(bookService.updateBookById(books)>0) {
			logService.addLog(new Logs( currentAdmin.getAdminId(), Operation.UPD,Operation.BOOK ,books.getBookName()));
			return packaging(StateCode.SUCCESS,books);
		}
		return packaging(StateCode.FAIL,null);
	}
	
	
	/**
     * 
     * Title: selectPageCount
     * Description: TODO 根据inds判断书是否存在
     * @return
	 */
	@GetMapping(value="/books/boolean/{isbn}")
    public Object booleanIsbn(@PathVariable String isbn) {
		Books boots = bookService.selectByIbsn(isbn);
		if(boots != null) {
			return packaging(StateCode.SUCCESS,boots);
		}
		return packaging(StateCode.FAIL,null);
    }


	/**
	 * 根据条形码查书籍信息
	 * @param barCode
	 * @return
	 */
	@GetMapping(value="/books/states/{barCode}")
    public Object selectByRCode(@PathVariable String barCode) {
		Books books = bookService.selectByBarCode(barCode);
		if(books!=null) {
			return packaging(StateCode.SUCCESS,books);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 书的借阅排名
	 * @param pageBookVo
	 * @return
	 */
	@GetMapping(value="/books/group")
	public Object selectRaning(PageVo<BookVo> pageBookVo) {
		bookService.selectRaning(pageBookVo);
		if(!StringUtils.isEmpty(pageBookVo.getDateList())) {
			return packaging(StateCode.SUCCESS,pageBookVo);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 判断图书在管本数
	 * @param bookId
	 * @return
	 */
	@GetMapping(value ="books/count/{bookId}")
	public Object isExist(@PathVariable Integer bookId) {
		Integer count = bookService.isExist(bookId);
		if(count!=null) {
			return packaging(StateCode.SUCCESS,count);
		}
		return packaging(StateCode.FAIL,null);
	}

	/**
	 * 查询图书状态id
	 * @param barCode
	 * @return
	 */
	@GetMapping(value = "books/by_state/{barCode}")
	public Object selectByCode(@PathVariable String barCode) {
		 BookStates bookStates = bookService.selectByCode(barCode);
		if(bookStates!=null) {
			return packaging(StateCode.SUCCESS,bookStates);
		}
		return packaging(StateCode.FAIL,null);
	}
}
