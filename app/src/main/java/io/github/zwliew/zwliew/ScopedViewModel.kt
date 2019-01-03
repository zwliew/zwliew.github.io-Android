package io.github.zwliew.zwliew

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel

open class ScopedViewModel : ViewModel() {
    protected val scope = CoroutineScope(Dispatchers.Main)

    @UseExperimental(ExperimentalCoroutinesApi::class)
    override fun onCleared() {
        super.onCleared()

        scope.cancel()
    }
}