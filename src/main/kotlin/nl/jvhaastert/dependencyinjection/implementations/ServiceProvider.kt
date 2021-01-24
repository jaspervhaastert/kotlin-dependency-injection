package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException

internal class ServiceProvider(
    private val services: ServiceCollection
) : ServiceProvider {

    override fun <T> get(serviceClass: Class<T>): T? {
        val supplier = services.get(serviceClass)
        return supplier?.getInstance(this)
    }

    override fun <T> getRequired(serviceClass: Class<T>) =
        get(serviceClass) ?: throw NoServiceSupplierFoundException(serviceClass)

}
