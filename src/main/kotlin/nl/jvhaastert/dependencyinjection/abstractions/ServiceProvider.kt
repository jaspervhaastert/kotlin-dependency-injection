package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.implementations.ServiceProvider as ServiceProviderImpl

public interface ServiceProvider {

    public fun <T> getOrNull(serviceClass: Class<T>): T?
    public fun <T> get(serviceClass: Class<T>): T

    public companion object {
        public fun create(serviceCollection: ServiceCollection): ServiceProvider =
            ServiceProviderImpl(serviceCollection)
    }

}
