package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

/**
 * Supplier for a certain service type.
 *
 * @param[T] The type of service that this supplier supplies.
 * @property[serviceClass] The class that this supplier supplies.
 */
public abstract class ServiceSupplier<T>(
    public val serviceClass: Class<T>
) {

    /**
     * Gets an instance.
     *
     * The behavior of this function is dependent on the specific implementation in subclasses.
     *
     * @param[serviceProvider] A [ServiceProvider] instance which, if needed, may be used during instantiation of [T].
     * @return An instance of [T].
     */
    public abstract fun getInstance(serviceProvider: ServiceProvider): T

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ServiceSupplier<*>

        if (serviceClass != other.serviceClass) return false

        return true
    }

    override fun hashCode(): Int {
        return serviceClass.hashCode()
    }

}
