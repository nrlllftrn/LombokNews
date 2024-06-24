package com.development.lomboknews.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.databinding.FragmentSearchBinding
import com.development.lomboknews.presentation.adapter.NewsAdapter
import com.development.lomboknews.utils.ViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel =
            ViewModelProvider.create(requireActivity(), viewModelFactory)[SearchViewModel::class]

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotBlank()) {
                    viewModel.searchNews(newText).observe(viewLifecycleOwner) { news ->
                        setupRecycle(news)
                    }
                }
                return true
            }
        })
    }

    private fun setupRecycle(news: List<NewsEntity>) {
        if (news.isEmpty()) {
            binding.apply {
                rvNews.visibility = View.GONE
                ivEmpty.visibility = View.VISIBLE
            }
        } else {
            binding.ivEmpty.visibility = View.GONE
            newsAdapter = NewsAdapter()
            binding.rvNews.apply {
                setHasFixedSize(true)
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(requireActivity())
            }
            newsAdapter.submitList(news)
        }
    }
}