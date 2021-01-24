package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider

public abstract class ServiceSupplier<T>(
    public val serviceClass: Class<T>
) {

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
