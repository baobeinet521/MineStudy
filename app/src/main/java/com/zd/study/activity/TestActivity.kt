package com.zd.study.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.zd.study.R
import com.zd.study.broadcast.TestBroadcastReceiverActivity
import com.zd.study.service.ServiceTestActivity
import java.io.File
import java.io.IOException
import kotlin.random.Random


class TestActivity : AppCompatActivity(), View.OnClickListener {
    var TAG = "TestActivity"
    private var mTestBtn: Button? = null
    private var mTouchEventTestBtn: Button? = null
    private var mServiceTestBtn: Button? = null
    private var mBroadcastReceiverBtn: Button? = null
    private var mAIDLBtn: Button? = null
    private var mDrawBtn: Button? = null
    private var mTestLocalThreadBtn: Button? = null
    private var mVideoTestBtn: Button? = null
    private var mFrescoTestBtn: Button? = null
    private var textView: TextView? = null

    var CanShowFloat = false

    private val REQUEST_OVERLAY = 5004

    private val PICK_IMAGE_VIDEO = 5005
    private val SEND_SMS = 1000

    private val mSendSMSUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "------------onCreate---------")
        setContentView(R.layout.activity_test_layout)
        mTestBtn = findViewById(R.id.test_activity_btn)
        mTestBtn?.setOnClickListener(this)
        mTouchEventTestBtn = findViewById(R.id.touch_event_btn)
        mTouchEventTestBtn?.setOnClickListener(this)
        mServiceTestBtn = findViewById(R.id.service_test_btn)
        mServiceTestBtn?.setOnClickListener(this)
        mBroadcastReceiverBtn = findViewById(R.id.broadcast_test_btn)
        mBroadcastReceiverBtn?.setOnClickListener(this)
        mAIDLBtn = findViewById(R.id.aidl_test_btn)
        mAIDLBtn?.setOnClickListener(this)
        mDrawBtn = findViewById(R.id.draw_view_test_btn)
        mDrawBtn?.setOnClickListener(this)
        mTestLocalThreadBtn = findViewById(R.id.local_thread_test_btn)
        mTestLocalThreadBtn?.setOnClickListener(this)
        mVideoTestBtn = findViewById(R.id.vedio_test_btn)
        mVideoTestBtn?.setOnClickListener(this)
        mFrescoTestBtn = findViewById(R.id.fresco_test_btn)
        mFrescoTestBtn?.setOnClickListener(this)
        textView = findViewById<TextView>(R.id.textView)
        textView?.text = "测试一下事件分发activity学习"
        var a = textView?.text
        textView?.post {
            var count  = textView?.layout?.lineCount ?: 0
            if(count > 0){
                var endOffset = textView?.layout?.getLineEnd(0)?:0
                if(endOffset > 1){
                    val lastChar = textView?.text?.get(endOffset - 1)
                    var test = "ceshi"
                }

            }

            var test = "ceshi"
        }

        test()
    }


    private fun test1(){

    }
    private fun test() {

        val text = "这是 一个例子，其中一部分文字会有背景色。"
        val spannableString = SpannableString(text)


// 设置背景色的文字范围
//        val startIndex = text.indexOf("部分")
//        val endIndex = startIndex + "部分".length
        spannableString.setSpan(
            BackgroundColorSpan(Color.YELLOW),
            0,
            2,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val spannableString10 = SpannableString("这是 一个例子，其中一部分文字会有背景色。")
        val imageSpan = ImageSpan(this, R.drawable.corner_8dp_bg_blue_line_blue1)
        spannableString10.setSpan(imageSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

// 应用到TextView
        textView?.text = spannableString10
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onClick(v: View) {
        val id = v.id
        val intent = Intent()
        when (id) {
            R.id.test_activity_btn -> //                Intent intent = new Intent(this, HandlerActivity.class);
//                 intent = new Intent(this, LifeCycleActivity.class);
//                Intent intentTest = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setClass(this, TestFlagActivity.class);
//                startActivity(intentTest);
//                SingleInstanceD mSingleInstanceD = SingleInstanceD.getInstance();
//                System.out.println("_____分割线_______");
//                mSingleInstanceD.testPrint();

//                startActivity(intent);

//                Intent i = new Intent(Intent.ACTION_PICK);
////                i.addCategory(Intent.CATEGORY_OPENABLE);
//                i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                i.putExtra(Intent.EXTRA_INTENT, i);
////                i.setType("image/*|audio/*");
//                startActivityForResult(i, 0);

                //打开的是文件夹
//                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
//                galleryIntent.setType("image/*");//图片
//                startActivityForResult(galleryIntent,1);

//                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
//                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
//                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
//                startActivityForResult(intentToPickPic, PICK_IMAGE_VIDEO);

//                GuidePopupDialog dialog = new GuidePopupDialog(this);
//                dialog.show();


//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(Uri.parse("cn.12306.comm://url"));
//                intent.addCategory("android.intent.category.DEFAULT");
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);


//                String str = "cn.12306.comm://url";
//                String str1 = Uri.encode(str);
//                Log.d(TAG, "------------str1    " + str1);
//
//                String str2 = Uri.encode(str, ":/");
//                Log.d(TAG, "------------str2    " + str2);
//
//                String str3 = Uri.encode(str, "://");
//                Log.d(TAG, "------------str3    " + str3);

//                intent.setClass(this, RidingCodeActivity.class);
//                startActivity(intent);

//                String encode = Uri.encode("https://www.baidu.com");
//                Log.e("ceshi", encode);


//                test(testStr, 4000);

//                getReadPermissions();


//                getVersionCode(MainActivity.this);

//                intent.setClass(this, AnimationTestActivity.class);
//                startActivity(intent);
//                mSendSMSUri
//                mSendSMSUri = Uri.parse("smsto:");
//                requestPermission();

//                getReadAndWritePermissions();
//                String str = "/oss/";
//                Log.e("ceshi", "=========   " + str);
                schemeTest()

            R.id.touch_event_btn -> {
//                intent.setClass(this, TouchEventTestActivity::class.java)
//                startActivity(intent)
                for (i in 0 until 4) {
                    Log.e("test111", "i   " + i)

                }


                val layoutManager: LinearLayoutManager =
                    object : LinearLayoutManager(this, HORIZONTAL, false) {
                        override fun canScrollHorizontally(): Boolean {
                            return false
                        }
                    }
//                recyclerView.setLayoutManager(layoutManager)
            }

            R.id.service_test_btn -> {
                intent.setClass(this, ServiceTestActivity::class.java)
                startActivity(intent)
            }

            R.id.broadcast_test_btn -> {
                intent.setClass(this, TestBroadcastReceiverActivity::class.java)
                startActivity(intent)
            }

            R.id.aidl_test_btn -> {
                intent.setClass(this, BookManagerActivity::class.java)
                startActivity(intent)
            }

            R.id.draw_view_test_btn -> if (CanShowFloat) {
                intent.setClass(this, DrawViewActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@TestActivity,
                    "未设置悬浮窗权限,请开启权限！",
                    Toast.LENGTH_SHORT
                ).show()
                RequestOverlayPermission(this)
            }

            R.id.local_thread_test_btn -> {
                intent.setClass(this, VedioPlayerTestActivity::class.java)
                startActivity(intent)
            }

            R.id.vedio_test_btn -> {
                intent.setClass(this, VedioPlayerTestActivity::class.java)
                startActivity(intent)
            }

            R.id.fresco_test_btn -> {
//                intent.setClass(this, FrescoTestActivity::class.java)
//                startActivity(intent)
                testLet()
            }
        }
    }


    fun testLet() {
//sampleStart
//        val numbers = listOf("one", "two", "three", "four")
//        val modifiedFirstItem = numbers.first().let { firstItem ->
//            println("The first item of the list is '$firstItem'")
//            if (firstItem.length >= 5) firstItem else "!" + firstItem + "!"
//        }.uppercase()
//        println("First item after modifications: '$modifiedFirstItem'")
//sampleEnd

//        val numbers = mutableListOf("one", "two", "three")
//        with(numbers) {
//            println("'with' is called with argument $this")
//            println("It contains $size elements")
//        }


//        val numbers = mutableListOf("one", "two", "three")
//        val firstAndLast = with(numbers) {
//            "The first element is ${first()}," +
//                    " the last element is ${last()}"
//        }
//        println(firstAndLast)


//        val service = MultiportService("https://example.kotlinlang.org", 80)
//
//        val result = service.run {
//            port = 8080
//            query(prepareRequest() + " to port $port")
//        }
//
//        // 同样的代码如果用 let() 函数来写:
//        val letResult = service.let {
//            it.port = 8080
//            it.query(it.prepareRequest() + " to port ${it.port}")
//        }
////sampleEnd
//        println(result)
//        println(letResult)
//        val hexNumberRegex = run {
//            val digits = "0-9"
//            val hexDigits = "A-Fa-f"
//            val sign = "+-"
//
//            Regex("[$sign]?[$digits$hexDigits]+")
//        }
//
//        for (match in hexNumberRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
//            println(match.value)
//        }

//        val adam = Person("Adam").apply {
//            age = 32
//            city = "London"
//        }
//        println(adam)

//        val numbers = mutableListOf("one", "two", "three")
//        numbers
//            .also { println("The list elements before adding new one: $it") }
//            .add("four")

        val number = Random.nextInt(100)

        val evenOrNull = number.takeIf { it % 2 == 0 }
        val oddOrNull = number.takeUnless { it % 2 == 0 }
        println("even: $evenOrNull, odd: $oddOrNull")

    }


    class MultiportService(var url: String, var port: Int) {
        fun prepareRequest(): String = "Default request"
        fun query(request: String): String = "Result for query '$request'"
    }

    data class Person(var name: String, var age: Int = 0, var city: String = "")

    fun schemeTest() {
        val url = "com.test.demo://"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivityForResult(intent, 10000)
    }


    fun getVersionCode(context: Context): Int {
        val info: PackageInfo
        var code = 0
        try { //context.getPackageName()
//            String a  = "com.bankcomm.Bankcomm";
            val a = "com.bankcomm"
            info = context.packageManager.getPackageInfo(a, 0)
            code = info.versionCode
            val name = info.versionName
            Log.d("ceshi", " 交通银行 code ====    $code   name      $name")
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return code
    }

    fun composeMmsMessage1() {
        val intent = Intent(Intent.ACTION_VIEW, mSendSMSUri)
        //        intent.setData(Uri.parse("smsto://15912345678"));  // This ensures only SMS apps respond
//        intent.putExtra("sms_body", message);
//        intent.putExtra(Intent.EXTRA_STREAM, mSendSMSUri);
        intent.putExtra("address", "18721106192")
        intent.putExtra("sms_body", "短信内容1") // 设置发送的内容
        intent.type = "vnd.android-dir/mms-sms"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, SEND_SMS)
        }


//        Uri smstoUri = Uri.parse("smsto:");
//        Intent intent = new Intent(Intent.ACTION_VIEW,smstoUri);
//        intent.putExtra("address","18721106192"); // 没有电话号码的话为默认的，即显示的时候是为空的
//        intent.putExtra("sms_body","短信内容"); // 设置发送的内容
//        intent.setType("vnd.android-dir/mms-sms");
//        startActivity(intent);
    }


    private fun requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val checkCallPhonePermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    SEND_SMS
                )
                return
            } else {
                composeMmsMessage1()
                //已有权限
            }
        } else {
            //API 版本在23以下
            composeMmsMessage1()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "------------onStart---------")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "------------onRestart---------")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "------------onResume---------")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "------------onPause---------")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "------------onStop---------")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "------------onDestroy---------")
    }


    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "------------onSaveInstanceState---------")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "------------onRestoreInstanceState---------")
    }

    fun gotoDrawView() {
        val intent = Intent()
        intent.setClass(this, DrawViewActivity::class.java)
        startActivity(intent)
    }

    /**
     * 动态请求悬浮窗权限
     */
    fun RequestOverlayPermission(activity: Activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(activity)) {
                val ACTION_MANAGE_OVERLAY_PERMISSION =
                    "android.settings.action.MANAGE_OVERLAY_PERMISSION"
                val packageName = packageName
                Log.e("ceshi", "packageName   = $packageName")
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse(
                        "package:$packageName"
                    )
                )
                activity.startActivityForResult(intent, REQUEST_OVERLAY)
            } else {
                CanShowFloat = true
                gotoDrawView()
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_OVERLAY) {       // 从应用权限设置界面返回
            if (resultCode == Activity.RESULT_OK) {
                CanShowFloat = true // 设置标识为可显示悬浮窗
                gotoDrawView()
            } else {
                CanShowFloat = false
                // 若当前未允许显示悬浮窗，则提示授权
                if (!Settings.canDrawOverlays(this)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setCancelable(false)
                    builder.setTitle("悬浮窗权限未授权")
                    builder.setMessage("应用需要悬浮窗权限，以展示浮标")
                    builder.setPositiveButton(
                        "去添加 权限"
                    ) { dialog, which ->
                        dialog.dismiss()
                        RequestOverlayPermission(this@TestActivity)
                    }
                    builder.setNegativeButton(
                        "拒绝则 退出"
                    ) { dialog, which ->
                        dialog.dismiss()
                        Toast.makeText(
                            this@TestActivity,
                            "未设置悬浮窗权限！",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    builder.show()
                }
            }
        } else if (requestCode == PICK_IMAGE_VIDEO) {
            val uri = data!!.data
            //通过uri的方式返回，部分手机uri可能为空
            if (uri != null) {
                val path = uri.path
                Log.e(TAG, "path  $path")
                try {
                    //通过uri获取到bitmap对象
                    val bitmap = MediaStore.Images.Media.getBitmap(
                        contentResolver, uri
                    )
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else {
                //部分手机可能直接存放在bundle中
                val bundleExtras = data.extras
                if (bundleExtras != null) {
                    val bitmaps = bundleExtras.getParcelable<Bitmap>("data")
                }
            }
        } else if (requestCode == 10000) {
            val result = data!!.extras!!.getString("result") //得到新Activity 关闭后返回的数据
            Log.i(TAG, result!!)
        }
    }

    var testStr =
        """
        说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账1234567890123单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收987654你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份456年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年yy度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年7899ut8度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收
        6230580000261339829有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有123一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收
        6230580000261339829有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有123456789012345678901234567890123待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收
        6230580000261339828账单待查收你有一份年度账单待查有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收说话 你好   这样的  ，，你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你说话 你好待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你有一份年度账单待查收你6230580000261339830
        """.trimIndent()


    fun test(testStr: String, length: Int) {
        val result: MutableList<String> = ArrayList()
        Log.d("zdTest", "初始字符串长度是   " + testStr.length)
        val forLen = Math.min(testStr.length, length)
        var temp = ""
        val startTime = System.currentTimeMillis()
        Log.d("zdTest", "开始时间   $startTime")
        for (i in 0 until forLen) {
            val subStr = testStr[i]
            if (subStr >= '0' && subStr <= '9') {
                temp += subStr
            } else {
                if (temp.length >= 13 && temp.length <= 30) {
                    result.add(temp)
                }
                temp = ""
            }
        }
        //最后一位如果是数字的话 需要在判断一下
        if (temp.length >= 13 && temp.length <= 30) {
            result.add(temp)
        }
        val stopTime = System.currentTimeMillis()
        Log.d("zdTest", "开始时间   $stopTime")
        if (result != null) {
            for (i in result.indices) {
                Log.d("zdTest", "i =  " + i + "  最终的卡号有   " + result[i])
            }
        }
    }

    fun charTest() {
        val test = "0123456789"
        for (i in 0 until test.length) {
            val subStr = test[i]
            val `is` = subStr >= '0' && subStr <= '9'
            Log.d("zdTest", "is   $`is`  i = $i")
        }
    }


    /**
     * 权限读取权限
     */
    private fun getReadAndWritePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this@TestActivity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    this@TestActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //没有权限
                ActivityCompat.requestPermissions(
                    this, arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), 10001
                )
            } else { //如果已经获取到了权限则直接进行下一步操作
                Log.e(TAG, "如果已经获取到了权限则直接进行下一步操作")
                testCreateFile()
            }
        }
    }

    fun testCreateFile() {
//        String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +"/Zdtest1111/";
        val BASE_PATH = Environment.getExternalStorageDirectory()
            .toString() + File.separator + "Zdtest1111" + File.separator
        Log.e(TAG, "BASE_PATH  = $BASE_PATH")
        val file = File(BASE_PATH)
        // video文件夹不存在
        if (!file.exists()) {
            // 创建文件夹
            file.mkdir()
            Log.e(TAG, "BASE_PATH  文件不存在 ")
        } else {
            Log.e(TAG, "BASE_PATH  = 文件已经存在 ")
        }
    }

    /**
     * 权限的验证及处理，相关方法
     */
    private fun getReadPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.RECEIVE_SMS
                    ) or ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) { //是否请求过该权限
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ), 10001
                    )
                } else { //没有则请求获取权限，示例权限是：存储权限和短信权限，需要其他权限请更改或者替换
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ), 10001
                    )
                }
            } else { //如果已经获取到了权限则直接进行下一步操作
                Log.e(TAG, "onRequestPermissionsResult")
            }
        }
    }

    /**
     * 一个或多个权限请求结果回调
     * 当点击了不在询问，但是想要实现某个功能，必须要用到权限，可以提示用户，引导用户去设置
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            10001 -> {
                var i = 0
                while (i < grantResults.size) {

//                   如果拒绝获取权限
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否勾选禁止后不再询问
                        val flag = ActivityCompat.shouldShowRequestPermissionRationale(
                            this,
                            permissions[i]!!
                        )
                        if (flag) {
                            getReadPermissions()
                            return  //用户权限是一个一个的请求的，只要有拒绝，剩下的请求就可以停止，再次请求打开权限了
                        } else { // 勾选不再询问，并拒绝
                            Toast.makeText(this, "请到设置中打开权限", Toast.LENGTH_LONG).show()
                            return
                        }
                    }
                    i++
                }
            }

            SEND_SMS -> Toast.makeText(this, "发送短信", Toast.LENGTH_LONG).show()
            else -> {}
        }
    }
}