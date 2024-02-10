package com.alivakili.news.common


import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category (
    @StringRes val name:Int,
    @DrawableRes var image:Int

):Parcelable {
    constructor(dto: Category):this(
        name =dto.name?:0,
        image =dto.image?:0,
        )
}