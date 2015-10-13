package com.thh.ipcdemo2.custom;

import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;

import com.thh.ipcdemo2.Book;

import java.util.List;

/**
 * Created by TangHui on 2015/10/13.
 */
public class BookManagerImpl extends Binder implements CIBookManager {

    public BookManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    /**
     * Cast an IBinder object into an com.thh.ipcdemo2.IBookManager interface,
     * generating a proxy if needed.
     */
    public static CIBookManager asInterface(android.os.IBinder obj) {
        Log.i("thhi","[BookManagerImpl asInterface]");
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof CIBookManager))) {
            Log.i("thhi","[BookManagerImpl asInterface equal process]");
            return ((CIBookManager) iin);
        }
        Log.i("thhi","[BookManagerImpl asInterface not equal process]");
        return new BookManagerImpl.Proxy(obj);
    }

    @Override
    public android.os.IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        Log.i("thhi","[BookManagerImpl onTransact]");
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getBookList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<com.thh.ipcdemo2.Book> _result = this.getBookList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }
            case TRANSACTION_addBook: {
                data.enforceInterface(DESCRIPTOR);
                com.thh.ipcdemo2.Book _arg0;
                if ((0 != data.readInt())) {
                    _arg0 = com.thh.ipcdemo2.Book.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                this.addBook(_arg0);
                reply.writeNoException();
                return true;
            }
        }
        return super.onTransact(code, data, reply, flags);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        Log.i("thhi","[BookManagerImpl getBookList]");
        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        Log.i("thhi","[BookManagerImpl addBook]");
    }

    private static class Proxy implements CIBookManager {
        private android.os.IBinder mRemote;

        Proxy(android.os.IBinder remote) {
            mRemote = remote;
        }

        @Override
        public android.os.IBinder asBinder() {
            return mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public java.util.List<com.thh.ipcdemo2.Book> getBookList() throws android.os.RemoteException {
            Log.i("thhi","[BookManagerImpl Proxy getBookList]");
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<com.thh.ipcdemo2.Book> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(BookManagerImpl.TRANSACTION_getBookList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(com.thh.ipcdemo2.Book.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public void addBook(com.thh.ipcdemo2.Book book) throws android.os.RemoteException {
            Log.i("thhi","[BookManagerImpl Proxy addBook]");
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                if ((book != null)) {
                    _data.writeInt(1);
                    book.writeToParcel(_data, 0);
                } else {
                    _data.writeInt(0);
                }
                mRemote.transact(BookManagerImpl.TRANSACTION_addBook, _data, _reply, 0);
                _reply.readException();
            } finally {
                _reply.recycle();
                _data.recycle();
            }
        }
    }
}
