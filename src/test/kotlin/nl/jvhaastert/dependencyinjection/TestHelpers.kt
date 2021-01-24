package nl.jvhaastert.dependencyinjection

import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
inline fun <reified T> any(): T = Mockito.any(T::class.java)

inline fun <reified T> assertInstanceOf(actual: Any?) = assertTrue { actual is T }
