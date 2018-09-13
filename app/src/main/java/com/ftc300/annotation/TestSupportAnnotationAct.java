package com.ftc300.annotation;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.FloatRange;
import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Comment:
 * Author: ftc300
 * Date: 2018/9/11
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */

@Keep
public class TestSupportAnnotationAct extends AppCompatActivity {
    private TextView tv;
//    @SuppressLint("CheckResult")
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            //参数可以传值为空
            testNull(null);
        } catch (Exception ignored) {

        }

        //错误示范
//      setTitle(R.layout.activity_main);
//        setAlpha(-1);
//        setAlpha(2f);
//        getLocationInWindow(new int[3]);
//        getLocationOnScreen(new int[1]);
//        workerThreadTest();
        //正确示范
        setTitle(R.string.app_name);
        setAlpha(13);
        setAlpha(0.2f);
        getLocationInWindow(new int[2]);
        getLocationOnScreen(new int[4]);

        getCheckResultValue("value");
    }

    //    Nullness 注解
//    作用于函数参数和返回值 ， 标记参数或者返回值是否可以为空
    @NonNull
    String testNull(@Nullable String arg) throws Exception {
        if (TextUtils.isEmpty(arg)) {
//            throw new NullPointerException("testNull arg is null.");
            return null;
        }
        return arg;
    }

    @Keep
    public void setTitle(@StringRes int resId) {
    }

    @UiThread
    public void uiThreadTest() {
        tv.setText("success");
    }

    @WorkerThread
    public void workerThreadTest() {
        tv.setText("error");
    }


    //  a，限定 alpha 参数的取值范围为 0 至255
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
    }

    //    b，限定 alpha 参数的取值范围为 0.0 至 1.0
    public void setAlpha(@FloatRange(from = 0.0, to = 1.0) float alpha) {

    }

    //    c，限定 location 数组大小为 2
    public void getLocationInWindow(@Size(2) int[] location) {
    }

    //    d，限定 location 参数的最小值为 1
    public void getLocationOnScreen(@Size(min = 2) int[] location) {
    }

    @RequiresPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    public void readFile(String dest, String source) {

    }

    @RequiresPermission(allOf = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public static final void copyFile(String dest, String source) {
    }

    @RequiresPermission(anyOf = {ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION})
    public void getLastKnownLocation(String provider) {

    }

    static class EnumItem {
        public static final int TYPE0 = 0;
        public static final int TYPE1 = 1;
        public static final int TYPE2 = 2;

        @IntDef({TYPE0, TYPE1, TYPE2})
        public @interface ItemType {
        }
        int mType;

        public void setType(@ItemType int type) {
            this.mType = type;
        }
    }

    @CheckResult(suggest= "建议你调用前判断一下是否为空？")
    public String getCheckResultValue(String arg){
        return arg;
    }

}
