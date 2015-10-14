package com.study.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.study.PreferencesType;
import com.study.R;
import com.study.util.SharedPreferenceUtil;

/**
 * Created by hugo on 15/10/6.
 *
 * 偏好设置Dialog
 *
 */
public class PreferencesSelectDialog extends Dialog {

    private RadioGroup rgSelectGroup;
    private RadioButton rbtnLanguages,rbtnInstructors,rbtnUniversities,rbtnType,rbtnAll;

    private Button btnConfirm;
    private PreferencesType mPreferencesType;

    //定义回调事件，用于dialog的点击事件
    public interface OnPreferencesSelectDialogListener {
        public void PreferencesSelectDialogCallBack(PreferencesType type);
    }

    private Context mContext;
    private OnPreferencesSelectDialogListener mOnPreferencesSelectDialogListener;

    public PreferencesSelectDialog(Context context, OnPreferencesSelectDialogListener preferencesSelectDialogCallBack) {
        super(context,R.style.PauseDialog);
        this.mContext = context;
        this.mOnPreferencesSelectDialogListener = preferencesSelectDialogCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preferences_select_dialog);

        setTitle(mContext.getResources().getString(R.string.preferences_select_dialog_title));

        rbtnLanguages = (RadioButton) this.findViewById(R.id.rbtn_languages);
        rbtnInstructors = (RadioButton) this.findViewById(R.id.rbtn_instructors);
        rbtnUniversities = (RadioButton) this.findViewById(R.id.rbtn_universities);
        rbtnType = (RadioButton) this.findViewById(R.id.rbtn_type);
        rbtnAll = (RadioButton) this.findViewById(R.id.rbtn_all);

        mPreferencesType = PreferencesType.values()[SharedPreferenceUtil.getPreferencesType()];

        switch (mPreferencesType)
        {
            case All:
                rbtnAll.setChecked(true);
                break;
            case Languages:
                rbtnLanguages.setChecked(true);
                break;
            case Instructors:
                rbtnInstructors.setChecked(true);
                break;
            case Universities:
                rbtnUniversities.setChecked(true);
                break;
            case Categories:
                rbtnType.setChecked(true);
                break;
        }

        rgSelectGroup = (RadioGroup) this.findViewById(R.id.rg_select_group);
        rgSelectGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbtn_languages:
                        mPreferencesType = PreferencesType.Languages;
                        break;
                    case R.id.rbtn_instructors:
                        mPreferencesType = PreferencesType.Instructors;
                        break;
                    case R.id.rbtn_universities:
                        mPreferencesType = PreferencesType.Universities;
                        break;
                    case R.id.rbtn_type:
                        mPreferencesType = PreferencesType.Categories;
                        break;
                    case R.id.rbtn_all:
                        mPreferencesType = PreferencesType.All;
                        break;
                }

            }

        });

        btnConfirm = (Button) this.findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPreferencesType == null)
                {
                    Toast.makeText(mContext,"请选择偏好",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mOnPreferencesSelectDialogListener != null) {
                    mOnPreferencesSelectDialogListener.PreferencesSelectDialogCallBack(mPreferencesType);
                }
                PreferencesSelectDialog.this.dismiss();
            }
        });

    }


}
