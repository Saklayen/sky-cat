package com.gs.skycatnews.ui.newsdetails

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gs.skycatnews.base.util.layoutInflater
import com.gs.skycatnews.databinding.StoryContentItemBinding
import com.gs.skycatnews.domain.models.Content
import com.gs.skycatnews.utils.clearDecorations
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class StoryContentAdapter(val viewModel: StoryDetailsViewModel) :
    ListAdapter<Content, StoryContentViewHolder>(
        ContentDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryContentViewHolder {
        return StoryContentViewHolder(
            StoryContentItemBinding.inflate(parent.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoryContentViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }
}


@ExperimentalCoroutinesApi
class StoryContentViewHolder(private val binding: StoryContentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: StoryDetailsViewModel, data: Content) {
        binding.viewModel = viewModel
        binding.item = data
        binding.executePendingBindings()
    }
}

private class ContentDiffCallback : DiffUtil.ItemCallback<Content>() {
    override fun areItemsTheSame(oldItem: Content, newItem: Content) =
        oldItem.text == newItem.text

    override fun areContentsTheSame(oldItem: Content, newItem: Content) =
        oldItem == newItem
}

@ExperimentalCoroutinesApi
@BindingAdapter(value = ["bindNewsDetailsViewModel", "bindStoryContent"], requireAll = true)
fun RecyclerView.bindStoryContentAdapter(
    viewModel: StoryDetailsViewModel,
    data: List<Content>?
) {
    if (adapter == null) adapter = StoryContentAdapter(viewModel)
    val value = data ?: emptyList()
    val storyContentAdapter = adapter as? StoryContentAdapter
    storyContentAdapter?.submitList(value)
    clearDecorations()
}
