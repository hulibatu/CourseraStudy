package com.study.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.study.R;
import com.study.StudyApp;
import com.study.activity.CourseActivity;
import com.study.model.CoursesModel;
import com.study.model.InstructorsModel;
import com.study.model.UniversitiesModel;
import com.study.net.http.HttpConfig;
import com.study.net.http.request.HttpRequestCourses;
import com.study.net.http.request.HttpRequestInstructors;
import com.study.net.http.request.HttpRequestUniversities;
import com.study.net.http.response.HttpResponseCourses;
import com.study.net.http.response.HttpResponseInstructors;
import com.study.net.http.response.HttpResponseUniversities;
import com.study.util.BitmapHelp;
import com.study.view.circleprogress.CircleProgress;

import java.util.ArrayList;

/**
 * Created by hugo on 15/10/14.
 */
public class CourseInformationFragment extends Fragment {

    private View mView;

    @ViewInject(R.id.iv_university_icon)
    private ImageView ivUniversityIcon;
    @ViewInject(R.id.iv_instructors_icon)
    private ImageView ivInstructorsIcon;
    @ViewInject(R.id.iv_course_icon)
    private ImageView ivCourseIcon;

    @ViewInject(R.id.tv_university_name)
    private TextView tvUniversityName;
    @ViewInject(R.id.tv_course_name_and_language)
    private TextView tvCourseNameAndLanguage;
    @ViewInject(R.id.tv_instructors_name)
    private TextView tvInstructorsName;
    @ViewInject(R.id.tv_instructors_department)
    private TextView tvInstructorsDepartment;
    @ViewInject(R.id.tv_course_description)
    private TextView tvCourseDescription;
    @ViewInject(R.id.rl_course_information)
    private RelativeLayout rlCourseInformation;

    @ViewInject(R.id.progress)
    private View progress;

    @ViewInject(R.id.circle_progress)
    private CircleProgress circleProgress;

    private String mId;
    private HttpResponseCourses mHttpResponseCourses;
    private HttpResponseInstructors mHttpResponseInstructors;
    private HttpResponseUniversities mHttpResponseUniversities;

    public BitmapUtils bitmapUtils;

    public CourseInformationFragment(String id) {
        mId = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initView(inflater, container);
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {

        mView = inflater.inflate(R.layout.course_information, container, false);
        ViewUtils.inject(this, mView); //注入view和事件

        bitmapUtils = BitmapHelp.getBitmapUtils(StudyApp.instance());

        if (mHttpResponseCourses == null) {
            RequestCourses();
        } else {
            SetCoursesView(mHttpResponseCourses);
        }
    }

    private void SetCoursesView(HttpResponseCourses courses) {

        if (courses != null) {

            if (courses.elements != null && courses.elements.size() > 0) {

                CoursesModel coursesModel = courses.elements.get(0);
                bitmapUtils.configDefaultLoadingImage(R.drawable.picture2);
                bitmapUtils.configDefaultLoadFailedImage(R.drawable.picture2);
                bitmapUtils.display(ivCourseIcon, coursesModel.getPhoto());
                String name_language = coursesModel.getName();
                if (TextUtils.isEmpty(coursesModel.getLanguage())) {
                    name_language += " · 未知语言";
                } else {
                    name_language += " · " + coursesModel.getLanguage().toUpperCase();
                }
                tvCourseNameAndLanguage.setText(name_language);
                tvCourseDescription.setText(Html.fromHtml(coursesModel.getCourseFormat()));

                if (mHttpResponseUniversities == null) {
                    RequestUniversities(String.valueOf(coursesModel.getLinks().getUniversities().get(0)));
                } else {
                    SetUniversities(mHttpResponseUniversities);
                }

                if(coursesModel.getLinks().getInstructors()!=null && coursesModel.getLinks().getInstructors().size()>0)
                {
                    if (mHttpResponseInstructors == null) {
                        RequestInstructors(String.valueOf(coursesModel.getLinks().getInstructors().get(0)));
                    } else {
                        SetInstructors(mHttpResponseInstructors);
                    }
                }

                if(getActivity()!=null)
                {
                    ((CourseActivity)getActivity()).setSessionsModelList(coursesModel.getLinks().getSessions());
                }

            }
        }
        SetCircleProgressGone();
    }

    private void SetUniversities(HttpResponseUniversities universities) {
        if (universities != null) {
            if (universities.elements != null && universities.elements.size() > 0) {
                UniversitiesModel universitiesModel = universities.elements.get(0);
                bitmapUtils.configDefaultLoadingImage(R.drawable.picture);
                bitmapUtils.configDefaultLoadFailedImage(R.drawable.picture);
                bitmapUtils.display(ivUniversityIcon, universitiesModel.getLogo());
                tvUniversityName.setText(universitiesModel.getName());
            }
        }
        SetCircleProgressGone();
    }

    private void SetInstructors(HttpResponseInstructors instructors) {
        if (instructors != null) {
            if (instructors.elements != null && instructors.elements.size() > 0) {
                InstructorsModel instructorsModel = instructors.elements.get(0);
                bitmapUtils.configDefaultLoadingImage(R.drawable.picture);
                bitmapUtils.configDefaultLoadFailedImage(R.drawable.picture);
                bitmapUtils.display(ivInstructorsIcon, instructorsModel.getPhoto150());
                String name = String.format("%s%s", instructorsModel.getPrefixName(), TextUtils.isEmpty(instructorsModel.getFirstName()) ? "未知" : instructorsModel.getFirstName());
                if (!TextUtils.isEmpty(instructorsModel.getTitle())) {
                    name += (" · " + instructorsModel.getTitle());
                }
                tvInstructorsName.setText(name);
                tvInstructorsDepartment.setText(instructorsModel.getDepartment());
            }
        }

        SetCircleProgressGone();

    }

    private void RequestCourses() {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Courses_Fields_id);
        Fields.add(HttpConfig.Courses_Fields_name);
        Fields.add(HttpConfig.Courses_Fields_language);
        Fields.add(HttpConfig.Courses_Fields_photo);
        Fields.add(HttpConfig.Courses_Fields_instructor);
        Fields.add(HttpConfig.Courses_Fields_courseFormat);

        ArrayList<String> Includes = new ArrayList<String>();
        Includes.add(HttpConfig.IncludesUniversities);
        Includes.add(HttpConfig.IncludesSessions);
        Includes.add(HttpConfig.IncludesInstructors);

        ArrayList<String> IDs = new ArrayList<String>();
        IDs.add(mId);

        HttpRequestCourses.instance().RequestCourses(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    mHttpResponseCourses = JSON.parseObject(responseInfo.result, HttpResponseCourses.class);
                    SetCoursesView(mHttpResponseCourses);
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

        }, Fields, Includes, IDs);

    }

    private void RequestUniversities(String universities_id) {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Universities_Fields_id);
        Fields.add(HttpConfig.Universities_Fields_shortName);
        Fields.add(HttpConfig.Universities_Fields_name);
        Fields.add(HttpConfig.Universities_Fields_logo);

        ArrayList<String> IDs = new ArrayList<String>();
        IDs.add(universities_id);

        HttpRequestUniversities.instance().RequestUniversities(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    mHttpResponseUniversities = JSON.parseObject(responseInfo.result, HttpResponseUniversities.class);
                    SetUniversities(mHttpResponseUniversities);
                }
            }

            @Override
            public void onStart() {
            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }

        }, Fields, null, IDs);

    }


    private void RequestInstructors(String instructors_id) {

        ArrayList<String> Fields = new ArrayList<String>();

        Fields.add(HttpConfig.Instructors_Fields_id);
        Fields.add(HttpConfig.Instructors_Fields_firstName);
        Fields.add(HttpConfig.Instructors_Fields_prefixName);
        Fields.add(HttpConfig.Instructors_Fields_title);
        Fields.add(HttpConfig.Instructors_Fields_photo150);

        ArrayList<String> IDs = new ArrayList<String>();
        IDs.add(instructors_id);

        HttpRequestInstructors.instance().RequestInstructors(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    mHttpResponseInstructors = JSON.parseObject(responseInfo.result, HttpResponseInstructors.class);
                    SetInstructors(mHttpResponseInstructors);
                }
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure(HttpException error, String msg) {
            }

        }, Fields, null, IDs);

    }


    private void SetCircleProgressGone() {

        if(getActivity()!=null)
        {
            if (mHttpResponseCourses != null && mHttpResponseUniversities != null) {
                circleProgress.stopAnim();
                circleProgress.reset();
                progress.setVisibility(View.GONE);
                rlCourseInformation.setVisibility(View.VISIBLE);
                ((CourseActivity) getActivity()).setChangePageFlag(true);
            }
        }

    }

    private void SetCircleProgressVisible() {
        if(getActivity()!=null)
        {
            progress.setVisibility(View.VISIBLE);
            rlCourseInformation.setVisibility(View.GONE);
            circleProgress.startAnim();
        }
    }

    private void HttpReuqestFailure() {
        if(getActivity()!=null)
        {
            circleProgress.stopAnim();
            circleProgress.reset();
            progress.setVisibility(View.GONE);
            rlCourseInformation.setVisibility(View.VISIBLE);
            Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("请求失败").setMessage("请求失败,点击确定重新获取")
                    .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestCourses();
                        }
                    })
                    .setPositiveButton("返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    }).create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

}
