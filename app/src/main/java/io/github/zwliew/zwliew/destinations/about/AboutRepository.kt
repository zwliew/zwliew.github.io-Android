package io.github.zwliew.zwliew.destinations.about

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object AboutRepository : BaseRepository<AboutState> {
    private val service = retrofit.create(AboutService::class.java)

    override val state = MutableLiveData<AboutState>()

    override suspend fun load() {
        state.value = state.value?.copy(status = Loading) ?: AboutState(Loading)

        try {
            val response = service.getAboutAsync().await()
            response.let {
                state.value = AboutState(
                    Loaded,
                    AboutData(it.education, it.activities, it.achievements)
                )
            }
        } catch (e: UnknownHostException) {
            // Handle the lack of network connection
            state.value = state.value?.copy(status = Failed) ?: AboutState(Failed)
        }
    }
}