package com.soma.lof.home.util

import ViewObjectFactory
import com.soma.lof.core_model.dto.CommonItem
import com.soma.lof.core_model.dto.CommonItemResponse
import com.soma.lof.core_model.entity.ViewType

object CommonItemTranslator {
    fun List<CommonItemResponse>.toCommonItemList(): List<CommonItem> {
        val commonItemList = mutableListOf<CommonItem>()
        this.forEach { item ->
            commonItemList.add(
                CommonItem(
                    item.viewType,
                    ViewObjectFactory.createViewObject(ViewType.valueOf(item.viewType).ordinal,
                        item.viewObject)
                )
            )
        }
        return commonItemList.toList()
    }
}