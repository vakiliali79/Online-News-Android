package com.alivakili.news.newsList

import com.alivakili.news.common.News

sealed class NewsListState {
    object Loading: NewsListState()
    data class Success(val news:List<News>,val sources:List<String>): NewsListState()
    object Failure: NewsListState()
}