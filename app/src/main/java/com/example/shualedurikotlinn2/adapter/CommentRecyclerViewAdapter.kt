package com.example.shualedurikotlinn2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shualedurikotlinn2.R
import com.example.shualedurikotlinn2.api.CommentApi

class CommentRecyclerViewAdapter(private val list:List<CommentApi>?):RecyclerView.Adapter<CommentRecyclerViewAdapter.CommentRecyclerViewHolder> (){

    class CommentRecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        private val commentBody:TextView = itemView.findViewById(R.id.commentView)
        private val userid:TextView = itemView.findViewById(R.id.userView)

        fun bindComment(comment : CommentApi){

            commentBody.text = comment.body
            userid.text = comment.email
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentRecyclerViewHolder {
        return CommentRecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.commentrecyclerview_item,parent,false))
    }

    override fun onBindViewHolder(holder: CommentRecyclerViewHolder, position: Int) {

        if (list != null) {
            holder.bindComment(list.elementAt(position))
        }
    }

    override fun getItemCount() = list!!.size
}