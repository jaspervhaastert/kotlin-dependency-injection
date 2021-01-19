package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException

internal class ServiceProvider(
    private val services: ServiceCollection
) : ServiceProvider {

    override fun <T> get(serviceClass: Class<T>): T? {
        val supplier = services[serviceClass]
        return supplier?.invoke()
    }

    override fun <T> getRequired(serviceClass: Class<T>): T {
        val supplier = services[serviceClass]
        return supplier?.invoke() ?: throw NoServiceSupplierFoundException(serviceClass)
    }

}
