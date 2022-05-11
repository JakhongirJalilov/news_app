//package com.itechart.news_app
//
//import com.itechart.news_app.uitils.DispatcherProvider
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.TestCoroutineDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.setMain
//import org.junit.rules.TestWatcher
//import org.junit.runner.Description
//
//@ExperimentalCoroutinesApi
//class MainCoroutineRule(
//    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher() {
//
//    val testDispatcherProvider = object : DispatcherProvider {
//        override val main: CoroutineDispatcher
//            get() = testDispatcher
//        override val io: CoroutineDispatcher
//            get() = testDispatcher
//        override val default: CoroutineDispatcher
//            get() = testDispatcher
//    }
//
//    override fun starting(description: Description?) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: Description?) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
//}
//
////fun <T> wheneverBlocking(methodCall: suspend () -> T): OngoingStubbing<T> {
////    return runBlocking { Mockito.`when`(methodCall()) }
////}
////
////fun <T> observe(liveData: LiveData<T>) = mock<Observer<T>>().apply {
////    liveData.observeForever(this)
////}