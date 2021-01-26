package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import nl.jvhaastert.dependencyinjection.implementations.ServiceCollection as ServiceCollectionImpl

public interface ServiceCollection {

    public fun <T> getSupplier(serviceClass: Class<T>): ServiceSupplier<T>?

    public fun <T> addSingleton(serviceClass: Class<T>, instance: T)
    public fun <T> addSingleton(serviceClass: Class<T>, factory: Factory<T>)
    public fun <T> addFactory(serviceClass: Class<T>, factory: Factory<T>)

    public companion object {
        public fun create(): ServiceCollection = ServiceCollectionImpl()
    }

}
