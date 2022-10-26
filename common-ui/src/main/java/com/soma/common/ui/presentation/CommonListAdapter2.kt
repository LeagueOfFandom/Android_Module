package com.soma.common.ui.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.soma.common.ui.util.CommonVHFactory
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.ViewType

class CommonListAdapter2 : ListAdapter<CommonItem, CommonVH>(CommonItemDiffUtilCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        /* 새로운 뷰타입이 생길 때마다 분기를 추가 */
        return CommonVHFactory.createViewHolder(parent, viewType)
    }

    /* CommonViewType에서 해당 data의 viewType의 ordinal(인덱스)를 반환  */
    override fun getItemViewType(position: Int): Int {
        return ViewType.valueOf(getItem(position).viewType).ordinal
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        holder.bind(getItem(position))
    }
}