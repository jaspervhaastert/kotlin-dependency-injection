package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.models.ServiceSupplier

internal class ServiceSupplierSet : MutableSet<ServiceSupplier<*>> by mutableSetOf() {

    fun <T> singleOrNull(predicate: (ServiceSupplier<T>) -> Boolean): ServiceSupplier<T>? {
        return filterIsInstance<ServiceSupplier<T>>().singleOrNull(predicate)
    }

    operator fun plusAssign(serviceSupplier: ServiceSupplier<*>) {
        if (serviceSupplier in this) remove(serviceSupplier)
        add(serviceSupplier)
    }

}
