package nl.jvhaastert.dependencyinjection

import org.mockito.Mockito

inline fun <reified T> any(): T = Mockito.any(T::class.java)
