package com.alivakili.news.selectCategory

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alivakili.news.common.Category
import com.alivakili.news.databinding.ActivityMainBinding
import com.alivakili.news.newsList.NewsListActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels{
        MainViewModel.factory()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeState()
    }

    private fun observeState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.state.collect{
                        state->
                    when(state){
                        is CategoryState.Success -> showCategories(state.categories)
                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun showCategories(categories:List<Category>) {
        binding.recyclerView.apply {
            adapter = CategoryRecyclerViewAdapter(items =categories , onClicked = ::showNews)
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }


    }

    private fun showNews(categoryName:Int){
        val intent=Intent(this, NewsListActivity::class.java)
        intent.putExtra("CATEGORY_NAME",categoryName)
        startActivity(intent)
    }
}