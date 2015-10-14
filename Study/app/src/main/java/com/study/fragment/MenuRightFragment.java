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
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.study.R;
import com.study.StudyApp;
import com.study.StudyConfig;
import com.study.activity.CourseActivity;
import com.study.activity.MainActivity;
import com.study.adapter.CoursesListAdapter;
import com.study.adapter.RecordListAdapter;
import com.study.db.CoursesDB;
import com.study.model.CoursesModel;
import com.study.model.RecordModel;

import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */
public class MenuRightFragment extends Fragment {

    private View mView;

    @ViewInject(R.id.lv_courses_record_list)
    private ListView lvCoursesRecordList;

    @ViewInject(R.id.tv_no_record_list_tip)
    private TextView tvNoRecordListTip;

    private RecordListAdapter mRecordListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.right_menu, container, false);
        ViewUtils.inject(this, mView); //注入view和事件
        List<RecordModel> list =  CoursesDB.instance().GetRecordData();
        if(list==null||list.size()<=0)
        {
            tvNoRecordListTip.setVisibility(View.VISIBLE);
            lvCoursesRecordList.setVisibility(View.GONE);
        }
        else
        {
            tvNoRecordListTip.setVisibility(View.GONE);
            lvCoursesRecordList.setVisibility(View.VISIBLE);
            mRecordListAdapter = new RecordListAdapter(list);
            lvCoursesRecordList.setAdapter(mRecordListAdapter);
        }
    }

    @OnItemClick({R.id.lv_courses_record_list})
    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mRecordListAdapter!=null)
        {
            RecordModel record = (RecordModel)mRecordListAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            intent.putExtra(StudyConfig.COURSES_ID, record.getId());
            startActivityForResult(intent, StudyConfig.CourseActivity);
        }
    }

    public void RefreshFragment()
    {
        List<RecordModel> list = CoursesDB.instance().GetRecordData();
        if(list!=null && list.size()>0)
        {
            if(lvCoursesRecordList.getAdapter()!=null)
            {
                ((RecordListAdapter)lvCoursesRecordList.getAdapter()).RefreshList(list);
            }
            else
            {
                tvNoRecordListTip.setVisibility(View.GONE);
                lvCoursesRecordList.setVisibility(View.VISIBLE);
                mRecordListAdapter = new RecordListAdapter(list);
                lvCoursesRecordList.setAdapter(mRecordListAdapter);
            }
        }
    }

}
