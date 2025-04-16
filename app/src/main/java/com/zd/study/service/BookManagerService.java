//package com.zd.study.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.PackageManager;
//import android.os.Binder;
//import android.os.IBinder;
//import android.os.Parcel;
//import android.os.RemoteCallbackList;
//import android.os.RemoteException;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//
//import com.zd.study.Book;
//import com.zd.study.IBookManager;
//import com.zd.study.IOnNewBookArrivedListener;
//
//import java.util.List;
//import java.util.concurrent.CopyOnWriteArrayList;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//public class BookManagerService extends Service {
//    public static String TAG = "BookManagerService";
//    private final AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);
//    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();
//    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<>();
//
//    private Binder mBinder = new IBookManager.Stub() {
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//        }
//
//        @Override
//        public List<Book> getBooks() throws RemoteException {
////            SystemClock.sleep(50000);
//            return mBookList;
//        }
//
//        @Override
//        public void addBook(Book book) throws RemoteException {
//            mBookList.add(book);
//        }
//
//        @Override
//        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            mListenerList.register(listener);
//        }
//
//        @Override
//        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            mListenerList.unregister(listener);
//        }
//
//        @Override
//        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//            //权限校验
////            Log.e(TAG,"onTransact  check permission ");
////            int check = checkCallingOrSelfPermission("com.zd.study.permission.ACCESS_BOOK_SERVICE");
////            if(check == PackageManager.PERMISSION_DENIED){
////                return false;
////            }
////
////            String packageName = null;
////            String[] packages = getPackageManager().getPackagesForUid(getCallingUid());
////            if(packages != null && packages.length > 0){
////                packageName = packages[0];
////            }
////            if(!packageName.startsWith("com.zd.study")){
////                return false;
////            }
//            return super.onTransact(code, data, reply, flags);
//        }
//    };
//
//
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mBookList.add(new Book(1, "Android"));
//        mBookList.add(new Book(2, "ios"));
////        sendNotify()
//    }
//
//    public void sendNotify(){
//        new Thread(new ServerWork()).start();
//    }
//
//    public class ServerWork implements Runnable{
//
//        @Override
//        public void run() {
//            while (!mIsServiceDestoryed.get()){
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                int bookId = mBookList.size() + 1;
//                Book newBook = new Book(bookId,"new book#" + bookId);
//                try {
//                    onNewBookArrived(newBook);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//    }
//
//    private void onNewBookArrived(Book book) throws  RemoteException{
//        mBookList.add(book);
//        int n = mListenerList.beginBroadcast();
//        Log.e(TAG,"onNewBookArrived,notify listeners: " + n);
//        for (int i = 0; i < n; i++){
//            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
//            if(listener != null){
//                Log.e(TAG,"onNewBookArrived,notify listeners: " + listener);
//                listener.onNewBookArrived(book);
//            }
//        }
//        mListenerList.finishBroadcast();
//
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
////        int check = checkCallingOrSelfPermission("com.zd.study.permission.ACCESS_BOOK_SERVICE");
////        Log.e(TAG,"onBind,check permission:  check = " + check);
////        if(check == PackageManager.PERMISSION_DENIED){
////            return null;
////        }
//        return mBinder;
//    }
//
//    @Override
//    public void unbindService(ServiceConnection conn) {
//        Log.e(TAG,"unbindService-------- ");
//        super.unbindService(conn);
//    }
//}
