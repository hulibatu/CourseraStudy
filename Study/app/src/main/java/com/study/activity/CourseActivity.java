package com.study.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.study.R;
import com.study.StudyConfig;
import com.study.fragment.CourseCalssTimeFragment;
import com.study.fragment.CourseInformationFragment;

import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */

@ContentView(R.layout.course_layout)
public class CourseActivity extends FragmentActivity{

    public static final int Fragment_Type_Information = 0;
    public static final int Fragment_Type_Calss_Time = 1;

    private CourseInformationFragment mCourseInformationFragment;
    private CourseCalssTimeFragment mCourseCalssTimeFragment;

    private FragmentManager mFragMgr;

    private int mFragmentType = -1;

    private int mCourseId;

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    public void setChangePageFlag(boolean flag) {
        this.mChangePageFlag = flag;
    }

    private boolean mChangePageFlag = false;

    public void setSessionsModelList(List<Integer> list) {
        this.mSessionsModelList = list;
    }
    private List<Integer> mSessionsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCourseId = getIntent().getIntExtra(StudyConfig.COURSES_ID,0);

        ViewUtils.inject(this);

        mFragMgr = getSupportFragmentManager();
        showFragments(String.valueOf(Fragment_Type_Information), false);

    }

    @OnClick({R.id.btn_information,R.id.btn_calss_time})
    public void ButtonClick(View v) {

        if(!mChangePageFlag)
            return;

        switch (v.getId()) {
            case R.id.btn_information:
                tvTitle.setText("课程介绍");
                showFragments(String.valueOf(Fragment_Type_Information), false);
                break;
            case R.id.btn_calss_time:
                tvTitle.setText("课时");
                showFragments(String.valueOf(Fragment_Type_Calss_Time), false);
                break;

        }
    }

    private void showFragments(String tag, boolean needback){

        int type = Integer.valueOf(tag).intValue();

        if(type == mFragmentType)
        {
            return;
        }

        FragmentTransaction trans = mFragMgr.beginTransaction();
        if(needback){
            trans.add(R.id.fragment_container, getFragmentByTag(tag), tag);
            trans.addToBackStack(tag);
        }else{
            trans.replace(R.id.fragment_container, getFragmentByTag(tag), tag);
        }
        trans.commit();
    }

    private Fragment getFragmentByTag(String tag){

        switch (Integer.valueOf(tag).intValue())
        {
            case Fragment_Type_Information:
                if(mCourseInformationFragment == null)
                {
                    mCourseInformationFragment = new CourseInformationFragment(String.valueOf(mCourseId));
                }
                return mCourseInformationFragment;
            case Fragment_Type_Calss_Time:
                if(mCourseCalssTimeFragment == null)
                {
                    mCourseCalssTimeFragment = new CourseCalssTimeFragment(mSessionsModelList);
                }
                return mCourseCalssTimeFragment;
            default:
                break;
        }
        return null;
    }

}
