package com.alivakili.news.newsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alivakili.news.NewsDetails.NewsDetailsActivity
import com.alivakili.news.R
import com.alivakili.news.common.News
import com.alivakili.news.databinding.ActivityNewsListBinding
import com.alivakili.news.databinding.LayoutChipBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class NewsListActivity : AppCompatActivity() {
     private lateinit var binding:ActivityNewsListBinding
     private var categoryName:String?=""
     private val viewModel: NewsListViewModel by viewModels{
         NewsListViewModel.factory(categoryName)
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryName=getString(intent.getIntExtra("CATEGORY_NAME",0))

        configureToolbar(title=categoryName!!)
        observeState()
    }




    private fun configureToolbar(title:String){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            this.title=title
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
    }

    private fun observeState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.state.collect{
                    state->
                    when(state){
                        NewsListState.Loading -> showProgressBar()
                        is NewsListState.Success -> {
                            showNews(state.news)
                            showSources(state.sources)
                        }

                        NewsListState.Failure -> showErrorMessage()
                    }
                }
            }
        }
    }

    private fun showSources(sources: List<String>){
        for (source in sources){

            binding.chipGroup.addView(createChip(source))
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun createChip(label:String): Chip {
        val chip = LayoutChipBinding.inflate(layoutInflater).root
        chip.text=label
        chip.setOnClickListener{
            Toast.makeText(this,label, Toast.LENGTH_LONG).show()
        }
        return chip
    }

    private fun showNews(news:List<News>){
        hideProgressBar()

        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@NewsListActivity,LinearLayout.VERTICAL))
            adapter= NewsRecyclerViewAdapter(items = news, onClicked =::showNewsDetails,this@NewsListActivity )
            layoutManager=LinearLayoutManager(this@NewsListActivity)
            setHasFixedSize(true)
            visibility=View.VISIBLE
        }
    }
    private fun showErrorMessage(){
        Toast.makeText(this,"Unable to retrieve news!",Toast.LENGTH_LONG).show()
    }
    private fun showProgressBar(){
        binding.progressBar.visibility= View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility= View.GONE
    }
    private fun showNewsDetails(news: News){
        val intent=NewsDetailsActivity.intent(this, news)
        startActivity(intent)
    }


}