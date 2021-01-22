package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException

internal class ServiceProvider(
    private val services: ServiceCollection
) : ServiceProvider {

    override fun <T> get(serviceClass: Class<T>): T? {
        val supplier = services[serviceClass]
        return if (supplier != null) supplier() else null
    }

    override fun <T> getRequired(serviceClass: Class<T>): T {
        return get(serviceClass) ?: throw NoServiceSupplierFoundException(serviceClass)
    }

}
