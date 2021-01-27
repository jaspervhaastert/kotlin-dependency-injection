package nl.jvhaastert.dependencyinjection.extensions

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

public inline fun <reified T> ServiceProvider.getOrNull(): T? = getOrNull(T::class.java)
public inline fun <reified T> ServiceProvider.get(): T = get(T::class.java)
