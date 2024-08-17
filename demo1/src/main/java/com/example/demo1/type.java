package com.example.demo1;

import java.util.List;

public class type {
    private int id;
    private String name;

    public type(){}

    public type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //region SettersAndGetters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregions
}
