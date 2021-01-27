package nl.jvhaastert.dependencyinjection.extensions

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier

public inline fun <reified T> ServiceCollection.getSupplier(): ServiceSupplier<T>? = getSupplier(T::class.java)

public inline fun <reified T> ServiceCollection.addSingleton(instance: T): Unit = addSingleton(T::class.java, instance)

public inline fun <reified T> ServiceCollection.addSingleton(noinline factory: Factory<T>): Unit =
    addSingleton(T::class.java, factory)

public inline fun <reified T> ServiceCollection.addFactory(noinline factory: Factory<T>): Unit =
    addFactory(T::class.java, factory)
