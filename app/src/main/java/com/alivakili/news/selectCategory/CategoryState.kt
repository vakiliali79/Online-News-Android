package com.alivakili.news.selectCategory

import com.alivakili.news.common.Category

sealed class CategoryState {

    data class Success(val categories:List<Category>):CategoryState()
}