package com.example.demo1;

import java.util.List;

public class book {
    private int ISBN;
    private String name;
    private int pageCount;
    private int type_id;
    private String typeName;
    private int author_id;
    private String authorName;


    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public book(){}

    public book(int ISBN, String name, int pageCount, int type_id, int author_id)
    {
        this.ISBN = ISBN;
        this.name = name;
        this.pageCount = pageCount;
        this.type_id = type_id;
        this.author_id = author_id;
    }

    //region SettersAndGetters
    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getAuthorName() {
        return authorName;
    }

    //endregion
}
