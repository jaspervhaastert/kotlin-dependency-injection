package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Supplier

internal data class ServiceSupplier<T>(
    val serviceClass: Class<T>,
    val supplier: Supplier<T>
) {

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
