package com.alivakili.news.selectCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alivakili.news.R
import com.alivakili.news.common.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel :ViewModel(){
    private val _state= MutableStateFlow<CategoryState>(CategoryState.Success(categories()))
    var state:StateFlow<CategoryState> =_state

    companion object{
        fun factory(): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return MainViewModel() as T
                }
            }
        }
    }

    fun categories(): List<Category> {
        return listOf(
            Category(name = R.string.general, image = R.drawable.general_image),
            Category(name = R.string.business, image = R.drawable.business_image),
            Category(name = R.string.entertainment, image = R.drawable.entertainment_image),
            Category(name = R.string.health, image = R.drawable.health_image),
            Category(name = R.string.sports, image = R.drawable.sports_image),
            Category(name = R.string.science, image = R.drawable.science_image),
            Category(name = R.string.technology, image = R.drawable.technology_image)
        )
    }
}