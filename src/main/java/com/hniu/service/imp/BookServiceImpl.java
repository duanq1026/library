package com.hniu.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hniu.entity.BookStates;
import com.hniu.entity.Books;
import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;
import com.hniu.mapper.BookStatesMapper;
import com.hniu.mapper.BooksMapper;
import com.hniu.mapper.SearchMapper;
import com.hniu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksMapper booksMapper;

    @Autowired
    private SearchMapper searchMapper;

    @Autowired
    private BookStatesMapper bookStatesMapper;

    @Override
    public void getBookList(PageVo<BookVo> pageBookVo) {
        PageHelper.startPage(pageBookVo.getPage(),pageBookVo.getRows());
        List<BookVo> list = booksMapper.getBookList();
        PageInfo<BookVo> pageInfo = new PageInfo<>(list);
        pageBookVo.setTotal(pageInfo.getTotal());
        pageBookVo.setDateList(list);
    }

    @Override
    public Books getBookById(Integer bookId) {
        return booksMapper.getBookById(bookId);
    }

    @Override
    public int updateBookById(Books books) {
        int resutl = booksMapper.updateBookById(books);
        searchMapper.editBookVo(books);
        return resutl;
    }

    @Override
    public synchronized int  insertBook(Books books, String barCode) {
        String flag = "1";
        if(StringUtils.isEmpty(books.getIsbn())){
            return 0;
        }
        if(StringUtils.isEmpty(barCode)){
            flag = "0";
        }
        int resut;
        Books ibsn= selectByIbsn(books.getIsbn());
        if(ibsn!=null){
            if(flag.equals("1")){
                books = ibsn;
                resut =1;
            }else{
                return 0;
            }
        }else {
            books.setNumber(new Short("0"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
            Date date = new Date();
            String dateString = format.format(date);
            //2018年06月15日 22时46分44秒
            books.setCreateTime(dateString);
            booksMapper.insertBook(books);
            searchMapper.addBookVo(books);
            resut =1;
        }
        if (flag.equals("1")){
            BookStates bookStates = new BookStates();
            bookStates.setBarCode(barCode);
            bookStates.setBookId(books.getBookId());
            bookStates.setState((byte) 0);
            bookStates.setBorrowNumber(new Short("0"));
            bookStatesMapper.insert(bookStates);
        }

        return resut;
    }

    @Override
    public int deleteBookById(Integer bookId) {
        return booksMapper.deleteBookById(bookId);
    }

    @Override
    public Books selectByIbsn(String isbn) {
        return booksMapper.selectByIbsn(isbn);
    }

    @Override
    public Books selectByBarCode(String barCode) {
        return booksMapper.selectByBarCode(barCode);
    }

    @Override
    public void selectRaning(PageVo<BookVo> pageBookVo) {
        PageHelper.startPage(pageBookVo.getPage(),pageBookVo.getRows());
        List<BookVo> list = booksMapper.selectRaning();
        PageInfo<BookVo> pageInfo = new PageInfo<>(list);
        pageBookVo.setTotal(pageInfo.getTotal());
        pageBookVo.setDateList(list);
    }

    @Override
    public Integer isExist(Integer bookId) {
        return bookStatesMapper.isExist(bookId);
    }

}
