package com.alivakili.news.NewsDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alivakili.news.R
import com.alivakili.news.common.News
import com.alivakili.news.databinding.ActivityNewsDetailsBinding
import com.squareup.picasso.Picasso

class NewsDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureToolbar()
        showDitails(news())

    }

    companion object{
        private const val KEY_NEWS="KEY_NEWS"

        fun intent(context:Context,news:News):Intent {
            val intent=Intent(context,NewsDetailsActivity::class.java)
            intent.putExtra(KEY_NEWS,news)
            return intent
        }
    }
    private fun showDitails(news:News){
        binding.date.text=news.date
        binding.source.text=news.source
        binding.decription.text=news.description
        binding.readStoryButton.setOnClickListener{openBrowser(news.url)}
        loadImage(news.image)

    }
    private fun openBrowser(url:String){
        val intent=Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
    private fun loadImage(url: String?) {
        Log.e("TAG", "loadImage: "+url, )
        if (url!=null&&url!="")
        Picasso.with(this@NewsDetailsActivity).load(url).fit().centerCrop()
            .placeholder(R.drawable.general_image)
            .error(R.drawable.general_image)
            .into(binding.image);
        else
            binding.image.setImageResource(R.drawable.general_image)
    }
    private fun news():News{
        return intent.getParcelableExtra<News>(KEY_NEWS)!!
    }
    private fun configureToolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            this.title=""
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}