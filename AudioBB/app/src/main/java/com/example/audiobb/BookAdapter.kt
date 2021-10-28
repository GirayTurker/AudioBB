package com.example.audiobb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(_context: Context, var _bookList:BookList, val clickListener : (bookInt: Int) -> Unit) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val inflater = LayoutInflater.from(_context)

    class BookViewHolder(itemView: View, val clickListener : (pos:Int) -> Unit) :  RecyclerView.ViewHolder(itemView){
        val bookTitleView : TextView = itemView.findViewById(R.id.title)
        val bookAuthorView : TextView = itemView.findViewById(R.id.author)

        init {
            itemView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.BookViewHolder {
        var view = inflater.inflate(R.layout.book_item, null)
        return BookViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: BookAdapter.BookViewHolder, position: Int) {
        holder.bookTitleView.text = _bookList.get(position).title
        holder.bookAuthorView.text = _bookList.get(position).author
    }

    override fun getItemCount(): Int {
        return _bookList.size()
    }
}