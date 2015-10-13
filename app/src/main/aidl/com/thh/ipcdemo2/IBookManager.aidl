// IBookManager.aidl
package com.thh.ipcdemo2;

// Declare any non-default types here with import statements
import com.thh.ipcdemo2.Book;

interface IBookManager {

    List<Book> getBookList();
    void addBook(in Book book);
}
