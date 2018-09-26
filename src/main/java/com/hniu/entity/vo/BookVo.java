package com.hniu.entity.vo;

import java.io.Serializable;

public class BookVo implements Serializable {

    /**
     * 图书id
     */
    private Integer bookId;

    /**
     * 类别id
     */
    private String bookTypeName;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 封面图片
     */
    private String imgPath;


    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String press;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }
}
