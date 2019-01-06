package io.github.zwliew.zwliew.destinations.about

import io.github.zwliew.zwliew.util.retrofit

object AboutRepository {
    private val service = retrofit.create(AboutService::class.java)

    private val cache = AboutCache()

    suspend fun loadAbout(): AboutList {
        // Check cache
        if (cache.initialized) {
            return AboutList(
                cache.educations,
                cache.activities,
                cache.achievements
            )
        }

        // Fetch from network
        val response = service.getAboutAsync().await()
        return response.also {
            // Cache the retrieved data
            cache.educations = it.education
            cache.achievements = it.achievements
            cache.activities = it.activities
            cache.initialized = true
        }
    }
}