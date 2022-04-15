package com.itechart.news_app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.domain.model.Article

class NewsPagingSource(
    private val newsService: NewsService,
    private val search: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            // Start refresh at page 1 if undefined.
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = newsService.getNews(search, BuildConfig.API_KEY, pageSize, page)

            val articles = checkNotNull(response.articles.map { it.toArticle() })
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1, // Only paging forward.
                nextKey = if (articles.size < pageSize) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
        }
    }
}