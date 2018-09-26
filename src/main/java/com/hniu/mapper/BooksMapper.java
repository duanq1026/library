package com.hniu.mapper;

import com.hniu.entity.Books;
import com.hniu.entity.vo.BookVo;

import java.util.List;

public interface BooksMapper {
    /**
     * 查询列表
     * @return
     */
    List<BookVo> getBookList();

    /**
     * 查询单个详情
     * @return
     */
    Books getBookById(Integer bookId);

    /**
     * 根据id根据修改单本
     * @param books
     * @return
     */
    int updateBookById (Books books);

    /**
     * 添加单本书籍
     * @param books
     * @return
     */
    int insertBook(Books books) ;

    /**
     * 根据id删除书
     * @param bookId
     * @return
     */
    int deleteBookById(Integer bookId);

    /**
     * 根据idbn查询图书信息
     * @param isbn
     * @return
     */
    Books selectByIbsn(String isbn);

    /**
     * 根据图书id增加书数
     * @return
     */
    int addNumber(Integer bookId);
    /**
     * 根据条形码查询图书信息
     */
    Books selectByBarCode(String barCode);
    /**
     * 书id借阅排名
     */
    List<BookVo> selectRaning();
}