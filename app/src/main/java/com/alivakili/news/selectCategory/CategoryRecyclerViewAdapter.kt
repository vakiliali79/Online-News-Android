package com.alivakili.news.selectCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alivakili.news.common.Category
import com.alivakili.news.databinding.LayoutCellCategoryBinding

class CategoryRecyclerViewAdapter (
    private val items:List<Category>,
    private val onClicked:(Int)->Unit,
    ):RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            CategoryViewHolder {
        return CategoryViewHolder.create(parent, onClicked)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category=items[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CategoryViewHolder(
        private  val binding: LayoutCellCategoryBinding,
        private val onClicked:(Int)->Unit
    ):RecyclerView.ViewHolder(binding.root)  {

        fun bind(category: Category){
            binding.categoryName.setText( category.name)
            binding.cartView.setBackgroundResource(category.image)
            binding.cartView.setOnClickListener {
                onClicked(category.name)
            }

        }
        companion object{
            fun create(parent:ViewGroup,onClicked:(Int)->Unit): CategoryViewHolder {
                val binding=LayoutCellCategoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
                return CategoryViewHolder(binding=binding, onClicked = onClicked)
            }
        }
    }


}