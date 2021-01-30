package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import nl.jvhaastert.dependencyinjection.implementations.ServiceProvider as ServiceProviderImpl

public interface ServiceProvider {

    public fun <T> getAll(serviceClass: Class<T>): List<T>

    /**
     * Tries to get an instance for the given service type.
     *
     * @param[T] The type of service for which to get an instance.
     * @param[serviceClass] The service class for which to get an instance.
     * @return An instance of [T] or null when no [ServiceSupplier] for the given [serviceClass] can be found.
     */
    public fun <T> getOrNull(serviceClass: Class<T>): T?

    /**
     * Gets an instance for the given service class.
     *
     * @param[T] The type of service for which to get an instance.
     * @param[serviceClass] The service class for which to get an instance.
     * @return An instance of [T].
     * @throws[NoServiceSupplierFoundException] When no [ServiceSupplier] for the given [serviceClass] can be found.
     */
    public fun <T> get(serviceClass: Class<T>): T

    public companion object {

        /**
         * Creates a default [ServiceProvider].
         *
         * @param[serviceCollection] The [ServiceCollection] to use in the new [ServiceProvider].
         * @return A new [ServiceProvider] instance.
         */
        public fun create(serviceCollection: ServiceCollection): ServiceProvider =
            ServiceProviderImpl(serviceCollection)

    }

}
