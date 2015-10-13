package com.thh.ipcdemo2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.thh.ipcdemo2.custom.BookManagerImpl;

import java.util.concurrent.CopyOnWriteArrayList;

public class MyService extends Service {

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<>();

    private Binder binder = new BookManagerImpl();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(0, "00000"));
        mBookList.add(new Book(1, "11111"));
        mBookList.add(new Book(2, "22222"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
