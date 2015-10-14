package com.study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.study.R;
import com.study.StudyConfig;
import com.study.activity.CourseActivity;
import com.study.adapter.CoursesListAdapter;
import com.study.db.CoursesDB;
import com.study.model.CoursesModel;
import com.study.model.RecordModel;

import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */
public class CoursesLikeFragment extends Fragment {

    private View mView;

    @ViewInject(R.id.lv_courses_like_list)
    private ListView lvCoursesList;

    @ViewInject(R.id.tv_no_like_tip)
    private TextView tvNoLikeTip;

    private CoursesListAdapter mCoursesListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        initView(inflater, container);
        return mView;
    }

    @OnItemClick({R.id.lv_courses_like_list})
    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mCoursesListAdapter!=null)
        {
            CoursesModel courses = (CoursesModel)mCoursesListAdapter.getItem(position);
            RecordModel record = new RecordModel();
            record.setId(courses.getId());
            record.setName(TextUtils.isEmpty(courses.getShortName()) ? "未知课程" : courses.getShortName());
            CoursesDB.instance().SaveRecordData(record);
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            intent.putExtra(StudyConfig.COURSES_ID, courses.getId());
            startActivityForResult(intent, StudyConfig.CourseActivity);
        }
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.courses_like, container, false);
        ViewUtils.inject(this, mView); //注入view和事件
        List<CoursesModel> list =  CoursesDB.instance().GetCoursesData();

        if(list==null||list.size()<=0)
        {
            tvNoLikeTip.setVisibility(View.VISIBLE);
            lvCoursesList.setVisibility(View.GONE);
        }
        else
        {
            tvNoLikeTip.setVisibility(View.GONE);
            lvCoursesList.setVisibility(View.VISIBLE);
            mCoursesListAdapter = new CoursesListAdapter(list);
            lvCoursesList.setAdapter(mCoursesListAdapter);
        }
    }

}
