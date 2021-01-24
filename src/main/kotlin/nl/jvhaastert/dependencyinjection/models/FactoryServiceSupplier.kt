package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

internal class FactoryServiceSupplier<T>(
    serviceClass: Class<T>,
    private val factory: Factory<T>
) : ServiceSupplier<T>(serviceClass) {

    override fun getInstance(serviceProvider: ServiceProvider) = factory(serviceProvider)

}
