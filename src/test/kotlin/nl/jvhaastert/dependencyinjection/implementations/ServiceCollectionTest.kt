package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.properties.Delegates.notNull

class ServiceCollectionTest {

    private var sut: ServiceCollection by notNull()

    @BeforeEach
    fun beforeEach() {
        sut = ServiceCollection()
    }

    //region get
    @Test
    fun `get with existent serviceClass should return correct Supplier`() {
        val factoryServiceSupplier = FactoryServiceSupplier(String::class.java) { "Value" }
        sut.serviceSuppliers.add(factoryServiceSupplier)

        val result = sut.get(String::class.java)

        assertEquals(factoryServiceSupplier, result)
    }

    @Test
    fun `get with non-existent serviceClass should return null`() {
        // No arrange

        val result = sut.get(String::class.java)

        assertNull(result)
    }
    //endregion

    //region set
    @Test
    fun `set with non-existent serviceClass should add correct ServiceSupplier`() {
        val factory: Factory<String> = { "Value" }
        val expectedServiceSupplier = FactoryServiceSupplier(String::class.java, factory)

        sut.addFactory(String::class.java, factory)

        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single { it.serviceClass == String::class.java })
    }

    @Test
    fun `set with existent serviceClass should overwrite ServiceSupplier`() {
        sut.serviceSuppliers.add(FactoryServiceSupplier(String::class.java) { "Old value" })
        val newFactory: Factory<String> = { "New value" }
        val expectedServiceSupplier = FactoryServiceSupplier(String::class.java, newFactory)

        sut.addFactory(String::class.java, newFactory)

        assertEquals(1, sut.serviceSuppliers.size)
        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single())
    }
    //endregion

}
