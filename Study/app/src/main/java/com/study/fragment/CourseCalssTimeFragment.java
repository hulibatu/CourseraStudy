package com.study.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.study.R;
import com.study.adapter.CourseCalssTimeAdapter;
import com.study.adapter.PreferencesSelectAdapter;
import com.study.model.SessionsModel;
import com.study.net.http.HttpConfig;
import com.study.net.http.request.HttpRequestCategories;
import com.study.net.http.request.HttpRequestSessions;
import com.study.net.http.response.HttpResponseCategories;
import com.study.net.http.response.HttpResponseSessions;
import com.study.view.circleprogress.CircleProgress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hugo on 15/10/14.
 */
public class CourseCalssTimeFragment extends Fragment {

    private View mView;

    @ViewInject(R.id.circle_progress)
    private CircleProgress circleProgress;

    @ViewInject(R.id.progress)
    private View progress;

    @ViewInject(R.id.lv_sessions_list)
    private ListView lvSessionsList;

    private HttpResponseSessions mHttpResponseSessions;
    private CourseCalssTimeAdapter mCourseCalssTimeAdapter;

    private List<Integer> mSessionsModelList;
    public CourseCalssTimeFragment(List<Integer> list)
    {
        mSessionsModelList = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        initView(inflater, container);
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.course_class_time, container, false);
        ViewUtils.inject(this, mView); //注入view和事件
        if(mSessionsModelList!=null && mSessionsModelList.size()>0)
        {
            if(mHttpResponseSessions == null)
            {
                RequestSessions();
            }
            else
            {
                SetListView(mHttpResponseSessions);
            }
        }
        else
        {
            Toast.makeText(getActivity(),"没有相应课程",Toast.LENGTH_SHORT).show();
        }
    }

    private void SetListView(HttpResponseSessions sessions)
    {
        if(getActivity()!=null)
        {
            SetCircleProgressGone();
            mCourseCalssTimeAdapter = new CourseCalssTimeAdapter(sessions.elements);
            lvSessionsList.setAdapter(mCourseCalssTimeAdapter);
        }
    }

    private void RequestSessions() {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Sessions_Fields_id);
        Fields.add(HttpConfig.Sessions_Fields_name);
        Fields.add(HttpConfig.Sessions_Fields_durationString);

        ArrayList<String> IDs = new ArrayList<String>();
        if(mSessionsModelList!=null)
        {
            for (int id : mSessionsModelList) {
                IDs.add(String.valueOf(id));
            }
        }

        HttpRequestSessions.instance().RequestSessions(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if(getActivity()!=null)
                {
                    SetCircleProgressGone();
                    if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                        mHttpResponseSessions = JSON.parseObject(responseInfo.result, HttpResponseSessions.class);
                        Collections.sort(mHttpResponseSessions.elements, new Comparator<SessionsModel>() {
                            public int compare(SessionsModel arg0, SessionsModel arg1) {
                                Integer name0 = Integer.valueOf(arg0.getName());
                                Integer name1 = Integer.valueOf(arg1.getName());
                                return name0.compareTo(name1);
                            }
                        });
                        SetListView(mHttpResponseSessions);
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

        }, Fields, null, IDs);
    }

    private void SetCircleProgressGone() {
        if(getActivity()!=null)
        {
            circleProgress.stopAnim();
            circleProgress.reset();
            progress.setVisibility(View.GONE);
            lvSessionsList.setVisibility(View.VISIBLE);
        }
    }

    private void SetCircleProgressVisible() {
        if(getActivity()!=null)
        {
            progress.setVisibility(View.VISIBLE);
            lvSessionsList.setVisibility(View.GONE);
            circleProgress.startAnim();
        }
    }

    private void HttpReuqestFailure() {

        if(getActivity()!=null)
        {
            circleProgress.stopAnim();
            circleProgress.reset();
            progress.setVisibility(View.GONE);
            Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("请求失败").setMessage("请求失败,点击确定重新获取")
                    .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestSessions();
                        }
                    })
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }

    }

}
