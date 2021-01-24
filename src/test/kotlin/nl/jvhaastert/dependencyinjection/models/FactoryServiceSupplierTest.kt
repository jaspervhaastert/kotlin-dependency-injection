package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.TestService
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.assertInstanceOf
import nl.jvhaastert.dependencyinjection.mock
import org.junit.jupiter.api.Test

class FactoryServiceSupplierTest {

    @Test
    fun `getInstance should return new instance`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestService> = { TestService() }
        val sut = FactoryServiceSupplier(TestService::class.java, factory)

        val result = sut.getInstance(serviceProviderMock)

        assertInstanceOf<TestService>(result)
    }

}
