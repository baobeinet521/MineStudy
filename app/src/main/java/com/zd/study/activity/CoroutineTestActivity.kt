package com.zd.study.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.util.Log
import com.zd.study.databinding.ActivityCoroutineLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

open class CoroutineTestActivity: AppCompatActivity() {
    private val TAG = "CoroutineTestActivity"
    private lateinit var binding: ActivityCoroutineLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoroutineLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            val str = getMessage()
            Log.d(TAG, "$str  getMessage 函数在 主线程上执行 ")
        }
        /**
         *
         * fetchData 函数在 IO 调度器上执行，而结果在主线程中更新 UI
         */
        GlobalScope.launch(Dispatchers.IO) {
            val data = fetchData()
            withContext(Dispatchers.Main) {
                Log.d(TAG, "$data  fetchData 函数在 IO 调度器上执行，而结果在主线程中更新 UI ") // 在主线程中更新 UI
                binding.testView.text = data
            }
        }

        main()

        /**
         * 可以使用 launch 或 async 函数来创建协程。
         *
         * launch：启动一个协程，不返回结果。
         * async：启动一个协程并返回 Deferred 对象，可以通过该对象获取结果
         */

        val job = GlobalScope.launch {
            val data = fetchData()
            println(data)
        }

        val deferred = GlobalScope.async {
            fetchData()
        }
//        val result = deferred.getCompleted() // 等待结果


    }

    private suspend fun getMessage(){
        return withContext(Dispatchers.IO){
            delay(1000) // 模拟网络请求
            "数据加载完成啦啦啦。。。。"
        }
    }

    private suspend fun fetchData(): String {
        delay(1000) // 模拟网络请求
        return "数据加载完成"
    }

    /**
     * runBlocking 创建了一个阻塞的协程作用域，
     * 在其中启动了一个新的协程（使用 launch 函数）。
     * 该协程调用 fetchData 函数，并打印结果
     */
    fun main() = runBlocking {
        launch {
            val data = fetchData()
            Log.d(TAG, "main  $data")
        }
    }
}