package com.soma.lof.community

import androidx.lifecycle.ViewModel
import com.soma.lof.core.model.entity.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(

): ViewModel() {

    val dummy = listOf(
        PostItem(
            0L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        ),
        PostItem(
            2L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        ),
        PostItem(
            4L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        ),
        PostItem(
            5L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        ),
        PostItem(
            6L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        ),
        PostItem(
            7L,
            "",
            "hello",
            "2022년 9월 20일",
            "내용이 들어갈 자리입니다.",
            "99+",
            "99+",
        )
    )


}
