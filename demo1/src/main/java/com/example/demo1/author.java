package com.example.demo1;

import java.util.List;

public class author extends fullName {
    private int id;

    public author() {
    }

    public author( int id,String name, String surName) {
        super(name, surName);
        this.id = id;
    }

    //region SettersAndGetters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //endregion
}

