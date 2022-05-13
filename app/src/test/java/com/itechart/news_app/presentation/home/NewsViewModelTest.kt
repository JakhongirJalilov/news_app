package com.itechart.news_app.presentation.home

import androidx.paging.map
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.itechart.news_app.MainCoroutineRule
import com.itechart.news_app.data.model.ArticlesDto
import com.itechart.news_app.domain.use_case.NewsUseCase
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Mock
    lateinit var newsUseCase: NewsUseCase

    @InjectMocks
    lateinit var newsViewModel: NewsViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun `get articles success`() {
        runTest {
            val articleResponse : ArticlesDto = fixture()
            newsViewModel.getNews("everything")
            newsViewModel.news.map {
                if (it is ResultWrapper.Success){
                    it.data.let { pagingData ->
                        pagingData?.map { article ->
                            articleResponse.articles.forEach { articleDto ->
                                assert(article == articleDto.toArticle())
                            }
                        }
                    }
                }
            }
        }
    }
}