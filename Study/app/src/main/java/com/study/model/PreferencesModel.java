package com.study.model;

import com.study.PreferencesType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/6.
 *
 * 偏好选择
 *
 */
public class PreferencesModel {

    // 偏好类型
    private PreferencesType type;
    // 语言集合
    private List<LanguagesModel> languagesList;
    // 类型数据集合
    private List<CategoriesModel> categoriesList;
    // 讲师数据集合
    private List<InstructorsModel> instructorsList;
    // 大学数据集合
    private List<UniversitiesModel> universitiesList;

    public PreferencesType getType() {
        return type;
    }

    public void setType(PreferencesType type) {
        this.type = type;
    }

    public List<LanguagesModel> getLanguagesList() {
        return languagesList;
    }

    public void setLanguagesList(List<LanguagesModel> languagesList) {
        this.languagesList = languagesList;
    }

    public List<CategoriesModel> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<CategoriesModel> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<InstructorsModel> getInstructorsList() {
        return instructorsList;
    }

    public void setInstructorsList(List<InstructorsModel> instructorsList) {
        this.instructorsList = instructorsList;
    }

    public List<UniversitiesModel> getUniversitiesList() {
        return universitiesList;
    }

    public void setUniversitiesList(List<UniversitiesModel> universitiesList) {
        this.universitiesList = universitiesList;
    }
}
