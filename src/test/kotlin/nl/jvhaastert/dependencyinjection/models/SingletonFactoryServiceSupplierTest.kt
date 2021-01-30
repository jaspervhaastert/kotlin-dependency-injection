package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.TestServiceA
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.assertInstanceOf
import nl.jvhaastert.dependencyinjection.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingletonFactoryServiceSupplierTest {

    @Test
    fun `first call to getInstance should return new instance`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestServiceA> = { TestServiceA() }
        val sut = SingletonFactoryServiceSupplier(TestServiceA::class.java, factory)

        val result = sut.getInstance(serviceProviderMock)

        assertInstanceOf<TestServiceA>(result)
    }

    @Test
    fun `second call to getInstance should return same instance as first call`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestServiceA> = { TestServiceA() }
        val sut = SingletonFactoryServiceSupplier(TestServiceA::class.java, factory)
        val expectedInstance = sut.getInstance(serviceProviderMock)

        val result = sut.getInstance(serviceProviderMock)

        assertEquals(expectedInstance, result)
    }

}
