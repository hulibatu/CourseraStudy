package com.study.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.study.PreferencesType;
import com.study.R;
import com.study.StudyConfig;
import com.study.adapter.PreferencesSelectAdapter;
import com.study.db.CategoriesDB;
import com.study.db.InstructorsDB;
import com.study.db.LanguagesDB;
import com.study.db.UniversitiesDB;
import com.study.model.CategoriesModel;
import com.study.model.InstructorsModel;
import com.study.model.LanguagesModel;
import com.study.model.PreferencesModel;
import com.study.model.UniversitiesModel;
import com.study.net.http.HttpConfig;
import com.study.net.http.request.HttpRequestCategories;
import com.study.net.http.request.HttpRequestInstructors;
import com.study.net.http.request.HttpRequestUniversities;
import com.study.net.http.response.HttpResponseCategories;
import com.study.net.http.response.HttpResponseInstructors;
import com.study.net.http.response.HttpResponseUniversities;
import com.study.util.Utils;
import com.study.view.circleprogress.CircleProgress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hugo on 15/10/6.
 * <p/>
 * 偏好List
 */
@ContentView(R.layout.preferences_select)
public class PreferencesSelectActivity extends Activity implements Handler.Callback {

    private static final int REQUEST_DATA = 10000;

    private PreferencesSelectAdapter mPreferencesSelectAdapter;
    private PreferencesType mPreferencesType;
    private PreferencesModel mPreferencesModel;

    @ViewInject(R.id.btn_confirm)
    private Button btnConfirm;

    @ViewInject(R.id.lv_p_select)
    private ListView lvPreSelect;

    @ViewInject(R.id.circle_progress)
    private CircleProgress circleProgress;

    @ViewInject(R.id.progress)
    private View progress;

    @ViewInject(R.id.failure)
    private View failure;

    @ViewInject(R.id.tv_title)
    private TextView tvTitle;

    @ViewInject(R.id.rl_view_group)
    private View rlViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        circleProgress.startAnim();
        mPreferencesType = PreferencesType.values()[getIntent().getIntExtra(StudyConfig.PREFERENCES_TYPE, 0)];
        mPreferencesModel = new PreferencesModel();
        mPreferencesModel.setType(mPreferencesType);
        StartRequest();
    }

    @OnClick({R.id.btn_confirm,R.id.btn_again})
    public void ButtonClick(View v) {

        if (v.getId() == R.id.btn_confirm) {

            if (mPreferencesSelectAdapter != null) {
                HashMap<Integer, Boolean> selectedMap = mPreferencesSelectAdapter.GetSelectedMap();
                if (selectedMap == null || selectedMap.size() <= 0) {
                    Toast.makeText(PreferencesSelectActivity.this, "请选择条件", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch (mPreferencesType) {
                    case Languages:
                        ArrayList<LanguagesModel> languagesList = new ArrayList<LanguagesModel>();
                        for (Integer posintion : selectedMap.keySet()) {
                            if (selectedMap.get(posintion)) {
                                languagesList.add(mPreferencesModel.getLanguagesList().get(posintion));
                            }
                        }
                        LanguagesDB.instance().DeleteLanguagesData();
                        LanguagesDB.instance().SaveLanguagesData(languagesList);
                        break;
                    case Instructors:
                        ArrayList<InstructorsModel> instructorsList = new ArrayList<InstructorsModel>();
                        for (Integer posintion : selectedMap.keySet()) {
                            if (selectedMap.get(posintion)) {
                                instructorsList.add(mPreferencesModel.getInstructorsList().get(posintion));
                            }
                        }
                        InstructorsDB.instance().DeleteInstructorsData();
                        InstructorsDB.instance().SaveInstructorsData(instructorsList);
                        break;
                    case Universities:
                        ArrayList<UniversitiesModel> universitiesList = new ArrayList<UniversitiesModel>();
                        for (Integer posintion : selectedMap.keySet()) {
                            if (selectedMap.get(posintion)) {
                                universitiesList.add(mPreferencesModel.getUniversitiesList().get(posintion));
                            }
                        }
                        UniversitiesDB.instance().DeleteUniversitiesDB();
                        UniversitiesDB.instance().SaveUniversitiesDB(universitiesList);
                        break;
                    case Categories:
                        ArrayList<CategoriesModel> categoriesList = new ArrayList<CategoriesModel>();
                        for (Integer posintion : selectedMap.keySet()) {
                            if (selectedMap.get(posintion)) {
                                categoriesList.add(mPreferencesModel.getCategoriesList().get(posintion));
                            }
                        }
                        CategoriesDB.instance().DeleteCategoriesData();
                        CategoriesDB.instance().SaveCategoriesData(categoriesList);
                        break;
                    default:
                        break;
                }

                PreferencesSelectActivity.this.setResult(-1);
                PreferencesSelectActivity.this.finish();

            }

        }
        else if(v.getId() == R.id.btn_again)
        {
            StartRequest();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == REQUEST_DATA) {
            switch (mPreferencesType) {
                case Languages:
                    break;
                case Instructors:
                    break;
                case Universities:
                    break;
                case Categories:
                    break;
                default:
                    break;
            }
        }
        return false;
    }

    private void RequestLanguages() {

        SetCircleProgressGone();

        try {
            String language = Utils.GetAssetsTxt("language.txt");
            List<LanguagesModel> listPerson = JSON.parseArray(language, LanguagesModel.class);
            if (listPerson != null && mPreferencesModel != null) {
                mPreferencesModel.setLanguagesList(listPerson);
                mPreferencesSelectAdapter = new PreferencesSelectAdapter(mPreferencesModel);
                lvPreSelect.setAdapter(mPreferencesSelectAdapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RequestInstructors() {

        ArrayList<String> Fields = new ArrayList<String>();

        Fields.add(HttpConfig.Instructors_Fields_id);
        Fields.add(HttpConfig.Instructors_Fields_firstName);
        Fields.add(HttpConfig.Instructors_Fields_prefixName);
        Fields.add(HttpConfig.Instructors_Fields_title);

        HttpRequestInstructors.instance().RequestInstructors(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                SetCircleProgressGone();
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    HttpResponseInstructors data = JSON.parseObject(responseInfo.result, HttpResponseInstructors.class);
                    if (data != null && mPreferencesModel != null) {
                        mPreferencesModel.setInstructorsList(data.elements);
                        mPreferencesSelectAdapter = new PreferencesSelectAdapter(mPreferencesModel);
                        lvPreSelect.setAdapter(mPreferencesSelectAdapter);
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

        }, Fields, null);

    }

    private void RequestUniversities() {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Universities_Fields_id);
        Fields.add(HttpConfig.Universities_Fields_shortName);

        HttpRequestUniversities.instance().RequestUniversities(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                SetCircleProgressGone();
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    HttpResponseUniversities data = JSON.parseObject(responseInfo.result, HttpResponseUniversities.class);
                    if (data != null && mPreferencesModel != null) {
                        mPreferencesModel.setUniversitiesList(data.elements);
                        mPreferencesSelectAdapter = new PreferencesSelectAdapter(mPreferencesModel);
                        lvPreSelect.setAdapter(mPreferencesSelectAdapter);
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

        }, Fields, null);

    }

    private void RequestCategories() {

        ArrayList<String> Fields = new ArrayList<String>();
        Fields.add(HttpConfig.Categories_Fields_Id);
        Fields.add(HttpConfig.Categories_Fields_Name);
        Fields.add(HttpConfig.Categories_Fields_ShortName);

        HttpRequestCategories.instance().RequestCategories(new RequestCallBack<String>() {

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                SetCircleProgressGone();
                if (responseInfo != null && !TextUtils.isEmpty(responseInfo.result)) {
                    HttpResponseCategories data = JSON.parseObject(responseInfo.result, HttpResponseCategories.class);
                    if (data != null && mPreferencesModel != null) {
                        mPreferencesModel.setCategoriesList(data.elements);
                        mPreferencesSelectAdapter = new PreferencesSelectAdapter(mPreferencesModel);
                        lvPreSelect.setAdapter(mPreferencesSelectAdapter);
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

        }, Fields, null);

    }

    private void SetCircleProgressGone() {
        circleProgress.stopAnim();
        circleProgress.reset();
        progress.setVisibility(View.GONE);
        rlViewGroup.setVisibility(View.VISIBLE);
    }

    private void SetCircleProgressVisible() {
        progress.setVisibility(View.VISIBLE);
        rlViewGroup.setVisibility(View.GONE);
        circleProgress.startAnim();
    }

    private void HttpReuqestFailure() {
        circleProgress.stopAnim();
        circleProgress.reset();
        progress.setVisibility(View.GONE);
        failure.setVisibility(View.VISIBLE);
    }

    private void StartRequest() {
        switch (mPreferencesType) {

            case Languages:
                tvTitle.setText("选择语言");
                RequestLanguages();
                break;
            case Instructors:
                tvTitle.setText("选择讲师");
                RequestInstructors();
                break;
            case Universities:
                tvTitle.setText("选择大学");
                RequestUniversities();
                break;
            case Categories:
                tvTitle.setText("选择类型");
                RequestCategories();
                break;
            default:
                break;
        }
    }

}
