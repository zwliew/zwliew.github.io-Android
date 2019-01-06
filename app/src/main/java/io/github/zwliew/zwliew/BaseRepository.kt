package io.github.zwliew.zwliew

import androidx.lifecycle.LiveData

interface BaseRepository<T> {
    // Observable state
    val state: LiveData<T>

    suspend fun load()
}

sealed class RepositoryStatus
object Empty : RepositoryStatus()
object Loading : RepositoryStatus()
object Loaded : RepositoryStatus()
object Failed : RepositoryStatus()