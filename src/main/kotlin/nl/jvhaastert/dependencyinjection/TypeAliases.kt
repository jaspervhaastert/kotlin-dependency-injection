package nl.jvhaastert.dependencyinjection

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

/**
 * A function which has a [ServiceProvider] as its receiver and should return an instance of the specified type.
 *
 * @param[T] The type which the function should return.
 */
public typealias Factory<T> = ServiceProvider.() -> T
