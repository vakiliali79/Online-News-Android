package com.alivakili.news.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alivakili.news.api.NewsAnswerDTO
import com.alivakili.news.api.RetrofitClient
import com.alivakili.news.common.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListViewModel(private val categoryName: String) : ViewModel() {
    private var _state = MutableStateFlow<NewsListState>(NewsListState.Loading)
    var state: StateFlow<NewsListState> = _state

    init {
        retrieveNews()
    }

    private fun retrieveNews() {
            val call = RetrofitClient.ApiClient.apiService.topHeadlines(apikey = "d483eb9e8c304874bfb0ed82424da303", language = "en", country = "us", category = categoryName)


        if (call != null) {
            call.enqueue(object : Callback<NewsAnswerDTO> {
                override fun onResponse(call: Call<NewsAnswerDTO>, response: Response<NewsAnswerDTO>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val articles=response.body()!!.articles
                        val news=articles!!.map {dto->
                            News(
                                title =dto?.title?:"",
                                image = dto?.urlToImage?:"",
                                source = dto?.source?.name?:"",
                                date =dto?.publishedAt?.split("T")?.first()?:"",
                                description = dto?.description?:"",
                                url = dto?.url?:""
                            )
                        }
                        _state.value=NewsListState.Success(
                            news,
                            sources =sources(news)
                        )
                    } else {
                        _state.value=NewsListState.Failure
                        retrieveNews()
                    }
                }

                override fun onFailure(call: Call<NewsAnswerDTO>, t: Throwable) {
                    _state.value=NewsListState.Failure
                    retrieveNews()
                }
            })
        }

    }

    private fun sources(news:List<News>):List<String>{
        return news.map { it.source}.distinct().sortedBy{it.first()}
    }

    companion object {
        fun factory(categoryName: String?): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return categoryName?.let { NewsListViewModel(it) } as T
                }
            }
        }
    }


}