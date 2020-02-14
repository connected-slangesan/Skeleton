package com.rocky.skeleton.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rocky.skeleton.R
import com.rocky.skeleton.databinding.ItemViewBinding
import com.rocky.skeleton.home.model.Character
import com.rocky.skeleton.home.viewmodel.HomeViewModel

class CharacterAdapter(
    val items: List<Character>,
    val context: Context?,
    val model: HomeViewModel
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemViewBinding>(
            LayoutInflater.from(context),
            R.layout.item_view,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position), model)
    }

    class ViewHolder(
        val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Character, viewModel: HomeViewModel) {
            binding.character = data
            binding.model = viewModel
        }
    }
}