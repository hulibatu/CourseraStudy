package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/8.
 */
public class LanguagesModel {

    @NoAutoIncrement
    private int id;
    private String code;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
