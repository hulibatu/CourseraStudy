package com.study.db;

import android.text.TextUtils;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.study.StudyApp;
import com.study.model.CategoriesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/8.
 */
public class CategoriesDB {

    private static CategoriesDB instance;

    public static CategoriesDB instance() {

        if (instance == null)
            instance = new CategoriesDB();

        return instance;
    }

    public void SaveCategoriesData(ArrayList<CategoriesModel> categoriesList) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.saveAll(categoriesList);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void DeleteCategoriesData()
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(CategoriesModel.class)>0)
            {
                db.deleteAll(CategoriesModel.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public List<CategoriesModel> GetCategoriesData() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<CategoriesModel> list = null;
        try {
            list = db.findAll(CategoriesModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return list;
    }

    public ArrayList<Integer> GetCategoriesIDs() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(CategoriesModel.class).groupBy("id").select("id", "count(id)"));
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(dbModels!=null)
        {
            for (DbModel m : dbModels)
            {
                list.add(m.getInt("id"));
            }
        }
        return list;
    }

    public ArrayList<String> GetCategoriesValues() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(CategoriesModel.class).groupBy("shortName").select("shortName", "count(shortName)"));
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        ArrayList<String> list = new ArrayList<String>();
        if(dbModels!=null)
        {
            for (DbModel m : dbModels)
            {
                String value = m.getString("shortName");
                if(!TextUtils.isEmpty(value))
                {
                    list.add(value);
                }
            }
        }
        return list;
    }




}
