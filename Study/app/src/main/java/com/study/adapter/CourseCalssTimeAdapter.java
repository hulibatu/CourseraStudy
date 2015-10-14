package com.study.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.study.R;
import com.study.StudyApp;
import com.study.model.SessionsModel;

import java.util.List;

/**
 * Created by hugo on 15/10/14.
 */
public class CourseCalssTimeAdapter extends BaseAdapter {

    public List<SessionsModel> mDataList;

    public CourseCalssTimeAdapter(List<SessionsModel> list)
    {
        this.mDataList = list;
    }

    @Override
    public int getCount() {

        if (mDataList != null)
            return mDataList.size();

        return 0;
    }

    @Override
    public Object getItem(int position) {

        if (mDataList != null)
            return mDataList.get(position);

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final SessionsModel session = mDataList.get(position);
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(StudyApp.instance());
            convertView = mInflater.inflate(R.layout.class_time_item, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(session.getName());

        if(!TextUtils.isEmpty(session.getDurationString()))
        {
            holder.tv_time.setText(session.getDurationString());
        }
        else
        {
            holder.tv_time.setText("未知");
        }

        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_time;
    }

}
