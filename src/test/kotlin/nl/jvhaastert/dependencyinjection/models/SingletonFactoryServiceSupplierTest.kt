package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.TestService
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.assertInstanceOf
import nl.jvhaastert.dependencyinjection.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SingletonFactoryServiceSupplierTest {

    @Test
    fun `first call to getInstance should return new instance`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestService> = { TestService() }
        val sut = SingletonFactoryServiceSupplier(TestService::class.java, factory)

        val result = sut.getInstance(serviceProviderMock)

        assertInstanceOf<TestService>(result)
    }

    @Test
    fun `second call to getInstance should return same instance as first call`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestService> = { TestService() }
        val sut = SingletonFactoryServiceSupplier(TestService::class.java, factory)
        val expectedInstance = sut.getInstance(serviceProviderMock)

        val result = sut.getInstance(serviceProviderMock)

        assertEquals(expectedInstance, result)
    }

}
