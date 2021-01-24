package nl.jvhaastert.dependencyinjection

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import nl.jvhaastert.dependencyinjection.extensions.getRequired
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SimpleIntegrationTests {

    @Test
    fun `the one where it's tried to retrieve an existent service with factory supplier`() {
        val serviceCollection = ServiceCollection.create()
        serviceCollection.addFactory(TestServiceA::class.java) { TestServiceA() }
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.get(TestServiceA::class.java)

        assertInstanceOf<TestServiceA>(result)
    }

    @Test
    fun `the one where it's tried to retrieve a non-existent service`() {
        val serviceCollection = ServiceCollection.create()
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.get(TestServiceA::class.java)

        assertNull(result)
    }

    @Test
    fun `the one where an existent service is retrieved with factory supplier`() {
        val serviceCollection = ServiceCollection.create()
        serviceCollection.addFactory(TestServiceA::class.java) { TestServiceA() }
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.getRequired(TestServiceA::class.java)

        assertInstanceOf<TestServiceA>(result)
    }

    @Test
    fun `the one where an existent service with existent service parameter is retrieved with factory supplier`() {
        val serviceCollection = ServiceCollection.create()
        serviceCollection.addFactory(TestServiceA::class.java) { TestServiceA() }
        serviceCollection.addFactory(TestServiceB::class.java) { TestServiceB(getRequired()) }
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.getRequired(TestServiceB::class.java)

        assertNotNull(result)
    }

    @Test
    fun `the one where a non-existent service is retrieved`() {
        val serviceCollection = ServiceCollection.create()
        val serviceProvider = ServiceProvider.create(serviceCollection)

        fun act() = serviceProvider.getRequired(TestServiceA::class.java)

        assertThrows(NoServiceSupplierFoundException::class.java, ::act)
    }

}
