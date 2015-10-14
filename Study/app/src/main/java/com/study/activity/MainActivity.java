package com.study.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.study.R;

import com.study.fragment.CoursesLikeFragment;
import com.study.fragment.CoursesListFragment;
import com.study.fragment.CoursesSearchFragment;
import com.study.fragment.MenuLeftFragment;
import com.study.fragment.MenuRightFragment;

/**
 * Created by hugo on 15/10/9.
 */

@ContentView(R.layout.activity_main)
public class MainActivity extends SlidingFragmentActivity {

    private MenuLeftFragment mMenuLeftFragment;
    private MenuRightFragment mRightMenuFragment;

    private CoursesListFragment mCoursesListFragment;
    private CoursesLikeFragment mCoursesLikeFragment;
    private CoursesSearchFragment mCoursesSearchFragment;

    public static final int Fragment_Type_List = 0;
    public static final int Fragment_Type_Like = 1;
    public static final int Fragment_Type_Search = 2;
    public static final int Fragment_Type_Preference = 3;

    private int mFragmentType = 0;

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    private FragmentManager mFragMgr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSlidingActionBarEnabled(false);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ViewUtils.inject(this);
        mFragMgr = getSupportFragmentManager();
        showFragments(String.valueOf(Fragment_Type_List),false);
        // 初始化SlideMenu
        initMenu();
    }

    private void initMenu() {
        mMenuLeftFragment = new MenuLeftFragment();
        setBehindContentView(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.id_left_menu_frame, mMenuLeftFragment).commit();
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //menu.setBehindWidth()
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        // menu.setBehindScrollScale(1.0f);
        menu.setSecondaryShadowDrawable(R.drawable.shadow);
        //设置右边（二级）侧滑菜单
        menu.setSecondaryMenu(R.layout.right_menu_frame);
        menu.setSecondaryOnOpenListner(new SlidingMenu.OnOpenListener() {
            @Override
            public void onOpen() {
                if (mRightMenuFragment != null) {
                    mRightMenuFragment.RefreshFragment();
                }
            }
        });
        mRightMenuFragment = new MenuRightFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.id_right_menu_frame, mRightMenuFragment).commit();
    }

    public void showLeftMenu(View view) {
        getSlidingMenu().showMenu();
    }

    public void showRightMenu(View view) {
        getSlidingMenu().showSecondaryMenu();
    }

    public void closeRightMenu() {
        getSlidingMenu().showSecondaryMenu(false);
    }

    public void RefreshFragment(int position)
    {
        if(mFragmentType!=position)
        {
            mFragmentType = position;
            showFragments(String.valueOf(position),false);
        }
        getSlidingMenu().toggle();
    }

    private void showFragments(String tag, boolean needback){

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
            case Fragment_Type_List:
                tvTitle.setText("课程");
                if(mCoursesListFragment == null)
                {
                    mCoursesListFragment = new CoursesListFragment();
                }
                return mCoursesListFragment;
            case Fragment_Type_Like:
                tvTitle.setText("喜欢");
                if(mCoursesLikeFragment == null)
                {
                    mCoursesLikeFragment = new CoursesLikeFragment();
                }
                return mCoursesLikeFragment;
            case Fragment_Type_Search:
                tvTitle.setText("搜索");
                if(mCoursesSearchFragment == null)
                {
                    mCoursesSearchFragment = new CoursesSearchFragment();
                }
                return mCoursesSearchFragment;
            default:
                break;
        }
        return null;
    }

    public void RefreshCoursesList()
    {
        if(mFragmentType == Fragment_Type_List && mCoursesListFragment!=null)
        {
            mCoursesListFragment.RefreshCoursesList();
        }
        else
        {
            mFragmentType = Fragment_Type_List;
            showFragments(String.valueOf(Fragment_Type_List),false);
        }
        getSlidingMenu().toggle();
    }


}
