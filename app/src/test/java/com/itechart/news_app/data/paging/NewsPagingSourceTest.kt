package com.itechart.news_app.data.paging

import androidx.paging.PagingSource
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.data.model.ArticlesDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.given

@ExperimentalCoroutinesApi
class NewsPagingSourceTest {
    companion object {
        private val fixture = kotlinFixture {
            nullabilityStrategy(NeverNullStrategy)
        }
        val articlesResponse = ArticlesDto(
            "200",
            100,
            fixture()
        )
    }

    @Mock
    lateinit var api: NewsService
    lateinit var articlesPagingSource: NewsPagingSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        articlesPagingSource = NewsPagingSource(api, "string")
    }

    @Test
    fun `get articles paging source refresh fail`() = runTest {
                given(api.getNews("q", BuildConfig.API_KEY)).willReturn(articlesResponse)
        val result : Boolean = PagingSource.LoadResult.Page(
            data = articlesResponse.articles.map { it.toArticle() },
            prevKey = null,
            nextKey = 1
        ) == articlesPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        assertEquals(false, result)
    }
}