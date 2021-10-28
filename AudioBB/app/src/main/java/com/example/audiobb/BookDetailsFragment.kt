package com.example.audiobb

import Book
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookDetailsFragment : Fragment() {
    lateinit var layout: View
    lateinit var title: TextView
    lateinit var author: TextView

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = layout.findViewById(R.id.title)
        author = layout.findViewById(R.id.author)
        ViewModelProvider(requireActivity())
            .get(CustomViewModel::class.java)
            .getBook()
            .observe(requireActivity()) {
                bookDetails(it)
            }
    }

    private fun bookDetails(_book: Book) {
        title.text = _book.title
        author.text = _book.author
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}