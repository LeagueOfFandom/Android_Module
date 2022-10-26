package com.soma.lof.domain.util

import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.dto.CommonItemResponse
import com.soma.lof.core.model.entity.ViewType
import com.soma.lof.core.model.util.ViewObjectFactory

/**
 * This Translator translates data to List<CommonItem>
 */
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