package com.zd.study.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zd.study.R;

public class WebViewTestActivity extends AppCompatActivity {
    private TextView mNotice;
    private TextView mNotice1;
    private WebView mWebView;
    private String a = "我行目前发行的薪金卡，分为标准版薪金卡、尊享薪金卡及分行联名薪金卡。尊享薪金卡与标准版薪金卡区别如下：<br><table border=\"1\"  cellspacing=\"0\" cellpadding=\"0\"><tr><th></th><th><center>尊享薪金卡</center></th><th><center>标准版薪金卡</center></th></tr><tr><td>手续费优惠</td><td>免年费、工本费、每月免前3笔同城及异地ATM跨行取现手续费</td><td>免年费、工本费、每月免前3笔仅同城ATM跨行取现手续费</td></tr><tr><td>卡样</td><td>卡面有“尊享”字样</td><td>分别有“年年有余”、“步步生花”、“源源不断”</td></tr></table>";

    private String b = "<p>活期盈是交通银行手机银行专属的现金管理服务，支持自动转入或手动转入，且受限额控制。<br><span style=\"font-weight: bold;\">转入限额:</span>“活期盈”可最多签约10款现金管理财产品，单笔转入最低起点0.01元，单只产品最高可持有金额为1万元，如签约10只产品，最高可持有金额为10万元。(收益结转带来的总金额增加不受限制)。  <br><span style=\"font-weight: bold;\">自动转入:</span>每个开放日15:00前系统根据设置的留存金额(最低留存金额为1000元)自动发起一次转入操作，即超出借记卡留存金额的超额资金自动购买活期类理财产品。<br><span style=\"font-weight: bold;\">手动转入:</span>7*24小时均可转入活期盈，即一键购买活期类理财产品。您可以下载最新版手机银行并通过页面上方“放大镜”搜索“活期盈”，点击页面底部“转入”，输入转入金额，进行手动转入操作。</p><p><span style=\"font-weight: bold;\">转入顺序:</span>若签约多只产品，系统会依照最近一次签约时产品的7日年化收益率从高到低的排序作为“申购排序”，客户可手动调整“申购排序”。<br><span style=\"font-weight: bold;\">收益计算:</span>工作日15:00前转入活期盈的资金，下一个工作日开始计算收益，再下一个自然日收益可查。若工作日15:00后提交转入操作，视为下一开放日的转入。</p>";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(WebViewTestActivity.this, "hahahhah",Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_test_webview);
        mNotice = findViewById(R.id.text_notice);
        mNotice1 = findViewById(R.id.text_notice1);
        mWebView = findViewById(R.id.web_view);
        String test ="<span style=\"font-weight: bold;\">1111四位数字</span><span style=\"font-weight: bold;\">22两位数字</span><span style=\"font-weight: bold;\">333三位数字</span>  ";
        String[] list = test.split("<span style=\"font-weight: bold;\">");
        String deal = "";
        if(list != null && list.length > 0){
            for(int i  = 0; i < list.length; i++){
                list[i].replace("</span>","</b>");
                deal += "<b>" + list[i];
            }
        }


        String testa= " <b>onclick链接测试：</b>您可以<a id=\"ebank\" onclick=\"fd/FD0/NFD0115.html\">点击这里</a>进入交心存页面。<br><b>a href链接测试：</b>您也可以<a href=\"https://m.bankcomm.com/wap/shtml/wap/cn/15539/16778/16783/list.shtml?channelId=15539\">点击这里</a>查看并扫描二维码后关注。<br><b>font color标红测试：</b>交行<font color=\"#ff0000\">个人网银目前支持的操作系统</font>有win7、win8、win10、win11,以及苹果电脑的mac系统。<br><b>br标签测试：</b>交通银行。<br>注意。<br><b>p标签测试：</b><p>网上支付手机号优先取鉴权手机号(即短信动态密码手机号),若无则取核心手机号码。</p><br><b>br和p标签在中间测试：</b>查看各类沃德金及收藏金产品的销售价格。<br><p>“沃德金”销售采用预约销售模式。<br><b>div标签测试：</b>打印出来保存。</p><div>若您申请的惠民贷未结清。</div><br><b>nbsp空格标签测试:</b>当您使用交行二代usbkey&nbsp; &nbsp;智慧网盾<br><br><b>html标签测试：</b>阳光碧乐活1号起售金额为0.01元。<br><b>科大编辑器标红测试：</b><span style=\"color: rgb(194, 79, 74);\">温馨提示：</span>1.评测成功之后实时生效。<br><b>科大编辑器加粗测试：自动转入:</b>每个开放日<br><b>手动导入的加粗格式b标签测试：</b><b>1.转入限额:</b><br><b>table表格测试：</b>我行目前发行的薪金卡，分为标准版薪金卡、尊享薪金卡及分行联名薪金卡。尊享薪金卡与标准版薪金卡区别如下：<br><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top: 1px solid #000; border-left: 1px solid #000\" border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-top: 1px solid #000; border-left: 1px solid #000\"><tbody><tr><th style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\"></th><th style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\"><center>尊享薪金卡</center></th><th style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 2px solid #000; text-align: center; border-right: 1px solid #000; padding: 3px 5px\"><center>标准版薪金卡</center></th></tr><tr><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">手续费优惠</td><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">免年费、工本费、每月免前3笔同城及异地ATM跨行取现手续费</td><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">免年费、工本费、每月免前3笔仅同城ATM跨行取现手续费</td></tr><tr><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">卡样</td><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">卡面有“尊享”字样</td><td style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\" style=\"border-bottom: 1px solid #000; border-right: 1px solid #000; padding: 3px 5px\">分别有“年年有余”、“步步生花”、“源源不断”</td></tr></tbody></table>";
        mNotice.setText(Html.fromHtml(testa));

//        String testClick = "<a id=\"ebank\" onclick=\"www.baidu.com\">点击这里</a>";

        String testClick = "<a href=\"http://baidu.com\">百度</a>";
        Spanned spannedHtml=Html.fromHtml(testClick);
        mNotice1.setText(getClickableHtml(spannedHtml));
        mNotice1.setMovementMethod(LinkMovementMethod.getInstance());
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



    private void setLinkClickable(final SpannableStringBuilder clickableHtmlBuilder,
                                  final URLSpan urlSpan) {
        int start = clickableHtmlBuilder.getSpanStart(urlSpan);
        int end = clickableHtmlBuilder.getSpanEnd(urlSpan);
        int flags = clickableHtmlBuilder.getSpanFlags(urlSpan);
        ClickableSpan clickableSpan = new ClickableSpan() {
            public void onClick(View view) {
                //Do something with URL here.
                // 如获取url地址，跳转到自己指定的页面
            }
        };
        clickableHtmlBuilder.setSpan(clickableSpan, start, end, flags);
    }

    private CharSequence getClickableHtml(Spanned spannedHtml) {
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
        for(final URLSpan span : urls) {
            setLinkClickable(clickableHtmlBuilder, span);
        }
        return clickableHtmlBuilder;
    }

}
