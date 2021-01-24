package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier

internal class ServiceCollection : ServiceCollection {

    internal val serviceSuppliers = ServiceSuppliers()

    override fun <T> get(serviceClass: Class<T>) = serviceSuppliers.singleOrNull<T> { it.serviceClass == serviceClass }

    override fun <T> addFactory(serviceClass: Class<T>, factory: Factory<out T>) {
        val factoryServiceSupplier = FactoryServiceSupplier(serviceClass, factory)
        serviceSuppliers += factoryServiceSupplier
    }

}
