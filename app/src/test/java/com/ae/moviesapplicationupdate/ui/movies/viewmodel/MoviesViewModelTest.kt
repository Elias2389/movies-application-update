package com.ae.moviesapplicationupdate.ui.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ae.moviesapplicationupdate.common.dto.MoviesResponse
import com.ae.moviesapplicationupdate.common.dto.Resource
import com.ae.moviesapplicationupdate.common.dto.ResultsItem
import com.ae.moviesapplicationupdate.common.dto.Status
import com.ae.moviesapplicationupdate.data.services.MoviesServices
import com.ae.moviesapplicationupdate.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

import org.junit.Assert.*
import org.junit.rules.TestRule
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MoviesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesServices: MoviesServices

    @Mock
    private lateinit var observer: Observer<Resource<MoviesResponse>>

    @ExperimentalCoroutinesApi
    val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MoviesViewModel
    private var results = mutableListOf<ResultsItem>()
    lateinit var moviesResponse: MoviesResponse
    lateinit var response: Response<MoviesResponse>

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)

        results.add(ResultsItem(1, "Overview", "", "", true,
            "","","","", 1.3, 1.2,
            true, 1))
        moviesResponse = MoviesResponse(0, 0, results)
        response = Response.success(moviesResponse)

        viewModel = MoviesViewModel(moviesServices)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetPopularMovies() {
        testCoroutineDispatcher.runBlockingTest {
            given(moviesServices.getPopularMovies()).willReturn(moviesResponse)
            viewModel.popularMovies.observeForever(observer)

            viewModel.popularMovies.getOrAwaitValue()

            Mockito.verify(observer).onChanged(Resource.loading(data = null))
            Mockito.verify(observer).onChanged(Resource.success(moviesResponse))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGetLatest() {
        testCoroutineDispatcher.runBlockingTest {
            given(moviesServices.getLatestMovie()).willReturn(moviesResponse)
            viewModel.latest.observeForever(observer)

            viewModel.latest.getOrAwaitValue()

            Mockito.verify(observer).onChanged(Resource.loading(data = null))
            Mockito.verify(observer).onChanged(Resource.success(moviesResponse))
        }
    }

    @ExperimentalCoroutinesApi
    @Test(expected = Exception::class)
    fun shouldTrowErrorPopularMovies() {
        testCoroutineDispatcher.runBlockingTest {
            given(moviesServices.getPopularMovies()).willThrow(Exception("Error"))
            viewModel.popularMovies.observeForever(observer)

            viewModel.popularMovies.getOrAwaitValue()

            Mockito.verify(observer).onChanged(Resource.loading(data = null))
            Mockito.verify(observer).onChanged(Resource.error(data = null, message = "Error Occurred!"))
            assertSame(Exception::class, viewModel.popularMovies.getOrAwaitValue())
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}