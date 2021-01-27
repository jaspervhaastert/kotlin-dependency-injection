package nl.jvhaastert.dependencyinjection.models

import nl.jvhaastert.dependencyinjection.TestService
import nl.jvhaastert.dependencyinjection.abstractions.ServiceProvider
import nl.jvhaastert.dependencyinjection.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstanceServiceSupplierTest {

    @Test
    fun `getInstance should return same instance as passed in the constructor`() {
        val serviceProviderMock: ServiceProvider = mock()
        val testService = TestService()
        val sut = InstanceServiceSupplier(TestService::class.java, testService)

        val result = sut.getInstance(serviceProviderMock)

        assertEquals(testService, result)
    }

}
