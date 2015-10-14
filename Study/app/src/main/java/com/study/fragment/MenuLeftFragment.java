package com.study.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.study.PreferencesType;
import com.study.R;
import com.study.StudyApp;
import com.study.StudyConfig;
import com.study.activity.MainActivity;
import com.study.activity.PreferencesSelectActivity;
import com.study.dialog.PreferencesSelectDialog;
import com.study.dialog.UserDialog;
import com.study.util.SharedPreferenceUtil;
import com.study.util.Utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by hugo on 15/10/9.
 */
public class MenuLeftFragment extends Fragment {

    private View mView;

    private final int OPEN_CAMERA = 1;
    private final int OPEN_PHOTO = 2;
    private final int OPEN_CROP = 3;

    private String mHeadName;
    private File sdcardTempFile;

    @ViewInject(R.id.lv_menu_list)
    private ListView lvMenuList;

    private ListAdapter mAdapter;

    @ViewInject(R.id.image_head)
    private ImageView imageHead;

    @ViewInject(R.id.tv_nickname)
    private TextView tvNickname;

    private UserDialog mUserDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            initView(inflater, container);
        }
        return mView;
    }

    private void initView(LayoutInflater inflater, ViewGroup container) {
        mView = inflater.inflate(R.layout.left_menu, container, false);
        ViewUtils.inject(this, mView); //注入view和事件
        String headPath = SharedPreferenceUtil.getUserHead();
        if (!TextUtils.isEmpty(headPath)) {
            BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
            bitmapUtils.display(imageHead, headPath);
        }
        String nickname = SharedPreferenceUtil.getUserNickname();
        if (!TextUtils.isEmpty(nickname)) {
            tvNickname.setText(nickname);
        }

        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.layout_menu_list));
        lvMenuList.setAdapter(mAdapter);

    }

    @OnClick({R.id.image_head})
    public void ButtonClick(View v) {
        switch (v.getId()) {
            case R.id.image_head:
                ShowUserDialog();
                break;
        }
    }

    @OnItemClick({R.id.lv_menu_list})
    public void OnItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == MainActivity.Fragment_Type_Preference) {
            ShowPreferencesSelectDialog();
        } else {
            ((MainActivity) getActivity()).RefreshFragment(position);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

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
                        imageHead.setImageBitmap(photo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case StudyConfig.Preferences_Select_Activity:
                ((MainActivity)getActivity()).RefreshCoursesList();
                break;
            default:
                break;
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
            Toast.makeText(StudyApp.instance(), "请插入存储卡", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowUserDialog() {

        mUserDialog = new UserDialog(getActivity(), new UserDialog.OnUserDialogListener() {

            @Override
            public void UserDialogCallBack(String head_path, String nickname) {
                SharedPreferenceUtil.setUserHead(head_path);
                SharedPreferenceUtil.setUserNickname(nickname);

                if (!TextUtils.isEmpty(nickname)) {
                    tvNickname.setText(nickname);
                }

            }

            @Override
            public void UserHeadCallBack() {

                Dialog dialog = new AlertDialog.Builder(getActivity()).setTitle("获取头像").setMessage("请选择从哪里获取头像")
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

        });
        mUserDialog.setCanceledOnTouchOutside(false);
        mUserDialog.show();
    }

    private void ShowPreferencesSelectDialog() {

        PreferencesSelectDialog dialog = new PreferencesSelectDialog(getActivity(), new PreferencesSelectDialog.OnPreferencesSelectDialogListener() {
            @Override
            public void PreferencesSelectDialogCallBack(PreferencesType type) {
                SharedPreferenceUtil.setPreferencesType(type.ordinal());
                if (type != PreferencesType.All) {
                    Intent intent = new Intent(getActivity(), PreferencesSelectActivity.class);
                    intent.putExtra(StudyConfig.PREFERENCES_TYPE, type.ordinal());
                    startActivityForResult(intent, StudyConfig.Preferences_Select_Activity);
                }
                else
                {
                    ((MainActivity)getActivity()).RefreshCoursesList();
                }
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
