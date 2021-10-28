package com.example.audiobb

import Book
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var customViewModel: CustomViewModel

    private val _CurrentBook = Book("", "")
    var isDouble = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bookList = BookList()
        bookList.insert(
            listOf(
                Book(" THE THOUSAND AUTUMNS OF JACOB DE ZOET", "DAVID MITCHELL"),
                Book("TRAIN DREAMS", "DENIS JOHNSON"),
                Book("THE BUDDHA IN THE ATTIC", "JULIE OTSUKA"),
                Book("THE TIGER’S WIFE", "TÉA OBREHT"),
                Book("SALVAGE THE BONES", "JESMYN WARD"),
                Book(" THE FLAMETHROWERS", "RACHEL KUSHNER"),
                Book("ALL MY PUNY SORROWS", "MIRIAM TOEWS"),
                Book("DEPT. OF SPECULATION", "JENNY OFFILL"),
                Book("THE SELLOUT", "PAUL BEATTY"),
                Book("THE SYMPATHIZER", "VIET THANH NGUYEN"),
            )
        )

        setTitle("AudioBB")

        customViewModel = ViewModelProvider(this).get(CustomViewModel::class.java)

        isDouble = findViewById<FragmentContainerView>(R.id.fragment2) != null

        if (savedInstanceState == null) {
            customViewModel.setBook(_CurrentBook)
            if (isDouble) {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment,
                        BookListFragment.newInstance(_CurrentBook.title, _CurrentBook.author)
                    )
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .add(
                        R.id.fragment,
                        BookListFragment.newInstance(_CurrentBook.title, _CurrentBook.author)
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }

        if (isDouble) {
            if (supportFragmentManager.findFragmentById(R.id.fragment) is BookDetailsFragment) {
                supportFragmentManager.popBackStack()
            }
            if (supportFragmentManager.findFragmentById(R.id.fragment2) == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragment2, BookDetailsFragment.newInstance("", ""))
                    .commit()
            }
        } else if (customViewModel.getBook().value != _CurrentBook) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment,
                    BookDetailsFragment.newInstance(_CurrentBook.title, _CurrentBook.author)
                )
                .addToBackStack(null)
                .commit()
        }
    }


}