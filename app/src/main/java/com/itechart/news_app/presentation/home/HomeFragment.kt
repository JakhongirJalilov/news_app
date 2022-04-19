package com.itechart.news_app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.itechart.news_app.databinding.FragmentHomeBinding
import com.itechart.news_app.presentation.home.adapters.NewsAdapter
import com.itechart.news_app.presentation.home.adapters.NewsLoadStateAdapter
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var adapterNews: NewsAdapter
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initData()
        initSearch()
    }

    private fun initData() {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                newsViewModel.news.collectLatest { pagingData ->
                    when (pagingData) {
                        is ResultWrapper.Success -> {
                            binding.progressCircular.visibility = View.GONE
                            pagingData.data?.let { it1 -> adapterNews.submitData(it1) }
                        }
                        is ResultWrapper.Error -> {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                "${pagingData.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        is ResultWrapper.Loading -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }
        newsViewModel.getNews("everything")
    }

    private fun initRecycler() {
        adapterNews = NewsAdapter()
        binding.listNews.apply {
            adapter = adapterNews.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { adapterNews.retry() },
                footer = NewsLoadStateAdapter { adapterNews.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.retryButton.setOnClickListener { adapterNews.retry() }
        lifecycleScope.launch {
            adapterNews.loadStateFlow.collect { loadState ->
                val isListEmpty =
                    loadState.refresh is LoadState.NotLoading && adapterNews.itemCount == 0
                // show empty list
                binding.emptyView.isVisible = isListEmpty
                // Only show the list if refresh succeeds.
                binding.listNews.isVisible = !isListEmpty

                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error

                errorState?.let {
                    Toast.makeText(
                        requireContext(),
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun initSearch() {
        binding.txSearch.addTextChangedListener { txSearch ->
            if (txSearch.toString().length >= 3) {
                binding.progressCircular.visibility = View.VISIBLE
                newsViewModel.getNews(txSearch.toString())
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}