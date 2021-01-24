package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.any
import nl.jvhaastert.dependencyinjection.mock
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import kotlin.properties.Delegates.notNull
import org.mockito.Mockito.`when` as whenever

class ServiceSuppliersTest {

    private var setMock: MutableSet<ServiceSupplier<*>> by notNull()
    private var sut: ServiceSuppliers by notNull()

    @BeforeEach
    fun beforeEach() {
        setMock = mock()
        sut = ServiceSuppliers(setMock)
    }

    //region singleOrNull
    @Test
    fun `singleOrNull with existent ServiceSupplier should return correct ServiceSupplier`() {
        val serviceSuppliers = mutableSetOf<FactoryServiceSupplier<*>>(
            FactoryServiceSupplier(String::class.java) { "String" },
            FactoryServiceSupplier(Int::class.java) { 1 }
        )
        whenever(setMock.iterator()).thenReturn(serviceSuppliers.iterator())
        val expectedServiceSupplier = FactoryServiceSupplier(String::class.java) { "String" }

        val result = sut.singleOrNull<String> { s -> s.serviceClass == String::class.java }

        assertEquals(expectedServiceSupplier, result)
    }

    @Test
    fun `singleOrNull with non-existent ServiceSupplier should return null`() {
        val serviceSuppliers = mutableSetOf<FactoryServiceSupplier<*>>(
            FactoryServiceSupplier(Int::class.java) { 1 }
        )
        whenever(setMock.iterator()).thenReturn(serviceSuppliers.iterator())

        val result = sut.singleOrNull<String> { s -> s.serviceClass == String::class.java }

        assertNull(result)
    }
    //endregion

    //region plusAssign
    @Test
    fun `plusAssign with non-existent serviceSupplier should add to set`() {
        whenever(setMock.contains(any())).thenReturn(false)
        val serviceSupplier = FactoryServiceSupplier(String::class.java) { "String" }

        sut.plusAssign(serviceSupplier)

        verify(setMock).contains(serviceSupplier)
        verify(setMock).add(serviceSupplier)
        verifyNoMoreInteractions(setMock)
    }

    @Test
    fun `plusAssign with existent serviceSupplier should replace in set`() {
        whenever(setMock.contains(any())).thenReturn(true)
        val serviceSupplier = FactoryServiceSupplier(String::class.java) { "String" }

        sut.plusAssign(serviceSupplier)

        verify(setMock).contains(serviceSupplier)
        verify(setMock).remove(serviceSupplier)
        verify(setMock).add(serviceSupplier)
        verifyNoMoreInteractions(setMock)
    }
    //endregion

}
