package com.study.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.study.R;
import com.study.util.SharedPreferenceUtil;
import com.study.view.circleimageview.CircleImageView;

/**
 * Created by hugo on 15/10/6.
 */

public class UserDialog extends Dialog implements View.OnClickListener {


    //定义回调事件，用于dialog的点击事件
    public interface OnUserDialogListener {
        public void UserDialogCallBack(String head_path, String nickname);
        public void UserHeadCallBack();
    }

    private OnUserDialogListener mOnUserDialogListener;

    private CircleImageView imageHead;
    private EditText etNickname;
    private Button btnConfirm;
    private Context mContext;
    private String mHeadPath;
    private String mNickname;

    public UserDialog(Context context, OnUserDialogListener userDialogListener) {
        super(context, R.style.PauseDialog);
        this.mContext = context;
        this.mOnUserDialogListener = userDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_dialog);

        setTitle(mContext.getResources().getString(R.string.user_dialog_title));

        imageHead = (CircleImageView) this.findViewById(R.id.image_head);
        etNickname = (EditText) this.findViewById(R.id.et_nickname);
        btnConfirm = (Button) this.findViewById(R.id.btn_confirm);

        mHeadPath = SharedPreferenceUtil.getUserHead();
        if (!TextUtils.isEmpty(mHeadPath)) {
            BitmapUtils bitmapUtils = new BitmapUtils(mContext);
            bitmapUtils.display(imageHead,mHeadPath);
        }

        mNickname = SharedPreferenceUtil.getUserNickname();
        if (!TextUtils.isEmpty(mNickname)) {
            etNickname.setText(mNickname);
        }

        imageHead.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_confirm) {
            if (TextUtils.isEmpty(mHeadPath))
                {
                    Toast.makeText(mContext,"请设置头像",Toast.LENGTH_SHORT).show();
                    return;
                }
                mNickname = etNickname.getText().toString();
            if (TextUtils.isEmpty(mNickname)) {
                Toast.makeText(mContext, "请输入昵称", Toast.LENGTH_SHORT).show();
                return;
            } else if (mNickname.length() < 4 || mNickname.length() > 8) {
                Toast.makeText(mContext, "请输入合法昵称", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mOnUserDialogListener != null) {
                mOnUserDialogListener.UserDialogCallBack(mHeadPath, mNickname);
            }
            UserDialog.this.dismiss();
        } else if (v.getId() == R.id.image_head) {
            if (mOnUserDialogListener != null) {
                mOnUserDialogListener.UserHeadCallBack();
            }
        }
    }

    public void SetUserHead(String path,Bitmap bitmap)
    {
        mHeadPath = path;
        imageHead.setImageBitmap(bitmap);
    }

}
