package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.TestServiceA
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.assertInstanceOf
import nl.jvhaastert.dependencyinjection.mock
import org.junit.jupiter.api.Test

class FactoryServiceSupplierTest {

    @Test
    fun `getInstance should return new instance`() {
        val serviceProviderMock: ServiceProvider = mock()
        val factory: Factory<TestServiceA> = { TestServiceA() }
        val sut = FactoryServiceSupplier(TestServiceA::class.java, factory)

        val result = sut.getInstance(serviceProviderMock)

        assertInstanceOf<TestServiceA>(result)
    }

}
