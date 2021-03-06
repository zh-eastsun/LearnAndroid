package com.zdy.application.learnandroid.bean

data class User(
    val `data`: UserData,
    val errorCode: Int,
    val errorMsg: String
)

data class UserData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)