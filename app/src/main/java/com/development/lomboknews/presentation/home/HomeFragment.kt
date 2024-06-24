package com.development.lomboknews.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.development.lomboknews.data.local.entity.NewsEntity
import com.development.lomboknews.databinding.FragmentHomeBinding
import com.development.lomboknews.presentation.adapter.NewsAdapter
import com.development.lomboknews.presentation.add.AddViewModel
import com.development.lomboknews.utils.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[HomeViewModel::class.java]

        viewModel.getAllNews().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding?.apply {
                    rvNews.visibility = View.GONE
                    ivEmpty.visibility = View.VISIBLE
                }
            } else {
                binding?.ivEmpty?.visibility = View.GONE
                setupRecycle(it)
            }
        }
    }

    private fun setupRecycle(news: List<NewsEntity>) {
        newsAdapter = NewsAdapter()

        binding?.rvNews?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
        newsAdapter.submitList(news)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}