package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

internal class SingletonServiceSupplier<T>(
    serviceClass: Class<T>,
    private val instance: T
) : ServiceSupplier<T>(serviceClass) {

    override fun getInstance(serviceProvider: ServiceProvider) = instance

}
