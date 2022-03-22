// IOnNewBookArrivedListener.aidl
package com.zd.study;

// Declare any non-default types here with import statements
import com.zd.study.Book;
interface IOnNewBookArrivedListener {
  void onNewBookArrived(in Book newBook);
}