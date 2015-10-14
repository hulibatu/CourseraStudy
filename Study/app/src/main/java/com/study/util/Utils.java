package com.study.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import com.study.StudyApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hugo on 15/10/8.
 */
public class Utils {


    public static String GetImei()
    {
        TelephonyManager telMgr = (TelephonyManager) StudyApp.instance().getSystemService(Context.TELEPHONY_SERVICE);
        String imei =  "" + telMgr.getDeviceId();
        return imei;
    }

    public static String SaveBitmap(Bitmap bitmap, String name) throws IOException {

        FileOutputStream fOut = null;
        String path = "/mnt/sdcard/Android/data/com.study/files";
        try {
            File destDir = new File(path);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            path = path + "/" + name;

            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
            fOut = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static Intent ImageCrop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 248);
        intent.putExtra("outputY", 248);
        intent.putExtra("outputFormat", "PNG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        return intent;
    }

    public static String GetAssetsTxt(String txt_name) throws IOException {

        InputStream is = StudyApp.instance().getAssets().open(txt_name);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String text = new String(buffer, "UTF-8");
        return text;

    }


}
