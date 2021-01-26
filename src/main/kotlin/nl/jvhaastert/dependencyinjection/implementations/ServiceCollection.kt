package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import nl.jvhaastert.dependencyinjection.models.InstanceServiceSupplier
import nl.jvhaastert.dependencyinjection.models.SingletonFactoryServiceSupplier

internal class ServiceCollection : ServiceCollection {

    internal val serviceSuppliers = ServiceSuppliers()

    override fun <T> getSupplier(serviceClass: Class<T>) = serviceSuppliers.singleOrNull<T> { it.serviceClass == serviceClass }

    override fun <T> addSingleton(serviceClass: Class<T>, instance: T) {
        val serviceSupplier = InstanceServiceSupplier(serviceClass, instance)
        serviceSuppliers += serviceSupplier
    }

    override fun <T> addSingleton(serviceClass: Class<T>, factory: Factory<T>) {
        val serviceSupplier = SingletonFactoryServiceSupplier(serviceClass, factory)
        serviceSuppliers += serviceSupplier
    }

    override fun <T> addFactory(serviceClass: Class<T>, factory: Factory<out T>) {
        val serviceSupplier = FactoryServiceSupplier(serviceClass, factory)
        serviceSuppliers += serviceSupplier
    }

}
