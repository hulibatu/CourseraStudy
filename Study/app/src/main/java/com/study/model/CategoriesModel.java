package com.study.model;

import com.lidroid.xutils.db.annotation.NoAutoIncrement;

/**
 * Created by hugo on 15/10/7.
 *
 * Categories Base url: https://api.coursera.org/api/catalog.v1/categories
 *
 * Fields :
 * id: Int - The category’s id
 * name: String - The category’s name.
 * shortName: String - The short name associated with the category.
 * description: Option[String] - The category’s description.
 *
 * Includes :
 * courses - The courses in a category.
 *
 */
public class CategoriesModel {


    @NoAutoIncrement
    private int id;
    private String name;
    private String shortName;
    private String description;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
