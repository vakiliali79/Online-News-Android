package com.alivakili.news.common

import android.os.Parcelable
import com.alivakili.news.api.Article
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    val title:String,
    val date: String,
    val source:String,
    val image: String,
    val description:String,
    val url:String

    ):Parcelable{
   constructor(dto:Article):this(
       title =dto.title?:"",
       date = dto.publishedAt?.split("T")?.first()?:"",
       source =dto.source?.name?:"",
       image =dto.urlToImage?:"",
       description=dto.description?:"",
       url=dto.url?:""

   )
}
