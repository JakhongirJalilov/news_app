package com.itechart.news_app.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.domain.model.Article
import retrofit2.HttpException

private const val FIRST_PAGE = 1

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
        if (search.isBlank()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        return try {
            // Start refresh at page 1 if undefined.
            val page = params.key ?: FIRST_PAGE
            val pageSize = params.loadSize.coerceAtMost(NewsService.MAX_PAGE_SIZE)
            val response = newsService.getNews(search, BuildConfig.API_KEY, pageSize, page)

            val articles = checkNotNull(response.articles.map { it.toArticle() })
            LoadResult.Page(
                data = articles,
                prevKey = if (page == FIRST_PAGE) null else page.dec(), // Only paging forward.
                nextKey = if (articles.size < pageSize) null else page.inc()
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}