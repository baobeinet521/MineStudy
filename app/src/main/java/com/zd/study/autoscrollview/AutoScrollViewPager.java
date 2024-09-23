package com.zd.study.autoscrollview;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zd.study.utils.DisplayUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AutoScrollViewPager<T extends PagerAdapter> extends RelativeLayout {

    private static final int MESSAGE_NEXT = 1;
    private BannerViewPager viewPager;
    private PagerAdapter mAdapter;
    private LinearLayout mLinearLayout;
    private Context mContext;
    private int oldPosition = 0; //上一个位置
    private int currentIndex = 1; //当前位置
    private long time = 3000; //自动播放时间
    private boolean autoPlay = true;//是否自动播放
    private int mLastX = 0;//上次滑动的坐标X
    private int mLastY = 0;//上次滑动的坐标Y

    public static final int CENTER = 0;
    public static final int LEFT = 1;

    @IntDef({CENTER, LEFT})
    @Retention(RetentionPolicy.SOURCE)
    @interface AutoScrollDotGravity {
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            if (message.what == MESSAGE_NEXT) {
                viewPager.setCurrentItem(++currentIndex);
            }
        }
    };

    public AutoScrollViewPager(@NonNull Context context) {
        this(context, null);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 动态添加viepager和小圆点
     */
    private void init() {
        mContext = getContext();
        setClipChildren(true);
        setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_YES);
        setFocusable(true);
        setFocusableInTouchMode(true);
        viewPager = new BannerViewPager(mContext);
        viewPager.setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO);
        viewPager.setFocusable(false);

        LayoutParams paramsViewPager = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewPager.setLayoutParams(paramsViewPager);
        addView(viewPager);

        mLinearLayout = new LinearLayout(mContext);
        mLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL); //小圆点的位置
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams paramsLinearLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        paramsLinearLayout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mLinearLayout.setLayoutParams(paramsLinearLayout);
        mLinearLayout.setPadding(DisplayUtil.dpToPx(mContext, (float) 15), 0, 0, DisplayUtil.dpToPx(mContext, (float) 12));
        addView(mLinearLayout);
    }

    /**
     * 设置适配器
     *
     * @param adapter
     */
    public void setAdapter(T adapter) {
        mAdapter = adapter;
        viewPager.setAdapter(mAdapter);
        initViewPager(mAdapter.getCount());
    }

    /**
     * 设置轮播间隔时长
     *
     * @param time 毫秒
     */
    public void setTime(long time) {
        if (time > 0) {
            this.time = time;
        }
    }

    /**
     * 初始化viewPager
     *
     * @param num
     */
    private void initViewPager(int num) {
        if (num <= 3) {
            mLinearLayout.setVisibility(INVISIBLE);
        } else {
            mLinearLayout.setVisibility(VISIBLE);
        }
        if (mAdapter == null) {
            return;
        }
        //设置缓存页面数
        viewPager.setOffscreenPageLimit(1);
        //设置2图之间间距
//        viewPager.setPageMargin(DisplayUtil.dip2px(mContext, (float) 20));

        viewPager.clearOnPageChangeListeners();//一定要清
        oldPosition = 0; //上一个位置
        currentIndex = 1; //当前位置
        viewPager.setCurrentItem(currentIndex);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    currentIndex = mAdapter.getCount() - 2;
                } else if (position == mAdapter.getCount() - 1) {
                    currentIndex = 1;
                } else {
                    currentIndex = position;
                }
                //此处currentIndex是从1开始的
                if (mLinearLayout.getChildAt(oldPosition) != null) {
                    mLinearLayout.getChildAt(oldPosition).setEnabled(false);
                }
                if (mLinearLayout.getChildAt(currentIndex - 1) != null) {
                    mLinearLayout.getChildAt(currentIndex - 1).setEnabled(true);
                }
                oldPosition = currentIndex - 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    viewPager.setCurrentItem(currentIndex, false);
                    if (autoPlay) {
                        play();
                    }
                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    cancel();
                }
            }
        });
        setIndicatorDot();
    }

    /**
     * 设置小圆点
     */
    private void setIndicatorDot() {
        mLinearLayout.removeAllViews();//一定要清
        for (int i = 0; i < mAdapter.getCount() - 2; i++) {
            //添加底部灰点
            View v = new View(mContext);
//            v.setBackgroundResource(R.drawable.gallery_point_selector);
            v.setEnabled(false);
            //指定其大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DisplayUtil.dpToPx(getContext(), 6f), DisplayUtil.dpToPx(getContext(), 2f));
            params.rightMargin = DisplayUtil.dpToPx(getContext(), 4f);
            v.setLayoutParams(params);
            mLinearLayout.addView(v);
        }
    }

    /**
     * 清除指示器状态
     */
    private void clearIndicatorDotStatus() {
        int size = mLinearLayout.getChildCount();
        for (int i = 0; i < size; i++) {
            mLinearLayout.getChildAt(i).setEnabled(false);
        }
    }

    /**
     * 播放 根据autoplay
     */
    public void play() {
        cancel();
        if (mAdapter.getCount() > 3) {
            Message message = new Message();
            message.what = MESSAGE_NEXT;
            handler.sendMessageDelayed(message, time);
        }
    }

    /**
     * 取消播放
     */
    public void cancel() {
        handler.removeMessages(MESSAGE_NEXT);
    }

    /**
     * 设置是否自动播放
     *
     * @param autoPlay
     */
    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
        if (!autoPlay) {
            cancel();
        }
    }

    /**
     * 是否自动自动轮播
     *
     * @return
     */
    public boolean isAutoPlay() {
        return autoPlay;
    }

    /**
     * 设置是否支持手势滑动
     *
     * @param isScroll
     */
    public void setScroll(boolean isScroll) {
        if (null == viewPager)
            return;
        viewPager.setScroll(isScroll);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = Math.abs(x - mLastX);
                int deltaY = Math.abs(y - mLastY);
                if (deltaY > deltaX) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

    public BannerViewPager getViewPager() {
        return viewPager;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (autoPlay) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    play();
                }
            },time);
        }
    }

    public void setDotGravity(@AutoScrollDotGravity int gravity) {
        if (gravity == CENTER) {
            if (mLinearLayout != null) {
                mLinearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
                mLinearLayout.setPadding(0, 0, 0, DisplayUtil.dpToPx(mContext, (float) 12));
            }
        } else if (gravity == LEFT) {
            if (mLinearLayout != null) {
                mLinearLayout.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                mLinearLayout.setPadding(DisplayUtil.dpToPx(mContext, (float) 15), 0, 0, DisplayUtil.dpToPx(mContext, (float) 12));
            }
        }
    }
}
