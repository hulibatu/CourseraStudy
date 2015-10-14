package com.study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.study.R;
import com.study.StudyConfig;
import com.study.activity.CourseActivity;
import com.study.adapter.CoursesListAdapter;
import com.study.db.CoursesDB;
import com.study.model.CoursesModel;
import com.study.model.RecordModel;
import com.study.net.http.HttpConfig;
import com.study.net.http.request.HttpRequestCourses;
import com.study.net.http.response.HttpResponseCourses;
import com.study.view.circleprogress.CircleProgress;

import java.util.ArrayList;

/**
 * Created by hugo on 15/10/12.
 */
public class CoursesSearchFragment extends Fragment {

    private View mView;

    @ViewInject(R.id.progress)
    private View progress;

    @ViewInject(R.id.lv_courses_list)
    private ListView lvCoursesList;

    @ViewInject(R.id.circle_progress)
    private CircleProgress circleProgress;

    @ViewInject(R.id.tv_courses_search_tip)
    private TextView tvCoursesSearchTip;

    @ViewInject(R.id.et_search_content)
    private EditText etSearchContent;

    private CoursesListAdapter mCoursesListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.courses_search, container, false);
        ViewUtils.inject(this, mView); //注入view和事件
    }

    @OnClick({R.id.btn_search})
    public void ButtonClick(View v) {
        if (v.getId() == R.id.btn_search) {
            String search = etSearchContent.getText().toString().replace(" ", "");
            if (TextUtils.isEmpty(search)) {
                Toast.makeText(getActivity(), "请输入搜索条件", Toast.LENGTH_SHORT).show();
            } else {
                RequestCourses(search);
            }
        }
    }

    @OnItemClick({R.id.lv_courses_list})
    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCoursesListAdapter != null) {
            CoursesModel courses = (CoursesModel) mCoursesListAdapter.getItem(position);
            RecordModel record = new RecordModel();
            record.setId(courses.getId());
            record.setName(TextUtils.isEmpty(courses.getShortName()) ? "未知课程" : courses.getShortName());
            CoursesDB.instance().SaveRecordData(record);
            Intent intent = new Intent(getActivity(), CourseActivity.class);
            intent.putExtra(StudyConfig.COURSES_ID, courses.getId());
            startActivityForResult(intent, StudyConfig.CourseActivity);
        }
    }

    private void RequestCourses(String seatch) {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Courses_Fields_id);
        Fields.add(HttpConfig.Courses_Fields_shortName);
        Fields.add(HttpConfig.Courses_Fields_language);
        Fields.add(HttpConfig.Courses_Fields_smallIcon);
        Fields.add(HttpConfig.Courses_Fields_instructor);

        ArrayList<String> Includes = new ArrayList<String>();
        Includes.add(HttpConfig.IncludesUniversities);
        Includes.add(HttpConfig.IncludesSessions);

        ArrayList<String> values = new ArrayList<String>();
        values.add(seatch);
        HttpRequestCourses.instance().RequestQueryCourses(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                SetCircleProgressGone();
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {

                    HttpResponseCourses data = JSON.parseObject(responseInfo.result, HttpResponseCourses.class);

                    if (data.elements == null || data.elements.size() <= 0) {
                        lvCoursesList.setVisibility(View.GONE);
                        tvCoursesSearchTip.setText(getString(R.string.courses_search_no_results_tip));
                    } else {
                        if (lvCoursesList.getAdapter() != null) {
                            mCoursesListAdapter.RefreshList(data);
                        } else {
                            mCoursesListAdapter = new CoursesListAdapter(data);
                            lvCoursesList.setAdapter(mCoursesListAdapter);
                        }
                    }

                }
            }

            @Override
            public void onStart() {
                SetCircleProgressVisible();
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                HttpReuqestFailure();
            }

        }, Fields, Includes, values);

    }

    private void SetCircleProgressGone() {
        if (getActivity() != null) {
            circleProgress.stopAnim();
            circleProgress.reset();
            progress.setVisibility(View.GONE);
            tvCoursesSearchTip.setVisibility(View.GONE);
            lvCoursesList.setVisibility(View.VISIBLE);
        }
    }

    private void SetCircleProgressVisible() {
        if (getActivity() != null) {
            progress.setVisibility(View.VISIBLE);
            tvCoursesSearchTip.setVisibility(View.GONE);
            lvCoursesList.setVisibility(View.GONE);
            circleProgress.startAnim();
        }
    }

    private void HttpReuqestFailure() {
        if (getActivity() != null) {
            circleProgress.stopAnim();
            circleProgress.reset();
            progress.setVisibility(View.GONE);
            tvCoursesSearchTip.setVisibility(View.VISIBLE);
            tvCoursesSearchTip.setText(getString(R.string.courses_search_no_results_tip));
        }
    }

}
