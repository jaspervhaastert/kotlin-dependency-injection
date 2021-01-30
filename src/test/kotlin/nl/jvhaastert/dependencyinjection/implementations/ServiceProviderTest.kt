package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.*
import nl.jvhaastert.dependencyinjection.abstractions.ServiceCollection
import nl.jvhaastert.dependencyinjection.exceptions.NoServiceSupplierFoundException
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

    //region getAll
    @Test
    fun `getAll should return correct list`() {
        val testServiceA = TestServiceA()
        val serviceSupplierA = FactoryServiceSupplier(TestServiceA::class.java) { testServiceA }
        whenever(serviceCollectionMock.getAllSuppliers<TestServiceA>(any())).thenReturn(listOf(serviceSupplierA))
        val expectedList = listOf(testServiceA)

        val result = sut.getAll(TestServiceA::class.java)

        assertEquals(expectedList, result)
    }

    @Test
    fun `getAll with subclasses should return correct list`() {
        val testServiceA = TestServiceA()
        val testServiceB = TestServiceB()
        val serviceSupplierA = FactoryServiceSupplier(TestServiceA::class.java) { testServiceA }
        val serviceSupplierB = FactoryServiceSupplier(TestServiceB::class.java) { testServiceB }
        whenever(serviceCollectionMock.getAllSuppliers<TestServiceA>(any()))
            .thenReturn(listOf(serviceSupplierA, serviceSupplierB))
        val expectedList = listOf(testServiceA, testServiceB)

        val result = sut.getAll(TestServiceA::class.java)

        assertEquals(expectedList, result)
    }
    //endregion

    //region getOrNull
    @Test
    fun `get with existent serviceClass should return correct instance`() {
        val serviceSupplierMock = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        whenever(serviceCollectionMock.getSupplier<TestServiceA>(any())).thenReturn(serviceSupplierMock)

        val result = sut.getOrNull(TestServiceA::class.java)

        assertInstanceOf<TestServiceA>(result)
    }

    @Test
    fun `get with non-existent serviceClass should return null`() {
        whenever(serviceCollectionMock.getSupplier<TestServiceA>(any())).thenReturn(null)

        val result = sut.getOrNull(TestServiceA::class.java)

        assertNull(result)
    }
    //endregion

    //region get
    @Test
    fun `getRequired with existent serviceClass should return correct instance`() {
        val serviceSupplierMock = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        whenever(serviceCollectionMock.getSupplier<TestServiceA>(any())).thenReturn(serviceSupplierMock)

        val result = sut.get(TestServiceA::class.java)

        assertInstanceOf<TestServiceA>(result)
    }

    @Test
    fun `getRequired with non-existent serviceClass should throw NoServiceSupplierFoundException`() {
        whenever(serviceCollectionMock.getSupplier<TestServiceA>(any())).thenReturn(null)

        fun act() = sut.get(TestServiceA::class.java)

        val exception = assertThrows(NoServiceSupplierFoundException::class.java, ::act)
        assertEquals("No service supplier found for type TestServiceA", exception.message)
    }
    //endregion

}
