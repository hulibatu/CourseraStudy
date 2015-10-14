package com.study.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.study.PreferencesType;
import com.study.R;
import com.study.StudyConfig;
import com.study.dialog.PreferencesSelectDialog;
import com.study.util.SharedPreferenceUtil;
import com.study.dialog.UserDialog;
import com.study.util.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hugo on 15/10/6.
 * <p/>
 * 启动页
 */

@ContentView(R.layout.welcome)
public class WelcomeActivity extends Activity implements UserDialog.OnUserDialogListener, PreferencesSelectDialog.OnPreferencesSelectDialogListener {

    private final int OPEN_CAMERA = 100001;
    private final int OPEN_PHOTO = 100002;
    private final int OPEN_CROP = 100003;

    private String mHeadName;
    private File sdcardTempFile;

    @ViewInject(R.id.btn_confirm)
    private Button btnConfirm;

    @ViewInject(R.id.rl_tip)
    private RelativeLayout rlTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);

        if (TextUtils.isEmpty(SharedPreferenceUtil.getImei())) {
            rlTip.setVisibility(View.VISIBLE);
        }
        else
        {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            WelcomeActivity.this.finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);


        switch (requestCode) {
            case OPEN_PHOTO:
                if ((intent != null) && (intent.getData() != null)) {
                    startActivityForResult(Utils.ImageCrop(intent.getData()), OPEN_CROP);
                }
                break;
            case OPEN_CAMERA:
                if (resultCode == -1) {
                    startActivityForResult(Utils.ImageCrop(Uri.fromFile(this.sdcardTempFile)), OPEN_CROP);
                }
                break;
            case OPEN_CROP:
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    Bitmap photo = (Bitmap) extras.getParcelable("data");
                    try {
                        String path = Utils.SaveBitmap(photo, this.mHeadName);
                        if (mUserDialog != null) {
                            mUserDialog.SetUserHead(path, photo);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case StudyConfig.Preferences_Select_Activity:
                if(resultCode == -1)
                {
                    SharedPreferenceUtil.setImei(Utils.GetImei());
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();
                }
                else
                {
                    ShowPreferencesSelectDialog();
                }
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_confirm})
    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                ShowUserDialog();
                break;
        }
    }

    private UserDialog mUserDialog;

    private void ShowUserDialog() {
        mUserDialog = new UserDialog(this, this);
        mUserDialog.setCanceledOnTouchOutside(false);
        mUserDialog.show();
    }

    @Override
    public void UserDialogCallBack(String head_path, String nickname) {
        SharedPreferenceUtil.setUserHead(head_path);
        SharedPreferenceUtil.setUserNickname(nickname);
        ShowPreferencesSelectDialog();
    }

    @Override
    public void UserHeadCallBack() {

        Dialog dialog = new AlertDialog.Builder(this).setTitle("获取头像").setMessage("请选择从哪里获取头像")
                .setNeutralButton("相册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserHeadOperation(StudyConfig.HEAD_PHOTO_ALBUM);
                    }
                })
                .setPositiveButton("照相机", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserHeadOperation(StudyConfig.HEAD_CAMERA);
                    }
                }).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void ShowPreferencesSelectDialog() {
        PreferencesSelectDialog dialog = new PreferencesSelectDialog(this, this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void PreferencesSelectDialogCallBack(PreferencesType type) {
        SharedPreferenceUtil.setPreferencesType(type.ordinal());
        if (type == PreferencesType.All) {
            SharedPreferenceUtil.setImei(Utils.GetImei());
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            WelcomeActivity.this.finish();
        } else {
            Intent intent = new Intent(WelcomeActivity.this, PreferencesSelectActivity.class);
            intent.putExtra(StudyConfig.PREFERENCES_TYPE, type.ordinal());
            startActivityForResult(intent,StudyConfig.Preferences_Select_Activity);
        }
    }

    public void UserHeadOperation(int type) {

        this.mHeadName = "hugo.png";
        boolean sdCardExist = Environment.getExternalStorageState().equals("mounted");
        if (sdCardExist) {

            this.sdcardTempFile = new File(Environment.getExternalStorageDirectory(), mHeadName);

            switch (type) {
                case StudyConfig.HEAD_CAMERA:
                    Intent intent1 = new Intent("android.media.action.IMAGE_CAPTURE");
                    Uri uri = Uri.fromFile(this.sdcardTempFile);
                    intent1.putExtra("output", uri);
                    startActivityForResult(intent1, OPEN_CAMERA);
                    break;
                case StudyConfig.HEAD_PHOTO_ALBUM:
                    Intent intent2 = new Intent("android.intent.action.PICK");
                    intent2.setType("image/*");
                    startActivityForResult(intent2, OPEN_PHOTO);
                    break;
            }
        } else {
            Toast.makeText(this, "请插入存储卡", Toast.LENGTH_SHORT).show();
        }
    }


}
