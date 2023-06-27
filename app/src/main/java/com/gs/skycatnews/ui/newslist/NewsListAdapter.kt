package com.gs.skycatnews.ui.newslist

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gs.skycatnews.base.util.layoutInflater
import com.gs.skycatnews.databinding.FeedItemBinding
import com.gs.skycatnews.domain.models.Feed
import com.gs.skycatnews.utils.clearDecorations
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class NewsListAdapter(val viewModel: NewsListViewModel) :
    ListAdapter<Feed, FollowersListViewHolder>(
        UserDiffCallback()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersListViewHolder {
        return FollowersListViewHolder(
            FeedItemBinding.inflate(parent.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FollowersListViewHolder, position: Int) {
        holder.bind(viewModel, getItem(position))
    }
}


@ExperimentalCoroutinesApi
class FollowersListViewHolder(val binding: FeedItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: NewsListViewModel, data: Feed) {
        binding.viewModel = viewModel
        binding.item = data
        binding.executePendingBindings()
    }
}

private class UserDiffCallback : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed) =
        oldItem == newItem
}

@ExperimentalCoroutinesApi
@BindingAdapter(value = ["bindNewsListViewModel", "bindDataList"], requireAll = true)
fun RecyclerView.bindNewsFeedListAdapter(
    viewModel: NewsListViewModel,
    data: List<Feed>?
) {
    if (adapter == null) adapter = NewsListAdapter(viewModel)
    val value = data ?: emptyList()
    val feedListAdapter = adapter as? NewsListAdapter
    feedListAdapter?.submitList(value)
    clearDecorations()
}
