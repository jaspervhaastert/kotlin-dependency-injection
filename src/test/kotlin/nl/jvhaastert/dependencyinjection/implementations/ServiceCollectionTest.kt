package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Factory
import nl.jvhaastert.dependencyinjection.models.FactoryServiceSupplier
import nl.jvhaastert.dependencyinjection.models.InstanceServiceSupplier
import nl.jvhaastert.dependencyinjection.models.SingletonFactoryServiceSupplier
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

    //region addSingleton
    @Test
    fun `addSingleton with instance should add correct ServiceSupplier`() {
        val instance = "Value"
        val expectedServiceSupplier = InstanceServiceSupplier(String::class.java, instance)

        sut.addSingleton(String::class.java, instance)

        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single { it.serviceClass == String::class.java })
    }

    @Test
    fun `addSingleton with factory should add correct ServiceSupplier`() {
        val factory: Factory<String> = { "Value" }
        val expectedServiceSupplier = SingletonFactoryServiceSupplier(String::class.java, factory)

        sut.addSingleton(String::class.java, factory)

        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single { it.serviceClass == String::class.java })
    }
    //endregion

    //region addFactory
    @Test
    fun `addFactory with non-existent serviceClass should add correct ServiceSupplier`() {
        val factory: Factory<String> = { "Value" }
        val expectedServiceSupplier = FactoryServiceSupplier(String::class.java, factory)

        sut.addFactory(String::class.java, factory)

        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single { it.serviceClass == String::class.java })
    }

    @Test
    fun `addFactory with existent serviceClass should overwrite ServiceSupplier`() {
        sut.serviceSuppliers.add(FactoryServiceSupplier(String::class.java) { "Old value" })
        val newFactory: Factory<String> = { "New value" }
        val expectedServiceSupplier = FactoryServiceSupplier(String::class.java, newFactory)

        sut.addFactory(String::class.java, newFactory)

        assertEquals(1, sut.serviceSuppliers.size)
        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single())
    }
    //endregion

}
