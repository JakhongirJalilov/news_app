package com.itechart.news_app.data.repository

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

/**
TODO:
 * How to mock API service?
 * How to check methods with mock response?
 * How to mock Repository and call api service with error result?
 * How to test and check LiveData?
 */

@ExperimentalCoroutinesApi
class NewsRepositoryImplTest {

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

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `get articles success`() = runTest {
        given(api.getNews("everything", BuildConfig.API_KEY)).willReturn(articlesResponse)

        val result = api.getNews("everything", BuildConfig.API_KEY)

//        verify(api.getNews("everything", BuildConfig.API_KEY))
        assertEquals(articlesResponse, result)
    }

    @Test
    fun `get articles null`() = runTest {
        given(api.getNews("everything", BuildConfig.API_KEY)).willReturn(null)

        val result = api.getNews("everything", BuildConfig.API_KEY)

//        verify(api.getNews("everything", BuildConfig.API_KEY))
        assertEquals(null, result)
    }

}