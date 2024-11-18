package io.github.mitesh.brba.core.testing

import androidx.lifecycle.ViewModel
import io.github.mitesh.brba.core.testing.rule.MainDispatcherRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class ViewModelTest<T : ViewModel> {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    val testDispatcher by lazy { mainDispatcherRule.testDispatcher }

    protected lateinit var viewModel: T

    abstract fun setDefaultMockData()

    abstract fun makeViewModel(): T

    @Before
    open fun onBefore() {
        setDefaultMockData()
        viewModel = makeViewModel()
    }

    @After
    open fun onAfter() {
    }
}