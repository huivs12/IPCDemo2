package com.thh.ipcdemo2.custom;

import android.os.IInterface;

/**
 * Created by TangHui on 2015/10/13.
 */
public interface CIBookManager extends IInterface {

    static final java.lang.String DESCRIPTOR = "com.thh.ipcdemo2.IBookManager";
    static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);

    public java.util.List<com.thh.ipcdemo2.Book> getBookList() throws android.os.RemoteException;

    public void addBook(com.thh.ipcdemo2.Book book) throws android.os.RemoteException;
}
