package com.itechart.news_app.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.map
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.data.model.ArticlesDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.given
import org.mockito.kotlin.times

@ExperimentalCoroutinesApi
class NewsRepositoryImplTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: NewsService

    @InjectMocks
    lateinit var newsRepository: NewsRepositoryImpl


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get articles success`() {
        runBlocking {
            val articleResponse: ArticlesDto = fixture()
           newsRepository.getNews("everything").first().map { article ->
                articleResponse.articles.forEach { i ->
                    assert(article == i.toArticle())
                }
            }
        }
    }

    @Test
    fun `get articles empty`() {
        runBlocking {
            val articleResponse = ArticlesDto("200", 0, emptyList())
            newsRepository.getNews("everything").first().map { article ->
                articleResponse.articles.forEach { i ->
                    assert(article == i.toArticle())
                }
            }
        }
    }

    @Test
    fun `get articles null`() = runTest {
        given(api.getNews("everything", BuildConfig.API_KEY)).willReturn(null)
        val result = api.getNews("everything", BuildConfig.API_KEY)
        assertEquals(null, result)
    }

    @Test
    fun `get articles called once`() = runTest {
        val articlesResponse = ArticlesDto(
            "200",
            100,
            fixture()
        )
        given(api.getNews("everything", BuildConfig.API_KEY)).willReturn(articlesResponse)
        api.getNews("everything", BuildConfig.API_KEY)
        verify(api, times(1)).getNews("everything", BuildConfig.API_KEY)
    }

}