package com.study.db;

import android.text.TextUtils;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.study.StudyApp;
import com.study.model.InstructorsModel;
import com.study.model.LanguagesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/8.
 */
public class InstructorsDB {

    private static InstructorsDB instance;

    public static InstructorsDB instance() {

        if (instance == null)
            instance = new InstructorsDB();

        return instance;
    }

    public void SaveInstructorsData(ArrayList<InstructorsModel> instructorsList) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.saveAll(instructorsList);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void DeleteInstructorsData()
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(InstructorsModel.class)>0)
            {
                db.deleteAll(InstructorsModel.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public List<InstructorsModel> GetInstructorsData() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<InstructorsModel> list = null;
        try {
            list = db.findAll(InstructorsModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

    public ArrayList<Integer> GetInstructorsIDs() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(InstructorsModel.class).groupBy("id").select("id", "count(id)"));
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

    public ArrayList<String> GetInstructorsValues() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(InstructorsModel.class).groupBy("firstName").select("firstName", "count(firstName)"));
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
                String value = m.getString("firstName");
                if(!TextUtils.isEmpty(value))
                {
                    list.add(value);
                }
            }
        }
        return list;
    }






}
