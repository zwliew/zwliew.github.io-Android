package io.github.zwliew.zwliew.destinations.about

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AboutService {
    @GET("res/data/about.json")
    fun getAboutAsync(): Deferred<AboutApi>
}
