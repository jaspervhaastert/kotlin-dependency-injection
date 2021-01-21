package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Supplier
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier

internal class ServiceCollection : ServiceCollection {

    internal val serviceSuppliers = ServiceSuppliers()

    override fun <T> get(serviceClass: Class<T>): Supplier<T>? {
        return serviceSuppliers.singleOrNull<T> { it.serviceClass == serviceClass }?.supplier
    }

    override fun <T> set(serviceClass: Class<T>, supplier: Supplier<out T>) {
        val serviceSupplier = ServiceSupplier(serviceClass, supplier)
        serviceSuppliers += serviceSupplier
    }

}
