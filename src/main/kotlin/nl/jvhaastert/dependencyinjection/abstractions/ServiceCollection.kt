package nl.jvhaastert.dependencyinjection.abstractions

import nl.jvhaastert.dependencyinjection.Supplier
import nl.jvhaastert.dependencyinjection.implementations.ServiceCollection as ServiceCollectionImpl

public interface ServiceCollection {

    public operator fun <T> get(serviceClass: Class<T>): Supplier<T>?
    public operator fun <T> set(serviceClass: Class<T>, supplier: Supplier<T>)

    public companion object {
        public fun create(): ServiceCollection = ServiceCollectionImpl()
    }

}
