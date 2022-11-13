package com.soma.lof.match.ui.match_info

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soma.common.ui.presentation.CommonMatchInfoVH
import com.soma.common.ui.util.MatchInfoVHFactory
import com.soma.lof.core.model.dto.CommonItem
import com.soma.lof.core.model.entity.MatchPreviewCommonVO
import com.soma.lof.core.model.entity.ViewType

class MatchInfoPreviewAdapter : ListAdapter<MatchPreviewCommonVO, CommonMatchInfoVH>(MatchPreviewCommonVODiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonMatchInfoVH {
        /* 새로운 뷰타입이 생길 때마다 분기를 추가 */
        return MatchInfoVHFactory.createViewHolder(parent, viewType)
    }

    /* CommonViewType에서 해당 data의 viewType의 ordinal(인덱스)를 반환  */
    override fun getItemViewType(position: Int): Int {
        return ViewType.valueOf(getItem(position).viewType).ordinal
    }

    override fun onBindViewHolder(holder: CommonMatchInfoVH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        class MatchPreviewCommonVODiffUtilCallback : DiffUtil.ItemCallback<MatchPreviewCommonVO>() {
            override fun areItemsTheSame(oldItem: MatchPreviewCommonVO, newItem: MatchPreviewCommonVO): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MatchPreviewCommonVO, newItem: MatchPreviewCommonVO): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

        }
    }


}
