package com.udacity.shoestore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.shoestore.data.Shoe
import com.udacity.shoestore.databinding.ItemShoeBinding

class ShoeAdapter : ListAdapter<Shoe, ShoeAdapter.ViewHolder>(ViewHolder.ShoeDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    /*Holds views to be bound to recycler view*/
    class ViewHolder private constructor(val binding: ItemShoeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Shoe) {
            binding.shoe = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemShoeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        class ShoeDiffCallback : DiffUtil.ItemCallback<Shoe>() {
            override fun areItemsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem.shoeId == newItem.shoeId
            }

            override fun areContentsTheSame(oldItem: Shoe, newItem: Shoe): Boolean {
                return oldItem == newItem
            }
        }
    }


}