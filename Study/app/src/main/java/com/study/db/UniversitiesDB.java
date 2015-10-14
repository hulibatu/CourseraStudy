package com.study.db;

import android.text.TextUtils;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.study.StudyApp;
import com.study.model.UniversitiesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/8.
 */
public class UniversitiesDB {

    private static UniversitiesDB instance;

    public static UniversitiesDB instance() {

        if (instance == null)
            instance = new UniversitiesDB();

        return instance;
    }

    public void SaveUniversitiesDB(ArrayList<UniversitiesModel> universitiesList) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.saveAll(universitiesList);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void DeleteUniversitiesDB()
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(UniversitiesModel.class)>0)
            {
                db.deleteAll(UniversitiesModel.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public List<UniversitiesModel> GetUniversitiesDB() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<UniversitiesModel> list = null;
        try {
            list = db.findAll(UniversitiesModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public ArrayList<Integer> GetUniversitiesIDs() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(UniversitiesModel.class).groupBy("id").select("id", "count(id)"));
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

    public ArrayList<String> GetUniversitiesValues() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(UniversitiesModel.class).groupBy("shortName").select("shortName", "count(shortName)"));
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
