package com.soma.lof.common.data.entity

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CommonItemResponse(
    val viewType: String,
    val viewObject: CommonViewObject,
)