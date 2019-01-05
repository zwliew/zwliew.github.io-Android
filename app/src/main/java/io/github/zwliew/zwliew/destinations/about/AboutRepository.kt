package io.github.zwliew.zwliew.destinations.about

import io.github.zwliew.zwliew.util.retrofit

object AboutRepository {
    private val service = retrofit.create(AboutService::class.java)

    private val cache = AboutCache()

    suspend fun loadEducations(): List<Education> {
        // Check cache
        if (cache.initialized) {
            return cache.education
        }

        // Fetch from network
        // Note: the API returns a list of Education objects that is named "education"
        val (education, _, _) = fetchFromNetwork()
        return education
    }

    suspend fun loadActivities(): List<Activity> {
        // Check cache
        if (cache.initialized) {
            return cache.activities
        }

        // Fetch from network
        val (_, activities, _) = fetchFromNetwork()
        return activities
    }

    suspend fun loadAchievements(): List<Achievement> {
        // Check cache
        if (cache.initialized) {
            return cache.achievements
        }

        // Fetch from network
        val (_, _, achievements) = fetchFromNetwork()
        return achievements
    }

    private suspend fun fetchFromNetwork(): AboutList {
        val data = service.getAboutAsync().await()
        return data.also {
            // Cache the retrieved data
            cache.education = it.education
            cache.achievements = it.achievements
            cache.activities = it.activities
            cache.initialized = true
        }
    }
}