package com.hniu.entity.vo;

import com.hniu.entity.BookStates;
import com.hniu.entity.Books;
import com.hniu.entity.Borrows;
import com.hniu.entity.Readers;


public class BorrowsVo extends Borrows {
    private Readers readers;

    private BookStates bookStates;

    private Books books;

    private Integer daysum;

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public Integer getDaysum() {
        return daysum;
    }

    public void setDaysum(Integer daysum) {
        this.daysum = daysum;
    }


    public BookStates getBookStates() {
        return bookStates;
    }

    public void setBookStates(BookStates bookStates) {
        this.bookStates = bookStates;
    }

    public Readers getReaders() {
        return readers;
    }

    public void setReaders(Readers readers) {
        this.readers = readers;
    }
}
