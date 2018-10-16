package com.hniu.entity.vo;

import com.hniu.entity.*;

public class BorrowHistorysVo extends BorrowHistorys {
    private Readers readers;

    private Books books;

    private BookStates bookStates;

    public BookStates getBookStates() {
        return bookStates;
    }

    public void setBookStates(BookStates bookStates) {
        this.bookStates = bookStates;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Readers getReaders() {
        return readers;
    }

    public void setReaders(Readers readers) {
        this.readers = readers;
    }
}
