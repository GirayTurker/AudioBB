package com.example.audiobb

import Book
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomViewModel : ViewModel() {
    private val book: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>()
    }

    fun getBook(): LiveData<Book> {
        return book
    }

    fun setBook(_book: Book) {
        book.value = _book
    }
}