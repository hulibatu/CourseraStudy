package com.study.db;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.study.StudyApp;
import com.study.model.LanguagesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/8.
 */
public class LanguagesDB {

    private static LanguagesDB instance;

    public static LanguagesDB instance() {

        if (instance == null)
            instance = new LanguagesDB();

        return instance;
    }

    public void SaveLanguagesData(ArrayList<LanguagesModel> languagesList) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.saveAll(languagesList);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void DeleteLanguagesData()
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(LanguagesModel.class)>0)
            {
                db.deleteAll(LanguagesModel.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public List<LanguagesModel> GetLanguagesData() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<LanguagesModel> list = null;
        try {
            list = db.findAll(LanguagesModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public ArrayList<Integer> GetLanguagesIDs() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(LanguagesModel.class).groupBy("id").select("id", "count(id)"));
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

    public ArrayList<String> GetLanguagesValues() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(LanguagesModel.class).groupBy("code").select("code", "count(code)"));
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
                list.add(m.getString("code"));
            }
        }
        return list;
    }

}
