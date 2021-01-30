package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import nl.jvhaastert.dependencyinjection.implementations.ServiceCollection as ServiceCollectionImpl

public interface ServiceCollection {

    /**
     * Gets all [ServiceSupplier]'s for the given service class.
     *
     * @param[T] The type of service for which to get the [ServiceSupplier]'s.
     * @param[serviceClass] The service class for which to get the [ServiceSupplier]'s.
     * @return A list of 0 or more [ServiceSupplier]'s.
     */
    public fun <T> getAllSuppliers(serviceClass: Class<T>): List<ServiceSupplier<out T>>

    /**
     * Gets the [ServiceSupplier] for the given service class.
     *
     * @param[T] The type of service for which to get the [ServiceSupplier].
     * @param[serviceClass] The service class for which to get the [ServiceSupplier].
     * @return The [ServiceSupplier] when found or else `null`.
     */
    public fun <T> getSupplier(serviceClass: Class<T>): ServiceSupplier<T>?

    /**
     * Adds a singleton [ServiceSupplier] with the given instance.
     *
     * @param[T] The type of service for which to add a [ServiceSupplier].
     * @param[serviceClass] The service class for which to add a [ServiceSupplier].
     * @param[instance] An instance of [T] to add.
     */
    public fun <T> addSingleton(serviceClass: Class<T>, instance: T)

    /**
     * Adds a lazy singleton [ServiceSupplier] with the given factory.
     *
     * @param[T] The type of service for which to add a [ServiceSupplier].
     * @param[serviceClass] The service class for which to add a [ServiceSupplier].
     * @param[factory] The [Factory] to add.
     */
    public fun <T> addSingleton(serviceClass: Class<T>, factory: Factory<T>)

    /**
     * Adds a factory [ServiceSupplier].
     *
     * @param[T] The type of service for which to add a [ServiceSupplier].
     * @param[serviceClass] The service class for which to add a [ServiceSupplier].
     * @param[factory] The [Factory] to add.
     */
    public fun <T> addFactory(serviceClass: Class<T>, factory: Factory<T>)

    public companion object {

        /**
         * Creates a default [ServiceCollection].
         *
         * @return A new [ServiceCollection] instance.
         */
        public fun create(): ServiceCollection = ServiceCollectionImpl()

    }

}
