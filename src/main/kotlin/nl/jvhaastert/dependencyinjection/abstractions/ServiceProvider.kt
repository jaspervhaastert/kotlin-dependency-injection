package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import nl.jvhaastert.dependencyinjection.implementations.ServiceProvider as ServiceProviderImpl

public interface ServiceProvider {

    /**
     * Tries to get an instance for the given service class.
     *
     * @param[serviceClass] The service class for which to get an instance.
     * @return An instance of [serviceClass] or null when no [ServiceSupplier] is found.
     */
    public fun <T> getOrNull(serviceClass: Class<T>): T?

    /**
     * Gets an instance for the given service class.
     *
     * @param[serviceClass] The service class for which to get an instance.
     * @return An instance of the service class
     * @throws[NoServiceSupplierFoundException] When no [ServiceSupplier] for the given [serviceClass] can be found.
     */
    public fun <T> get(serviceClass: Class<T>): T

    public companion object {

        /**
         * Creates a default [ServiceProvider].
         *
         * @param[serviceCollection] The [ServiceCollection] to create the [ServiceProvider] with.
         * @return A new [ServiceProvider] instance.
         */
        public fun create(serviceCollection: ServiceCollection): ServiceProvider =
            ServiceProviderImpl(serviceCollection)

    }

}
