package nl.jvhaastert.dependencyinjection

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

public typealias Factory<T> = ServiceProvider.() -> T
