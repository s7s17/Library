package com.example.demo1;

public class fullName {
    private String name;
    private String surName;

    public fullName(){}
    public fullName(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    //region SettersAndGetters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    //endregion
}
