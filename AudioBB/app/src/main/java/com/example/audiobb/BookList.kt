package com.example.audiobb

import Book
import java.io.Serializable

class BookList() : Serializable {
    var bookList: List<Book> = listOf()

    fun get(index: Int): Book {
        return if (-1 < index && index < size()) {
            bookList[index]
        } else {
            Book("Book does not exist", "Error")
        }
    }

    fun insert(bookList: List<Book>){
        this.bookList = this.bookList + bookList
    }

    fun size(): Int {
        return bookList.size
    }
}