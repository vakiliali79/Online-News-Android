package com.alivakili.news.newsList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alivakili.news.R
import com.alivakili.news.common.News
import com.alivakili.news.databinding.LayoutCellNewsBinding
import com.squareup.picasso.Picasso

class NewsRecyclerViewAdapter(
    private val items:List<News>,
    private val onClicked:(News)->Unit,
    private val context:Context
):RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NewsViewHolder {
        return NewsViewHolder.create(parent, onClicked)
    }



    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news=items[position]
        holder.bind(news,context)
    }



    override fun getItemCount(): Int {
        return items.size
    }

    class NewsViewHolder(
        private val binding: LayoutCellNewsBinding,
        private val onClicked: (News) -> Unit
    ): RecyclerView.ViewHolder(binding.root)  {

        companion object{
            fun create(parent:ViewGroup,onClicked:(News)->Unit): NewsRecyclerViewAdapter.NewsViewHolder {
                val binding= LayoutCellNewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
                return NewsRecyclerViewAdapter.NewsViewHolder(
                    binding = binding,
                    onClicked = onClicked
                )
            }
        }

        fun bind(news: News,context:Context){
            binding.source.text=news.source
            binding.date.text=news.date
            binding.title.text=news.title
            loadImage(news.image,context,binding.image)
            binding.root.setOnClickListener{
                onClicked(news)
            }
        }


        private fun loadImage(url: String?,context: Context,imageView:ImageView) {
            if (url != ""&&url!=null) {
                    Picasso.with(context).load(url).fit().centerCrop()
                        .placeholder(R.drawable.general_image)
                        .error(R.drawable.general_image)
                        .into(imageView)
            }
            else
                imageView.setImageResource(R.drawable.general_image)
        }


    }


}