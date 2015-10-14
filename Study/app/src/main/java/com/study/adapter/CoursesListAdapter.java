package com.study.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.study.R;
import com.study.StudyApp;
import com.study.db.CoursesDB;
import com.study.model.CoursesModel;
import com.study.model.SessionsModel;
import com.study.model.UniversitiesModel;
import com.study.net.http.request.HttpRequestCourses;
import com.study.net.http.response.HttpResponseCourses;
import com.study.util.BitmapHelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */
public class CoursesListAdapter extends BaseAdapter {

    private enum ListType {
        CoursesList,
        CoursesLikeList
    }

    private ListType type;

    public BitmapUtils bitmapUtils;

    public List<CoursesModel> mDataList;
    public HashMap<Integer, String> mUniversity = new HashMap<Integer, String>();
    public List<SessionsModel> mSession;
    private ArrayList<Integer> mIDList;

    public CoursesListAdapter(List<CoursesModel> data) {
        type = ListType.CoursesLikeList;
        bitmapUtils = BitmapHelp.getBitmapUtils(StudyApp.instance());
        bitmapUtils.configDefaultLoadingImage(R.drawable.picture);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.picture);
        mDataList = data;
        mIDList = CoursesDB.instance().GetCoursesIDs();
    }

    public CoursesListAdapter(HttpResponseCourses courses) {

        type = ListType.CoursesList;
        bitmapUtils = BitmapHelp.getBitmapUtils(StudyApp.instance());
        bitmapUtils.configDefaultLoadingImage(R.drawable.picture);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.picture);

        mDataList = courses.elements;
        if (courses.linked.getUniversities() != null) {
            for (UniversitiesModel u : courses.linked.getUniversities()) {
                mUniversity.put(u.getId(), u.getShortName());
            }
        }
        mSession = courses.linked.getSessions();
        mIDList = CoursesDB.instance().GetCoursesIDs();
    }

    public void RefreshList(HttpResponseCourses courses) {
        mDataList = courses.elements;
        mUniversity.clear();
        if (courses.linked.getUniversities() != null) {
            for (UniversitiesModel u : courses.linked.getUniversities()) {
                mUniversity.put(u.getId(), u.getShortName());
            }
        }
        mSession = courses.linked.getSessions();
        mIDList = CoursesDB.instance().GetCoursesIDs();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        if (mDataList != null)
            return mDataList.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {

        if (mDataList != null)
            return mDataList.get(position);

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final CoursesModel courses = mDataList.get(position);
        final ViewHolder holder;

        if (convertView == null) {

            LayoutInflater mInflater = LayoutInflater.from(StudyApp.instance());
            convertView = mInflater.inflate(R.layout.courses_list_item, null);
            holder = new ViewHolder();
            holder.iv_course_icon = (ImageView) convertView.findViewById(R.id.iv_course_icon);
            holder.tv_course_name = (TextView) convertView.findViewById(R.id.tv_course_name);
            holder.tv_instructor_name = (TextView) convertView.findViewById(R.id.tv_instructor_name);
            holder.tv_course_university = (TextView) convertView.findViewById(R.id.tv_course_university);
            holder.tv_class = (TextView) convertView.findViewById(R.id.tv_class);
            holder.iv_like_btn = (ImageButton) convertView.findViewById(R.id.iv_like_btn);

            holder.iv_like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mIDList.contains(courses.getId())) {
                        holder.iv_like_btn.setBackground(StudyApp.instance().getDrawable(R.drawable.heart_1));
                        mIDList.remove(mIDList.indexOf(courses.getId()));
                        CoursesDB.instance().DeleteCoursesData(courses);
                    } else {
                        holder.iv_like_btn.setBackground(StudyApp.instance().getDrawable(R.drawable.heart_2));
                        mIDList.add(courses.getId());
                        courses.setUniversityName(holder.tv_course_university.getText().toString());
                        courses.setClassTime((courses.getLinks().getSessions() != null && courses.getLinks().getSessions().size() > 0) ? (courses.getLinks().getSessions().size()) : 1);
                        CoursesDB.instance().SaveCoursesData(courses);
                    }

                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_course_name.setText(TextUtils.isEmpty(courses.getShortName()) ? "未知课程" : courses.getShortName());
        holder.tv_instructor_name.setText(TextUtils.isEmpty(courses.getInstructor()) ? "未知讲师" : courses.getInstructor());

        String university_name = "未知大学";
        if (type == ListType.CoursesList) {
            String class_time = (courses.getLinks().getSessions() != null && courses.getLinks().getSessions().size() > 0) ? (courses.getLinks().getSessions().size() + "课时") : "1课时";
            holder.tv_class.setText(class_time);

            if (courses.getLinks().getUniversities() != null && courses.getLinks().getUniversities().size() > 0) {
                int university_id = courses.getLinks().getUniversities().get(0);
                if (mUniversity.containsKey(university_id)) {
                    university_name = mUniversity.get(university_id);
                }
            }
        } else if (type == ListType.CoursesLikeList) {
            university_name = courses.getUniversityName();
        }

        holder.tv_course_university.setText(university_name.toUpperCase());

        bitmapUtils.display(holder.iv_course_icon, courses.getSmallIcon());

        if (mIDList.contains(courses.getId())) {
            holder.iv_like_btn.setBackground(StudyApp.instance().getDrawable(R.drawable.heart_2));
        } else {
            holder.iv_like_btn.setBackground(StudyApp.instance().getDrawable(R.drawable.heart_1));
        }

        return convertView;
    }

    class ViewHolder {
        ImageView iv_course_icon;
        TextView tv_course_name;
        TextView tv_course_university;
        TextView tv_instructor_name;
        TextView tv_class;
        ImageButton iv_like_btn;
    }

}
