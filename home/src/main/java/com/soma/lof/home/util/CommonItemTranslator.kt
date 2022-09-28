package com.soma.lof.home.util

import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.CommonItemResponse
import com.soma.lof.common.data.entity.OneLineTextViewObject
import com.soma.lof.common.data.entity.ViewType
import com.soma.lof.common.ui.CommonVHFactory
import com.soma.lof.common.ui.VHFactory
import com.soma.lof.common.util.ViewObjectFactory

object CommonItemTranslator {
    fun List<CommonItemResponse>.toCommonItemList(): List<CommonItem> {
        val commonItemList = mutableListOf<CommonItem>()
        this.forEach { item ->
            commonItemList.add(
                CommonItem(
                    item.viewType,
                    ViewObjectFactory.createViewObject(ViewType.valueOf(item.viewType).ordinal, item.viewObject)
                )
            )
        }
        return commonItemList.toList()
    }
}