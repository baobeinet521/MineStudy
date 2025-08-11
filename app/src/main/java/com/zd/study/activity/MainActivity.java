package com.zd.study.activity;

import static android.text.Html.FROM_HTML_MODE_LEGACY;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zd.study.R;
import com.zd.study.handler.HandlerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.jar.JarFile;
import android.content.ComponentCallbacks2;

/**
 * @author zd
 */
public class MainActivity extends BaseActivity {

    class TestObject{
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.d("cesi", "finalize:   ");
        }
    }

    private Button mTabFragmentBtn;
    private Button mButton;
    private Button mTestButton;
    private CardView mCardView;
    private TextView mTestView;
    private WeakReference<TestObject> mTestObject;

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
        mTestButton = findViewById(R.id.test_page_btn);
        mTestView = findViewById(R.id.text_view);
//        String a = "<a href=\"www://baidu.com\">跳转链接</a>";
//        mTestText.setText(Html.fromHtml(a));
        Button glideTestBtn = findViewById(R.id.glide_test_btn);
        Button viewTestBtn = findViewById(R.id.view_test_btn);
        glideTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GlideStudyActivity.class);
            startActivity(intent);
        });
        viewTestBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LayoutOptActivity.class);
            startActivity(intent);
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

//                "aa".equals(null);


                Intent intent = new Intent(MainActivity.this, CoroutineTestActivity.class);
                startActivity(intent);
            }
        });
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });


//        String test = String.format(MainActivity.this.getString(R.string.black_red1), "nihaoaa");
        String test = "<div>您也可以<a href=\"https://www.baidu.com\"><font color='#FF03DAC5'>点击这里</a></font>查看并扫描二维码后关注。</div>";
        mTestView.setText(Html.fromHtml(test, FROM_HTML_MODE_LEGACY));

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

        findViewById(R.id.handler_test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(intent);
            }
        });

        ImageView glideTest = findViewById(R.id.glide_test);
        glideTest.post(new Runnable() {
            @Override
            public void run() {
                int width = glideTest.getMeasuredWidth();
                int height = glideTest.getMeasuredHeight();
                Log.d("ceshi"," view width     " + width +  "    view height     " + height);
                RequestOptions option = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_ARGB_8888).priority(Priority.IMMEDIATE)/*.override(width * 5, height * 5)*/;

//        String url = "https://mbanktest.bankcomm.com:8724/mobs6.0-TMF-UAT/home/oss/pub/file/img/public/wonhot/images/IM4804a7d02df744c3a77a4cd9a34cc7ed.jpeg?InstRoom=zj";
                String url = "https://mbanktest.bankcomm.com:8724/mobs6.0-TMF-UAT/home/oss/pub/file/img/public/wonhot/images/IM22a39b8407d943a7bf40ef88667e527b.jpeg?InstRoom=zj";
//        url = "https://download1.bankcomm.com/mobs_assets/home/oss/pub/file/img/public/wonhot/images/IM63dc386a663e49fe8affe5a4ef719ecd.jpeg?InstRoom=pjx";

                Glide.with(MainActivity.this).asDrawable().apply(option).load(url).into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ((BitmapDrawable)resource).getBitmap();
                        int size = bitmap.getByteCount();
                        float imageSize = size / 1024f / 1024f;
                        Log.d("ceshi"," image width     " + bitmap.getWidth() +  "    image height     " + bitmap.getHeight() + bitmap.getConfig());
                        Log.d("ceshi","   imageSize   " + imageSize + "  size   " + size);
                        glideTest.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
            }
        });
        /*new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("ceshi","   进入handeler了   ");
                try {
                    List<Byte[]> lists = new ArrayList<>();
                    for (int i = 0; i < 10000000; i++){
                        lists.add(new Byte[1024 * 1024 * 100]);
//                        Log.d("ceshi", "i  " +  i);
//                        Bitmap bitmap = Bitmap.createBitmap(1024, 1024, Bitmap.Config.ARGB_8888);
//                        lists.add(bitmap);
                    }
                }catch (OutOfMemoryError e){
                    Log.d("ceshi", "" + e);
                    Log.e("ceshi", "清楚Glide缓存 ============ ");
                    Glide.get(MainActivity.this).clearMemory();
                    Glide.get(MainActivity.this).clearDiskCache();
                }
            }
        }, 10000);*/
    }

    private void handleFoldingFeature(FoldingFeature feature, Rect bounds) {
        // 判断折叠屏是否已经折叠
        if (feature.getState() == FoldingFeature.State.HALF_OPENED) {
            // 处理折叠状态
        } else if (feature.getState() == FoldingFeature.State.FLAT) {
            // 处理展开状态
        }
    }

    public void getScreenWith() {
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Log.d("ceshi", " width   " + getDisplayWidth(this) + " height   " + getDisplayHeight(this));
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


    public void testPoc() {
        try {

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
            for (int i = 0; i < packages.size(); i++) {
                PackageInfo packageInfo = packages.get(i);
                Log.i("TAG", "getAppProcessName: 1111" + packageInfo.packageName);
            }

            long start = System.currentTimeMillis();
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
            while ((length = fis.read(buffer)) != -1) {
                md5.update(buffer, 0, length);
            }
            byte[] digest = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            String md5Value = sb.toString();
            long end = System.currentTimeMillis();
            Log.i("TAG", "开始时间    end  " + end + "========" + (end - start));
            Log.d("TAG", "md5Value       " + md5Value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void dealOpenPermission(Context context, SpannableString mSpannableString, TextView goToOpenPermissionText) {
        goToOpenPermissionText.setText(mSpannableString);
        goToOpenPermissionText.setHighlightColor(context.getResources().getColor(R.color.transparent));
        String test = String.format(MainActivity.this.getString(R.string.test), "nihaoaa");
        mSpannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
//                XXPermissions.startPermissionActivity(context, permissions);
                Toast.makeText(context, "点击了", Toast.LENGTH_LONG).show();
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

    public void test() {
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
        Log.d("test", "time   " + time + "   day = " + day);


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
        Log.d("test1", "   time1 = " + time1 + "    month  " + month + " day1  " + day1 + " 最终   "
                + String.valueOf(time1) + String.valueOf(month) + String.valueOf(day1));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    // JSON字符串示例
    String jsonStr = "[{\"name\":\"John\", \"age\":30}, {\"name\":\"Jane\", \"age\":25}]";

}