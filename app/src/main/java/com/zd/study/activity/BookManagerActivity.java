//package com.zd.study.activity;
//
//import android.app.Activity;
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.os.RemoteException;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.zd.study.Book;
//import com.zd.study.IBookManager;
//import com.zd.study.IOnNewBookArrivedListener;
//import com.zd.study.R;
//import com.zd.study.service.BookManagerService;
//
//import java.util.List;
//
//public class BookManagerActivity extends Activity {
//    public static String TAG = "BookManagerActivity";
//
//    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;
//    private MyHandler mMyHandler = new MyHandler();
//    private IBookManager mRemoteBookManager;
//    private Button mBtn;
//    private Button mTestBtn;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_manager_layout);
//
//        mBtn = findViewById(R.id.bind_service_btn);
//        mTestBtn = findViewById(R.id.test_btn);
//
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "绑定服务click");
//                Intent intent = new Intent(BookManagerActivity.this, BookManagerService.class);
//                bindService(intent,mConnectionService, Context.BIND_AUTO_CREATE);
//            }
//        });
//
//        mTestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            if(mRemoteBookManager != null){
//                                mRemoteBookManager.getBooks();
//                            }
//                        } catch (RemoteException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//
//            }
//        });
//    }
//
//    private ServiceConnection mConnectionService = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            //该方法运行在主线程中，避免执行耗时操作
//            IBookManager bookManager = IBookManager.Stub.asInterface(service);
//            mRemoteBookManager = bookManager;
//            try {
//                List<Book> list = bookManager.getBooks();
//                Log.e(TAG, "--------------查询服务端发送数据----------------");
//                Log.e(TAG, "query book list, list type:" + list.getClass().getCanonicalName());
//                for (int i = 0; i < list.size();i++){
//                    Log.e(TAG, "query book list, list type:" + list.get(i).bookName);
//                }
//
//                Log.e(TAG, "--------------向服务端发送数据----------------");
//                bookManager.addBook(new Book(3,"Android开发艺术探索"));
//
//
//                Log.e(TAG, "--------------查询服务端发送数据----------------");
//                List<Book> listAfter = bookManager.getBooks();
//                Log.e(TAG, "query book list, list type:" + listAfter.getClass().getCanonicalName());
//                for (int i = 0; i < listAfter.size();i++){
//                    Log.e(TAG, "query book list, list type:" + listAfter.get(i).bookName);
//                }
//
//                bookManager.registerListener(mOnNewBookArrivedListener);
//
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            mRemoteBookManager = null;
//            Log.e(TAG, "binder died. name = " + name);
//        }
//
//        @Override
//        public void onBindingDied(ComponentName name) {
//            ServiceConnection.super.onBindingDied(name);
//        }
//    };
//
//    public static class MyHandler extends Handler{
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            switch (msg.what){
//                case MESSAGE_NEW_BOOK_ARRIVED:
//                    Book book = (Book) msg.obj;
//                    Log.e(TAG," receive new book:" + book.bookName);
//                    break;
//                default:
//                    super.handleMessage(msg);
//            }
//
//        }
//    }
//
//    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {
//        @Override
//        public void onNewBookArrived(Book newBook) throws RemoteException {
//            Message message = mMyHandler.obtainMessage();
//            message.what = MESSAGE_NEW_BOOK_ARRIVED;
//            message.obj = newBook;
//            mMyHandler.sendMessage(message);
//        }
//    };
//
//    @Override
//    protected void onDestroy() {
//        Log.e(TAG," onDestroy-----");
//
//        if (mRemoteBookManager != null && mRemoteBookManager.asBinder().isBinderAlive()){
//            try {
//                Log.e(TAG," unregister listener:" + mOnNewBookArrivedListener);
//                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//
//        }
//        unbindService(mConnectionService);
//        super.onDestroy();
//    }
//
//}
