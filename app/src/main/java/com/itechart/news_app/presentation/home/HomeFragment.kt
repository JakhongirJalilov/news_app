package com.itechart.news_app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.itechart.news_app.databinding.FragmentHomeBinding
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
        initProgress()
    }

    private fun initProgress() {
        newsViewModel.isLoading.observe(viewLifecycleOwner) {
            if (!it) {
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    private fun initData() {
        newsViewModel.getNews()
        newsViewModel.news.observe(viewLifecycleOwner) {
            it.onSuccess { news ->
                adapterNews.getNews(news)
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
}