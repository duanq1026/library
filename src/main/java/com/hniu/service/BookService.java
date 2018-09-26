package com.hniu.service;


import com.hniu.entity.Books;
import com.hniu.entity.vo.BookVo;
import com.hniu.entity.vo.PageVo;

public interface BookService {
    /**
     * 查询列表
     * @return
     */
    void getBookList(PageVo<BookVo> pageBookVo);

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
    int  insertBook(Books books, String barCode) ;

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
     * 根据条形码查询图书信息
     */
    Books selectByBarCode(String barCode);

    /**
     * 书id借阅排名
     */
    void selectRaning(PageVo<BookVo> pageBookVo);

    /**
     * 判断图书有几本在图书馆中
     * @param bookId
     * @return
     */
    Integer isExist(Integer bookId);
}
