package com.itechart.news_app.domain.use_case

import androidx.paging.map
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.itechart.news_app.MainCoroutineRule
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.data.model.ArticlesDto
import com.itechart.news_app.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NewsUseCaseImplTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var newsService: NewsService

    @InjectMocks
    lateinit var newsRepositoryImpl: NewsRepositoryImpl

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var newsUseCaseImpl: NewsUseCaseImpl

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        newsUseCaseImpl = NewsUseCaseImpl(newsRepositoryImpl, testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `get articles successfully`(){
        runTest {
            val articlesResponse : ArticlesDto = fixture()
            newsUseCaseImpl.getNews("everything").first().map { article ->
                articlesResponse.articles.forEach { articleDto ->
                    assert(articleDto.toArticle() == article)
                }
            }
        }
    }
}