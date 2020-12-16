package com.example.testfour.ui.stories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.testfour.R
import com.example.testfour.data.model.Story
import com.example.testfour.databinding.ItemRvStoryBinding
import kotlinx.android.synthetic.main.item_rv_story.view.*

class StoriesAdapter(
    private val itemClickListener: OnStoryClickListener,
    private val onStorySwipeListener: OnStorySwipeListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val items = mutableListOf<Story>()

    interface OnStoryClickListener {
        fun onStoryClick(story: Story, position: Int)
    }

    interface OnStorySwipeListener {
        fun onStorySwipe(story: Story, position: Int)
    }

    fun setData(newItems: List<Story>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun deleteStoryList(position: Int) {
        onStorySwipeListener.onStorySwipe(items[position], position)
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val itemBinding = ItemRvStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = StoryViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position =
                holder.adapterPosition.takeIf { it != NO_POSITION }?: return@setOnClickListener

            itemClickListener.onStoryClick(items[position], position)
        }


        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is StoryViewHolder -> holder.bind(items[position])
        }
    }

    inner class StoryViewHolder(private val binding: ItemRvStoryBinding) : BaseViewHolder<Story>(binding.root) {

        override fun bind(item: Story): Unit = with(binding) {

            txtTitle.text = item.titleOne
            txtAuthor.text = item.name
            txtDate.text = item.date

        }
    }


}


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}