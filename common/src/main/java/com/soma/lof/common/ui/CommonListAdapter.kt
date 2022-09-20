package com.soma.lof.common.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.common.data.entity.CommonItem
import com.soma.lof.common.data.entity.ViewType

class CommonListAdapter(
    private val dataSet: List<CommonItem>
) : RecyclerView.Adapter<CommonVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        /* 새로운 뷰타입이 생길 때마다 분기를 추가 */
        return CommonVHFactory.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    /* CommonViewType에서 해당 data의 viewType의 ordinal(인덱스)를 반환  */
    override fun getItemViewType(position: Int): Int {
        return ViewType.valueOf(dataSet[position].viewType).ordinal
    }
}