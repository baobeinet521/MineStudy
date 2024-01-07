package com.zd.study.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zd.study.R;

public class WebViewTestActivity extends AppCompatActivity {
    private TextView mNotice;
    private WebView mWebView;
    private String a = "我行目前发行的薪金卡，分为标准版薪金卡、尊享薪金卡及分行联名薪金卡。尊享薪金卡与标准版薪金卡区别如下：<br><table border=\"1\"  cellspacing=\"0\" cellpadding=\"0\"><tr><th></th><th><center>尊享薪金卡</center></th><th><center>标准版薪金卡</center></th></tr><tr><td>手续费优惠</td><td>免年费、工本费、每月免前3笔同城及异地ATM跨行取现手续费</td><td>免年费、工本费、每月免前3笔仅同城ATM跨行取现手续费</td></tr><tr><td>卡样</td><td>卡面有“尊享”字样</td><td>分别有“年年有余”、“步步生花”、“源源不断”</td></tr></table>";

    private String b = "<p>活期盈是交通银行手机银行专属的现金管理服务，支持自动转入或手动转入，且受限额控制。<br><span style=\"font-weight: bold;\">转入限额:</span>“活期盈”可最多签约10款现金管理财产品，单笔转入最低起点0.01元，单只产品最高可持有金额为1万元，如签约10只产品，最高可持有金额为10万元。(收益结转带来的总金额增加不受限制)。  <br><span style=\"font-weight: bold;\">自动转入:</span>每个开放日15:00前系统根据设置的留存金额(最低留存金额为1000元)自动发起一次转入操作，即超出借记卡留存金额的超额资金自动购买活期类理财产品。<br><span style=\"font-weight: bold;\">手动转入:</span>7*24小时均可转入活期盈，即一键购买活期类理财产品。您可以下载最新版手机银行并通过页面上方“放大镜”搜索“活期盈”，点击页面底部“转入”，输入转入金额，进行手动转入操作。</p><p><span style=\"font-weight: bold;\">转入顺序:</span>若签约多只产品，系统会依照最近一次签约时产品的7日年化收益率从高到低的排序作为“申购排序”，客户可手动调整“申购排序”。<br><span style=\"font-weight: bold;\">收益计算:</span>工作日15:00前转入活期盈的资金，下一个工作日开始计算收益，再下一个自然日收益可查。若工作日15:00后提交转入操作，视为下一开放日的转入。</p>";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_webview);
        mNotice = findViewById(R.id.text_notice);
        mWebView = findViewById(R.id.web_view);
//        mNotice.setText(a);
        mNotice.post(new Runnable() {
            @Override
            public void run() {
                int widthMeasureMode = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.EXACTLY);
                int heightMeasureMode = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                mNotice.measure(widthMeasureMode, heightMeasureMode);
                int measureHeight = mNotice.getMeasuredHeight();
                Log.d("WebViewTestActivity", "measureHeight    " + measureHeight);
            }
        });

        mWebView.setBackgroundColor(0);
        mWebView.setScrollContainer(false);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.loadDataWithBaseURL(null, b, "text/html", "UTF-8", null);
        //设置为ChromeClinet 才能执行js代码
//        WebChromeClient webChromeClient = new WebChromeClient();
//        mWebView.setWebChromeClient(webChromeClient);
//        mWebView.loadUrl("file:///android_asset/testszqb_has_params_type.html");

    }
}
