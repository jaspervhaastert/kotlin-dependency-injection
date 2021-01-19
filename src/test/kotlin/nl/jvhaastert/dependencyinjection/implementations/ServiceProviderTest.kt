package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.any
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import kotlin.properties.Delegates.notNull
import org.mockito.Mockito.`when` as whenever

class ServiceProviderTest {

    private var serviceCollectionMock: ServiceCollection by notNull()
    private var sut: ServiceProvider by notNull()

    @BeforeEach
    fun beforeEach() {
        serviceCollectionMock = Mockito.mock(ServiceCollection::class.java)
        sut = ServiceProvider(serviceCollectionMock)
    }

    //region get
    @Test
    fun `get with existent serviceClass should return correct instance`() {
        whenever(serviceCollectionMock.get<String>(any())).thenReturn { "Value" }

        val result = sut.get(String::class.java)

        assertEquals("Value", result)
    }

    @Test
    fun `get with non-existent serviceClass should return null`() {
        whenever(serviceCollectionMock.get<String>(any())).thenReturn(null)

        val result = sut.get(String::class.java)

        assertNull(result)
    }
    //endregion

    //region getRequired
    @Test
    fun `getRequired with existent serviceClass should return correct instance`() {
        whenever(serviceCollectionMock.get<String>(any())).thenReturn { "Value" }

        val result = sut.getRequired(String::class.java)

        assertEquals("Value", result)
    }

    @Test
    fun `getRequired with non-existent serviceClass should throw NoServiceSupplierFoundException`() {
        whenever(serviceCollectionMock.get<String>(any())).thenReturn(null)

        fun act() = sut.getRequired(String::class.java)

        val exception = assertThrows(NoServiceSupplierFoundException::class.java, ::act)
        assertEquals("No service supplier found for type String", exception.message)
    }
    //endregion

}
