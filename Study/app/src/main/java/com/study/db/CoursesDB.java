package com.study.db;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.study.StudyApp;
import com.study.model.CoursesModel;
import com.study.model.RecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */
public class CoursesDB {

    private static CoursesDB instance;

    public static CoursesDB instance() {

        if (instance == null)
            instance = new CoursesDB();

        return instance;
    }

    public void SaveCoursesData(ArrayList<CoursesModel> categoriesList) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.saveAll(categoriesList);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void SaveCoursesData(CoursesModel courses) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.save(courses);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void DeleteCoursesData(CoursesModel courses)
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(CoursesModel.class)>0)
            {
                db.delete(courses);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }

    public void DeleteCoursesData()
    {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            if(db.count(CoursesModel.class)>0)
            {
                db.deleteAll(CoursesModel.class);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
    }


    public List<CoursesModel> GetCoursesData() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<CoursesModel> list = null;
        try {
            list = db.findAll(CoursesModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return list;
    }

    public ArrayList<Integer> GetCoursesIDs() {

        DbUtils db = DbUtils.create(StudyApp.instance());
        List<DbModel> dbModels = null;
        try {
            dbModels = db.findDbModelAll(Selector.from(CoursesModel.class).groupBy("id").select("id", "count(id)"));
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

    public void SaveRecordData(RecordModel record) {
        DbUtils db = DbUtils.create(StudyApp.instance());
        try {
            db.save(record);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public List<RecordModel> GetRecordData() {
        DbUtils db = DbUtils.create(StudyApp.instance());
        List<RecordModel> list = null;
        try {
            list = db.findAll(RecordModel.class);
        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return list;
    }

}
