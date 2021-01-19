package nl.jvhaastert.dependencyinjection

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@Suppress("FunctionName")
class SimpleIntegrationTests {

    @Test
    fun `the one where it's tried to retrieve an existent service`() {
        val serviceCollection = ServiceCollection.create()
        serviceCollection[String::class.java] = { "Value" }
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.get(String::class.java)

        assertEquals("Value", result)
    }

    @Test
    fun `the one where it's tried to retrieve a non-existent service`() {
        val serviceCollection = ServiceCollection.create()
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.get(String::class.java)

        assertNull(result)
    }

    @Test
    fun `the one where an existent service is retrieved`() {
        val serviceCollection = ServiceCollection.create()
        serviceCollection[String::class.java] = { "Value" }
        val serviceProvider = ServiceProvider.create(serviceCollection)

        val result = serviceProvider.getRequired(String::class.java)

        assertEquals("Value", result)
    }

    @Test
    fun `the one where a non-existent service is retrieved`() {
        val serviceCollection = ServiceCollection.create()
        val serviceProvider = ServiceProvider.create(serviceCollection)

        fun act() = serviceProvider.getRequired(String::class.java)

        assertThrows(NoServiceSupplierFoundException::class.java, ::act)
    }

}
