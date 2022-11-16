package com.soma.lof.match.ui.match_info

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soma.lof.match.R
import com.soma.lof.match.databinding.ItemMatchSetBtnBinding

class MatchSetAdapter(private val context: Context, private val viewModel: MatchInfoViewModel) :
    RecyclerView.Adapter<MatchSetAdapter.ViewHolder>() {

    private val TAG = "MatchSetAdapter"

    inner class ViewHolder(private val binding: ItemMatchSetBtnBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.itemMatchSetText.text = "${position + 1}Set"

            if (position == viewModel.getCurrentSet()) {
                binding.itemMatchSetLayout.background =
                    ResourcesCompat.getDrawable(context.resources,
                        R.drawable.bg_match_set_btn_selected,
                        null)
                binding.itemMatchSetLayout.translationZ = 10f
            } else {
                binding.itemMatchSetLayout.background =
                    ResourcesCompat.getDrawable(context.resources,
                        R.drawable.bg_match_set_btn,
                        null)
                binding.itemMatchSetLayout.translationZ = 0f
            }

            binding.itemMatchSetLayout.setOnClickListener {
                viewModel.setMatchSetPos(position)
                notifyItemChanged(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<ItemMatchSetBtnBinding>(LayoutInflater.from(parent.context),
                R.layout.item_match_set_btn,
                parent,
                false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = viewModel.getSetSize()
}