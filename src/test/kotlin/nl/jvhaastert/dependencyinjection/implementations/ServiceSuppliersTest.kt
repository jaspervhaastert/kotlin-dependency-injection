package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.TestServiceA
import nl.jvhaastert.dependencyinjection.TestServiceC
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates.notNull

class ServiceSuppliersTest {

    private var set: MutableSet<ServiceSupplier<*>> by notNull()
    private var sut: ServiceSuppliers by notNull()

    @BeforeEach
    fun beforeEach() {
        set = mutableSetOf()
        sut = ServiceSuppliers(set)
    }

    //region filter
    @Test
    fun `filter should return correct ServiceSupplier list`() {
        val serviceSupplierA = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        val serviceSupplierC = FactoryServiceSupplier(TestServiceC::class.java) { TestServiceC() }
        set.add(serviceSupplierA)
        set.add(serviceSupplierC)
        val expectedList = listOf(serviceSupplierA)

        val result = sut.filter<TestServiceA> { s -> s.serviceClass == TestServiceA::class.java }

        assertEquals(expectedList, result)
    }

    @Test
    fun `filter with non-existent ServiceSupplier's should return empty list`() {
        // No arrange

        val result = sut.filter<TestServiceA> { s -> s.serviceClass == TestServiceA::class.java }

        assertTrue { result.isEmpty() }
    }
    //endregion

    //region singleOrNull
    @Test
    fun `singleOrNull with existent ServiceSupplier should return correct ServiceSupplier`() {
        val factoryServiceSupplierA = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        val factoryServiceSupplierC = FactoryServiceSupplier(TestServiceC::class.java) { TestServiceC() }
        set.add(factoryServiceSupplierA)
        set.add(factoryServiceSupplierC)

        val result = sut.singleOrNull<TestServiceA> { s -> s.serviceClass == TestServiceA::class.java }

        assertEquals(factoryServiceSupplierA, result)
    }

    @Test
    fun `singleOrNull with non-existent ServiceSupplier should return null`() {
        // No arrange

        val result = sut.singleOrNull<TestServiceA> { s -> s.serviceClass == TestServiceA::class.java }

        assertNull(result)
    }
    //endregion

    //region plusAssign
    @Test
    fun `plusAssign with non-existent serviceSupplier should add to set`() {
        val serviceSupplier = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        val expectedSet = setOf(serviceSupplier)

        sut.plusAssign(serviceSupplier)

        assertEquals(expectedSet, set)
    }

    @Test
    fun `plusAssign with existent serviceSupplier should replace in set`() {
        val existingServiceSupplier = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        set.add(existingServiceSupplier)
        val newServiceSupplier = FactoryServiceSupplier(TestServiceA::class.java) { TestServiceA() }
        val expectedSet = setOf(newServiceSupplier)

        sut.plusAssign(newServiceSupplier)

        assertEquals(expectedSet, set)
    }
    //endregion

}
