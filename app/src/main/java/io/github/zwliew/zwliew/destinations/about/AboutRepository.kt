package io.github.zwliew.zwliew.destinations.about

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.Failed
import io.github.zwliew.zwliew.Loaded
import io.github.zwliew.zwliew.Loading
import io.github.zwliew.zwliew.util.retrofit
import java.net.UnknownHostException

object AboutRepository : BaseRepository<AboutData> {
    private val service = retrofit.create(AboutService::class.java)

    override val data = MutableLiveData<AboutData>()

    override suspend fun load() {
        data.value = data.value?.copy(status = Loading) ?: AboutData(Loading)

        try {
            val response = service.getAboutAsync().await()
            response.let {
                data.value = AboutData(
                    Loaded,
                    it.education,
                    it.activities,
                    it.achievements
                )
            }
        } catch (e: UnknownHostException) {
            // Handle the lack of network connection
            data.value = data.value?.copy(status = Failed) ?: AboutData(Failed)
        }
    }
}