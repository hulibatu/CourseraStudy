package com.study.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.R;
import com.study.StudyApp;
import com.study.model.CoursesModel;
import com.study.model.RecordModel;

import java.util.List;

/**
 * Created by hugo on 15/10/12.
 */
public class RecordListAdapter  extends BaseAdapter {

    public List<RecordModel> mDataList;

    public RecordListAdapter(List<RecordModel> list)
    {
        mDataList = list;
    }

    public void RefreshList(List<RecordModel> list)
    {
        mDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        if(mDataList!=null)
            return mDataList.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {

        if(mDataList!=null)
            return mDataList.get(position);

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(StudyApp.instance());
            convertView = mInflater.inflate(R.layout.record_list_item, null);
            holder = new ViewHolder();
            holder.tv_course_name = (TextView) convertView.findViewById(R.id.tv_course_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_course_name.setText(mDataList.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        TextView tv_course_name;
    }

}
