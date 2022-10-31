package com.soma.lof.core.model.entity

data class PostItem (val postId: Long, val userProfileImg: String, val userNickName: String, val postDate: String, val postPhotoList: List<PhotoItem>, val content: String, val commentCnt: String, val favoriteCnt: String, var isBookMarked: Boolean = false)