package com.linetest.dialogmanycallback

interface ItemClickListener<T> {
    fun onClick(position:Int, item: T)
}