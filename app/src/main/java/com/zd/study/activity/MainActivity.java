package com.zd.study.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;

import com.bumptech.glide.Glide;
import com.zd.study.R;
import com.zd.study.utils.DisplayUtil;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.jar.JarFile;

/**
 * @author zd
 */
public class MainActivity extends AppCompatActivity {

    private Button mTabFragmentBtn;
    private Button mButton;
    private CardView mCardView;
    private TextView mTestView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getResources().getConfiguration().locale.getLanguage();
        // 获取WindowInfoTracker实例
        WindowInfoTracker windowInfoTracker = WindowInfoTracker.getOrCreate(this);
        // 创建一个Executor来在其上执行异步任务
        Executor executor = Executors.newSingleThreadExecutor();
        windowInfoTracker.windowLayoutInfo(this);
        //        String a = "<a href=\"www://baidu.com\">跳转链接</a>";
//        mTestText.setText(Html.fromHtml(a));
        // 使用windowInfoTracker和Executor来获取当前的WindowLayoutInfo

        setContentView(R.layout.activity_main);
        mTabFragmentBtn = findViewById(R.id.tab_fragment_btn);
        mCardView = findViewById(R.id.test_card_view);
        mCardView.setCardBackgroundColor(this.getResources().getColor(R.color.black));
        mButton = findViewById(R.id.test_btn);
        mTestView = findViewById(R.id.text_view);
//        String a = "<a href=\"www://baidu.com\">跳转链接</a>";
//        mTestText.setText(Html.fromHtml(a));
        Button glideTestBtn = findViewById(R.id.glide_test_btn);
        glideTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlideStudyActivity.class);
                startActivity(intent);
            }
        });
        mTabFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentTestActivity.class);
                startActivity(intent);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getScreenWith();
//                Intent intent = new Intent(MainActivity.this, TestActivity.class);
//                startActivity(intent);

//                try {
//                    Uri uri = Uri.parse("bocom://https://wap.95559.com.cn/mobs/main.html#public/index/index?flag=bocom_home_page_nph0001_update&&https%3A%2F%2Fmbank.95559.com.cn%3A8888%2Fmobs6%2Ftransfer%2FTRA%2FNTRAA01.html%3FtokenId%3Dxxx");
//                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                String testStr = "12345678901234567890";
//                String a = testStr.substring(0,12);
//                Log.d("ceshi",a);



//                test();
//                PopupwindowUtils.showPop(MainActivity.this);

//                test();
//                PopupwindowUtils.showPop(MainActivity.this);
//                testPoc();

                "aa".equals(null);
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

//                String test = String.format(MainActivity.this.getString(R.string.test), "nihaoaa");
//                SpannableString spanColor = new SpannableString(test);
//                dealOpenPermission(MainActivity.this, spanColor, mTestView);

//                boolean test = DisplayUtil.checkDeviceHasNavigationBar(MainActivity.this);
//                Log.d("ceshi", " test   " + test);
//                int test1 = DisplayUtil.getNavigationBarRealHeight(MainActivity.this);
//                Log.d("ceshi", " test1   " + test1);
//
//
//                Intent intent = new Intent(MainActivity.this, WindowTestActivity.class);
//                startActivity(intent);

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("bocom://https://wap.95559.com.cn/mobs/main.html#public/index/index?flag=bocom_home_page_nph0001_update&&https%3A%2F%2Fmbank.95559.com.cn%3A8888%2Fmobs6"));
                startActivity(intent);
            }
        });
    }

    private void handleFoldingFeature(FoldingFeature feature, Rect bounds) {
        // 判断折叠屏是否已经折叠
        if (feature.getState() == FoldingFeature.State.HALF_OPENED) {
            // 处理折叠状态
        } else if (feature.getState() == FoldingFeature.State.FLAT) {
            // 处理展开状态
        }
    }

    public void getScreenWith(){
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Log.d("ceshi", " width   " +  getDisplayWidth(this) + " height   " + getDisplayHeight(this));
    }


    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public double getDisplayWidth(Activity activity) {
        DisplayMetrics displayMetrics = getRealDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public double getDisplayHeight(Activity activity) {
        DisplayMetrics displayMetrics = getRealDisplayMetrics(activity);
        return displayMetrics.heightPixels;
    }


    private DisplayMetrics getRealDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
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


    // JSON字符串示例
    String jsonStr = "[{\"name\":\"John\", \"age\":30}, {\"name\":\"Jane\", \"age\":25}]";

}