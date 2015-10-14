package com.study.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.lidroid.xutils.util.LogUtils;
import com.study.R;
import com.study.StudyApp;
import com.study.db.CategoriesDB;
import com.study.db.InstructorsDB;
import com.study.db.LanguagesDB;
import com.study.db.UniversitiesDB;
import com.study.model.CategoriesModel;
import com.study.model.InstructorsModel;
import com.study.model.LanguagesModel;
import com.study.model.PreferencesModel;
import com.study.model.UniversitiesModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hugo on 15/10/6.
 * <p/>
 * 偏好Adapter
 */
public class PreferencesSelectAdapter extends BaseAdapter {

    private HashMap<Integer, Boolean> mSelectedMap;
    private PreferencesModel mData;
    private ArrayList<Integer> mIDList;

    public HashMap<Integer, Boolean> GetSelectedMap() {
        return mSelectedMap;
    }

    public PreferencesSelectAdapter(PreferencesModel data) {
        this.mData = data;
        this.mSelectedMap = new HashMap<Integer, Boolean>();

        switch (mData.getType()) {
            case Languages:
                mIDList = LanguagesDB.instance().GetLanguagesIDs();
                break;
            case Instructors:
                mIDList = InstructorsDB.instance().GetInstructorsIDs();
                break;
            case Universities:
                mIDList = UniversitiesDB.instance().GetUniversitiesIDs();
                break;
            case Categories:
                mIDList = CategoriesDB.instance().GetCategoriesIDs();
                break;
            default:
                break;
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mData != null) {
            switch (mData.getType()) {
                case Languages:
                    count = mData.getLanguagesList() != null ? mData.getLanguagesList().size() : 0;
                    break;
                case Instructors:
                    count = mData.getInstructorsList() != null ? mData.getInstructorsList().size() : 0;
                    break;
                case Universities:
                    count = mData.getUniversitiesList() != null ? mData.getUniversitiesList().size() : 0;
                    break;
                case Categories:
                    count = mData.getCategoriesList() != null ? mData.getCategoriesList().size() : 0;
                    break;
                default:
                    break;
            }
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        Object obj = null;
        if (mData != null) {
            switch (mData.getType()) {
                case Languages:
                    obj = mData.getLanguagesList().get(position);
                    break;
                case Instructors:
                    obj = mData.getInstructorsList().get(position);
                    break;
                case Universities:
                    obj = mData.getUniversitiesList().get(position);
                    break;
                case Categories:
                    obj = mData.getCategoriesList().get(position);
                    break;
                default:
                    break;
            }
        }
        return obj;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(StudyApp.instance());
            convertView = mInflater.inflate(R.layout.preferences_select_item, null);
            holder = new ViewHolder();

            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.cb_p_select_item = (CheckBox) convertView.findViewById(R.id.cb_p_select_item);

            holder.cb_p_select_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;

                    LogUtils.i("cb_p_select_item onClick : " + position + " === " + cb.isChecked());

                    mSelectedMap.put(position, cb.isChecked());
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int data_id = 0;

        switch (mData.getType()) {
            case Languages:
                LanguagesModel languagesModel = mData.getLanguagesList().get(position);
                holder.tv_name.setText(languagesModel.getName());
                data_id = languagesModel.getId();
                break;
            case Instructors:
                InstructorsModel instructorsModel = mData.getInstructorsList().get(position);
                String name = String.format("%s%s", instructorsModel.getPrefixName(), TextUtils.isEmpty(instructorsModel.getFirstName())?"未知":instructorsModel.getFirstName());
                if (!TextUtils.isEmpty(instructorsModel.getTitle())) {
                    name += (" · " + instructorsModel.getTitle());
                }
                holder.tv_name.setText(name);
                data_id = instructorsModel.getId();
                break;
            case Universities:
                UniversitiesModel universitiesModel = mData.getUniversitiesList().get(position);
                holder.tv_name.setText(universitiesModel.getShortName());
                data_id = universitiesModel.getId();
                break;
            case Categories:
                CategoriesModel categoriesModel = mData.getCategoriesList().get(position);
                holder.tv_name.setText(categoriesModel.getShortName());
                data_id = categoriesModel.getId();
                break;
            default:
                break;
        }

        if (mSelectedMap.containsKey(position)) {
            holder.cb_p_select_item.setChecked(mSelectedMap.get(position));
        } else {
            boolean flag = mIDList.contains(data_id);
            holder.cb_p_select_item.setChecked(flag);
            mSelectedMap.put(position, flag);
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        CheckBox cb_p_select_item;
    }

}
