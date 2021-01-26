package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

internal class SingletonFactoryServiceSupplier<T>(
    serviceClass: Class<T>,
    private val factory: Factory<T>
) : ServiceSupplier<T>(serviceClass) {

    private var instance: T? = null

    override fun getInstance(serviceProvider: ServiceProvider) =
        instance ?: factory(serviceProvider).also { instance = it }

}
