package nl.jvhaastert.dependencyinjection.extensions

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

public inline fun <reified T> ServiceProvider.get(): T? = get(T::class.java)
public inline fun <reified T> ServiceProvider.getRequired(): T = getRequired(T::class.java)
