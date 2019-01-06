package io.github.zwliew.zwliew.destinations.about

import androidx.lifecycle.MutableLiveData
import io.github.zwliew.zwliew.BaseRepository
import io.github.zwliew.zwliew.util.retrofit

object AboutRepository : BaseRepository<AboutData> {
    private val service = retrofit.create(AboutService::class.java)

    override val data = MutableLiveData<AboutData>()

    override suspend fun load() {
        val response = service.getAboutAsync().await()
        response.let {
            data.value = AboutData(
                it.education,
                it.activities,
                it.achievements
            )
        }
    }
}