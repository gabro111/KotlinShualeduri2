package com.example.shualedurikotlinn2.adapter

import android.content.Intent
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.shualedurikotlinn2.R
import com.example.shualedurikotlinn2.SecondActivity
import com.example.shualedurikotlinn2.api.CommentApi
import com.example.shualedurikotlinn2.room.Post
import kotlin.coroutines.coroutineContext

class RecycleViewAdapter(private var list:List<Post>): RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>() {


    fun setUpdatedData(list:List<Post>){
        this.list = list
        notifyDataSetChanged()
    }
    class RecycleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val title = itemView.findViewById<TextView>(R.id.titlePost)
        private val body = itemView.findViewById<TextView>(R.id.bodyPost)
        val card = itemView.findViewById<CardView>(R.id.cardView)
        fun bindPost(post:Post){
            title.text = post.title
            body.text = post.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        return RecycleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {

        holder.card.setOnClickListener {
            val intent = Intent(holder.card.context, SecondActivity::class.java)
            intent.putExtra("postId",list.elementAt(position).postId)
            holder.card.context.startActivity(intent)
        }

        holder.bindPost(list.elementAt(position))
    }

    override fun getItemCount() = list.size
}