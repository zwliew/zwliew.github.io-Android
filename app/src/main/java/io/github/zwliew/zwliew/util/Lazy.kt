package io.github.zwliew.zwliew.util

/**
 * Use lazy without any thread synchronization. Useful when the initializer will definitely
 * only be run on 1 thread (the main/UI thread for example), and we can eek out some
 * performance gains as a result.
 */
fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)