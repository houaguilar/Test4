package com.example.testfour.ui.stories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testfour.R
import com.example.testfour.data.model.Story
import com.example.testfour.databinding.FragmentStoriesBinding
import kotlinx.android.synthetic.main.fragment_stories.*
import org.koin.android.viewmodel.ext.android.viewModel

class StoriesFragment : Fragment(R.layout.fragment_stories), StoriesView, StoriesAdapter.OnStorySwipeListener, StoriesAdapter.OnStoryClickListener  {

    private val presenter: StoriesPresenter by viewModel<StoryViewModel>()
    private val adapter by lazy { StoriesAdapter(this, this) }
    private var storyList: Story? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentStoriesBinding.bind(view)

        binding.rvTest.adapter = adapter

        binding.rvTest.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTest.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        binding.pullToRefresh.setOnRefreshListener { refreshList() }
        presenter.setView(this)

        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                // do nothing
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.deleteStoryList(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(binding.rvTest)

    }

    private fun refreshList(){
        val data = storyList

        if (data == null){
            pullToRefresh.isRefreshing = false
            return
        }

        val refreshedList = presenter.getStoryListById(data.id) as Story
        storyList = refreshedList
        pullToRefresh.isRefreshing = false
    }

    override fun onStoryClick(story: Story, position: Int) {
        findNavController().navigate(
            StoriesFragmentDirections.actionStoriesFragmentToDetailFragment(
                story
            )
        )
    }

    override fun showStories(stories: List<Story>) {
        adapter.setData(stories)
        refreshList()
    }

    override fun showError(throwable: Throwable) {
        pullToRefresh.isRefreshing = false
    }

    override fun onStart() {
        super.onStart()
        presenter.getData()
    }

    override fun onStorySwipe(story: Story, position: Int) {
        presenter.deleteStory(story)
        presenter.updateStoryList(story)
        refreshList()
    }


}