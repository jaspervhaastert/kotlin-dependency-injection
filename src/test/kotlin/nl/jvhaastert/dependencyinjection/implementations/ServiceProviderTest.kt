package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.TestService
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.any
import nl.jvhaastert.dependencyinjection.assertInstanceOf
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import nl.jvhaastert.dependencyinjection.mock
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates.notNull
import org.mockito.Mockito.`when` as whenever

class ServiceProviderTest {

    private var serviceCollectionMock: ServiceCollection by notNull()
    private var sut: ServiceProvider by notNull()

    @BeforeEach
    fun beforeEach() {
        serviceCollectionMock = mock()
        sut = ServiceProvider(serviceCollectionMock)
    }

    //region get
    @Test
    fun `get with existent serviceClass should return correct instance`() {
        val serviceSupplierMock = FactoryServiceSupplier(TestService::class.java) { TestService() }
        whenever(serviceCollectionMock.get<TestService>(any())).thenReturn(serviceSupplierMock)

        val result = sut.get(TestService::class.java)

        assertInstanceOf<TestService>(result)
    }

    @Test
    fun `get with non-existent serviceClass should return null`() {
        whenever(serviceCollectionMock.get<TestService>(any())).thenReturn(null)

        val result = sut.get(TestService::class.java)

        assertNull(result)
    }
    //endregion

    //region getRequired
    @Test
    fun `getRequired with existent serviceClass should return correct instance`() {
        val serviceSupplierMock = FactoryServiceSupplier(TestService::class.java) { TestService() }
        whenever(serviceCollectionMock.get<TestService>(any())).thenReturn(serviceSupplierMock)

        val result = sut.getRequired(TestService::class.java)

        assertInstanceOf<TestService>(result)
    }

    @Test
    fun `getRequired with non-existent serviceClass should throw NoServiceSupplierFoundException`() {
        whenever(serviceCollectionMock.get<TestService>(any())).thenReturn(null)

        fun act() = sut.getRequired(TestService::class.java)

        val exception = assertThrows(NoServiceSupplierFoundException::class.java, ::act)
        assertEquals("No service supplier found for type TestService", exception.message)
    }
    //endregion

}
