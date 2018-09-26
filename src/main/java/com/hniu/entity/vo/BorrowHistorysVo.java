package com.hniu.entity.vo;

import com.hniu.entity.*;

public class BorrowHistorysVo extends BorrowHistorys {
    private Readers readers;
    private BookStates bookStates;
    private Books books;
    private Borrows borrows;

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

    public Borrows getBorrows() {
        return borrows;
    }

    public void setBorrows(Borrows borrows) {
        this.borrows = borrows;
    }

    public Readers getReaders() {
        return readers;
    }

    public void setReaders(Readers readers) {
        this.readers = readers;
    }
}
