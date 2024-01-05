package com.zd.study.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.zd.study.R;
import com.zd.study.amimation.AnimationTestActivity;
import com.zd.study.view.PopupwindowUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MainActivity extends AppCompatActivity {

    private TextView mTestText;
    private Button mButton;
    private CardView mCardView;
    private TextView mTestView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestText = findViewById(R.id.test_text);
        mCardView = findViewById(R.id.test_card_view);
        mCardView.setCardBackgroundColor(this.getResources().getColor(R.color.black));
        mButton = findViewById(R.id.test_btn);
        mTestView = findViewById(R.id.text_view);
        String a = "<a href=\"www://baidu.com\">跳转链接</a>";
        mTestText.setText(Html.fromHtml(a));
        ViewGroup.LayoutParams params = mButton.getLayoutParams();

        ImageView imageView = (ImageView) findViewById(R.id.my_image_view);
        String image = "https://img-blog.csdnimg.cn/20191215043500229.png";
        Glide.with(this).load(image).into(imageView);
        boolean endwith = image.endsWith("29.png");
        Log.d("Zdtest","endwith    "+ endwith);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, FragmentTestActivity.class);
//                startActivity(intent);

//                test();
//                PopupwindowUtils.showPop(MainActivity.this);

//                test();
//                PopupwindowUtils.showPop(MainActivity.this);
                testPoc();
            }
        });


        String test = String.format(MainActivity.this.getString(R.string.black_red1), "nihaoaa");
        mTestView.setText(Html.fromHtml(test));

        findViewById(R.id.test1_btn).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                PopupwindowUtils.show1(MainActivity.this);
//                Calendar a = Calendar.getInstance();
//                int year = a.get(Calendar.YEAR);
//                int month = a.get(Calendar.MONTH) + 1;
//                int dayOfMonth = a.get(Calendar.DAY_OF_MONTH);
//
//                Log.d("test", year + "" + month + "" + dayOfMonth);

                int vi = View.GONE;

//                String test = String.format(MainActivity.this.getString(R.string.test), "nihaoaa");
//                SpannableString spanColor = new SpannableString(test);
//                dealOpenPermission(MainActivity.this, spanColor, mTestView);

                boolean test = checkDeviceHasNavigationBar(MainActivity.this);
                Log.d("ceshi", " test   " + test);
                int test1 = getNavigationBarRealHeight(MainActivity.this);
                boolean isNavigationBarShow = isNavigationBarShow(MainActivity.this);
                int getNavigationBarHeight = getNavigationBarHeight(MainActivity.this);
                Log.d("ceshi", " test1   " + test1 + "   isNavigationBarShow   " + isNavigationBarShow + "    getNavigationBarHeight  " + getNavigationBarHeight);
            }
        });
    }



    public boolean isNavigationBarShow(Activity activity){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1){
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        }else {
            return checkDeviceHasNavigationBar(activity);
        }
    }
    public int getNavigationBarRealHeight(Activity activity) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        DisplayMetrics outMetrics = new DisplayMetrics();
        WindowManager windowManager = window.getWindowManager();
        windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
        return outMetrics.heightPixels - rect.bottom;
    }


    public int getNavigationBarHeight(Activity activity){
        int height = 0;
        ContentResolver cr = activity.getContentResolver();
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen","android");
        Log.d("ceshi", "getNavigationBarHeight  resourceId   " + resourceId);

        if(resourceId > 0){
            height = resources.getDimensionPixelSize(resourceId);
        }
        return height;
    }

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(context)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    public void testPoc(){
        try{

            //当前应用pid
            final PackageManager packageManager = this.getPackageManager();
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            // get all apps
            final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);
            for (int i = 0; i < apps.size(); i++) {
                String name = apps.get(i).activityInfo.packageName;
                Log.i("TAG", "getAppProcessName: " +
                        apps.get(i).activityInfo.applicationInfo.loadLabel(packageManager).toString() + "------" + name);
            }

            List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
            for(int i=0;i<packages.size();i++) {
                PackageInfo packageInfo = packages.get(i);
                Log.i("TAG", "getAppProcessName: 1111" + packageInfo.packageName);
            }

            long start  = System.currentTimeMillis();
            Log.i("TAG", "开始时间    start  " + start);
            String packageName = getPackageName();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.eg.android.AlipayGphone"/*packageName*/, 0);
            String apkPath = packageInfo.applicationInfo.sourceDir;

            String publicSourceDir = packageInfo.applicationInfo.publicSourceDir;
            //data/app
            Log.i("TAG", "    sourceDir  " + apkPath + "   publicSourceDir   " + publicSourceDir);

            File apkFile = new File(apkPath);

            JarFile jarFile = new JarFile(apkPath, false);



            MessageDigest md5 = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(apkFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) != -1){
                md5.update(buffer, 0, length);
            }
            byte[] digest = md5.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            String md5Value = sb.toString();
            long end  = System.currentTimeMillis();
            Log.i("TAG", "开始时间    end  " + end + "========" + (end - start));
            Log.d("TAG", "md5Value       " + md5Value);
        }catch ( Exception e){
            e.printStackTrace();
        }



    }

    private void dealOpenPermission(Context context , SpannableString mSpannableString, TextView goToOpenPermissionText){
        goToOpenPermissionText.setText(mSpannableString);
        goToOpenPermissionText.setHighlightColor(context.getResources().getColor(R.color.transparent));
        String test = String.format(MainActivity.this.getString(R.string.test), "nihaoaa");
        mSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
//                XXPermissions.startPermissionActivity(context, permissions);
                Toast.makeText(context,"点击了", Toast.LENGTH_LONG).show();
            }
        }, mSpannableString.length() - 3, mSpannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.blue));
        mSpannableString.setSpan(foregroundColorSpan, test.length() - 3, test.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        goToOpenPermissionText.setFocusable(true);

//        goToOpenPermissionText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"点击TextView", Toast.LENGTH_LONG).show();
//            }
//        });


    }

    public void test(){
//        String url = "http://p3-sdkdemo-images.byteimg.com/tos-cn-i-q5j7jlkqqz/1.webp~tplv-q5j7jlkqqz-image.image?sc=test_%_sc";
//        Uri uri = Uri.parse(url);
//        String scheme = uri.getScheme();
//        Log.d("test", "   scheme = " + scheme);
//        String sctest = new String(sc,"utf-8");
//        try {
//            String sctest = URLDecoder.decode(sc, "utf-8");
//            Log.d("test", "   sctest = " + sctest);
//        }catch (Exception e){
//
//        }


        long time = System.currentTimeMillis();
        int day = (int) (time / (1000));
        Log.d("test", "time   "+  time + "   day = " + day);


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr1 = sdf1.format(new Date(day));
        Log.d("test1", "   dateStr1 = " + dateStr1);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr = sdf.format(new Date(time));
        Log.d("test1", "   dateStr = " + dateStr);

        Calendar calendar = Calendar.getInstance();
        int time1 = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DAY_OF_MONTH);
        Log.d("test1", "   time1 = " + time1 + "    month  "+ month + " day1  " + day1 + " 最终   "
                +  String.valueOf(time1)+ String.valueOf(month) + String.valueOf(day1));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}