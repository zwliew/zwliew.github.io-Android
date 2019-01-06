package io.github.zwliew.zwliew

class Event<T>(private val content: T) {
    var handled = false
        private set

    val value: T?
        get() {
            return if (handled) {
                null
            } else {
                handled = true
                content
            }
        }
}