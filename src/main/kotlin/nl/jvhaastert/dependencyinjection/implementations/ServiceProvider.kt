package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException

internal class ServiceProvider(
    private val services: ServiceCollection
) : ServiceProvider {

    override fun <T> getOrNull(serviceClass: Class<T>): T? {
        val supplier = services.getSupplier(serviceClass)
        return supplier?.getInstance(this)
    }

    override fun <T> get(serviceClass: Class<T>) =
        getOrNull(serviceClass) ?: throw NoServiceSupplierFoundException(serviceClass)

}
