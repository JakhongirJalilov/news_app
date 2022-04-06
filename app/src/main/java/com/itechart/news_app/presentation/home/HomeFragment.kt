package com.itechart.news_app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.itechart.news_app.databinding.FragmentHomeBinding
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.Dispatchers
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
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterNews: NewsAdapter
    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initData()
        initSearch()
    }

    private fun initData() {
        newsViewModel.getNews("everything")
        lifecycleScope.launch {
            withContext(Dispatchers.Main) {
                newsViewModel.news.collectLatest {
                    when (it) {
                        is ResultWrapper.Success -> {
                            binding.progressCircular.visibility = View.GONE
                            adapterNews.submitList(it.data)
                        }
                        is ResultWrapper.Error -> {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is ResultWrapper.Loading -> {
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                    }

                }
            }
        }
    }

    private fun initRecycler() {
        adapterNews = NewsAdapter()
        binding.listNews.apply {
            adapter = adapterNews
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initSearch() {
        binding.txSearch.addTextChangedListener {
            if (it.toString().length >= 3) {
                newsViewModel.getNews(it.toString())
            }
        }
    }
}