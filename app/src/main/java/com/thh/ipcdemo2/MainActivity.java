package com.thh.ipcdemo2;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.thh.ipcdemo2.custom.BookManagerImpl;
import com.thh.ipcdemo2.custom.CIBookManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void write(View view){
        User user = new User("tom",13,true);
        Log.i("thh-i", "[MainActivity write] user hashCode:" + user.hashCode() + "--toString:" + user.toString());
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getExternalCacheDir()+"/cache.txt"));
            outputStream.writeObject(user);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reader(View view){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getExternalCacheDir()+"/cache.txt"));
            User user = (User) inputStream.readObject();
            inputStream.close();

            Log.i("thh-i", "[MainActivity reader] user hashCode:" + user.hashCode() + "--toString:" + user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    CIBookManager bookManager;

    public void bindService(View view){
        bindService();
    }

    private void bindService() {
        Log.i("thhi", "[MainActivity bindService]");
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i("thhi", "[MainActivity bindService onServiceConnected]");
                bookManager = BookManagerImpl.asInterface(service);
                try {
                    service.linkToDeath(mDeathRecipient, 0);
                    List<Book> bookList = bookManager.getBookList();
                    Log.i("thhi","[MainActivity bindService] bookList:"+bookList);
                    bookManager.addBook(new Book(5, "55555"));
                    Log.i("thhi", "[MainActivity bindService] addBook after : bookList:" + bookManager.getBookList());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i("thhi","[MainActivity bindService onServiceDisconnected]");
                bookManager = null;
            }
        }, Service.BIND_AUTO_CREATE);
    }

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (bookManager == null)
                return;
            bookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            bookManager = null;
            //TODO 重新绑定完程Service
            Log.i("thhi","[MainActivity mDeathRecipient binderDied]");
            bindService();
        }
    };

    public void getBookList(View view){
        try {
            Log.i("thhi","[MainActivity bindService] bookList:"+bookManager.getBookList());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void addBook(View view){

    }
}
