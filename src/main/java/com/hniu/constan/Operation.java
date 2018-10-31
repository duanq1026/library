package com.hniu.constan;

public enum Operation {
    UPD("修改"),
    DEL("删除"),
    ADD("添加"),
    OPT("其他"),

    BOOK("图书"),
    BOOK_TYPE("图书分类"),
    ADMIN("管理员"),
    BOROWS("借书"),
    RETURN("还书"),
    READER("读者"),
    READER_TYPE("读者类别"),
    ROLE("角色");


    private String val;

    private Operation(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}

