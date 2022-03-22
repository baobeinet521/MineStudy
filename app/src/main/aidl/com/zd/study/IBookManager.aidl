// IBookManager.aidl
package com.zd.study;

// Declare any non-default types here with import statements
import com.zd.study.Book;
import com.zd.study.IOnNewBookArrivedListener;
//要传递的对象要放在java文件里，目录要和aidl里面的文件路径保持一致
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<Book> getBooks();
    void addBook(in Book book);
    void registerListener(IOnNewBookArrivedListener listener);
    void unregisterListener(IOnNewBookArrivedListener listener);
}