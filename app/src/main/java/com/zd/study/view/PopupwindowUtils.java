package com.zd.study.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zd.study.R;

public class PopupwindowUtils {
    private static PopupWindow popupWindow;

    public static void showPop(Activity activity) {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(activity);
        }
        View view = LayoutInflater.from(activity).inflate(R.layout.pop_view_layout, null);
        View view_bg = view.findViewById(R.id.bg_view);
        ConstraintLayout popView = view.findViewById(R.id.pop_view);
        popupWindow.setContentView(view);
        popupWindow.setClippingEnabled(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
//                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//                lp.alpha = 1f;
//                activity.getWindow().setAttributes(lp);
//                view_bg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.bg_out_anim));
//                view_bg.setVisibility(View.GONE);
//                popView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_out_anim));

            }
        });
//        activity.getWindow().getDecorView()
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//        lp.alpha = 0.5f;
//        lp.verticalMargin = getWindowHeight(activity) - getNavigationBarHeight(activity);
//        lp.height = getWindowHeight(activity) - getNavigationBarHeight(activity);
        activity.getWindow().setAttributes(lp);
        lp.gravity = Gravity.BOTTOM;
        popupWindow.setAnimationStyle(R.style.BottomDialog1);
//        popView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.pop_in_anim));
//        view_bg.setVisibility(View.VISIBLE);
//        view_bg.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.bg_in_anim));
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }


    public static void show1(Context context){
        HalfScreenDialog dialog = new HalfScreenDialog(context, R.layout.pop_view_layout);
        dialog.getWindow().setDimAmount(0f);
        View view = dialog.getContentView();
        View view_bg = view.findViewById(R.id.bg_view);
        ConstraintLayout popView = view.findViewById(R.id.pop_view);

        dialog.show();
    }

    public static int getWindowHeight(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * 获取虚拟操作拦（home等）高度
     */
    public static int getNavigationBarHeight(Activity activity) {
        if (!isNavigationBarShow(activity))
            return 0;
        int height = 0;
        Resources resources = activity.getResources();
        //获取NavigationBar的高度
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0)
            height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 虚拟操作拦（home等）是否显示
     */
    public static boolean isNavigationBarShow(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Display display = activity.getWindowManager().getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(activity).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }
}
