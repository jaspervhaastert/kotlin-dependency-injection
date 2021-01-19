package nl.jvhaastert.dependencyinjection.implementations

import nl.jvhaastert.dependencyinjection.Supplier
import nl.jvhaastert.dependencyinjection.models.ServiceSupplier
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
        val supplier: Supplier<String> = { "Value" }
        sut.serviceSuppliers.add(ServiceSupplier(String::class.java, supplier))

        val result = sut[String::class.java]

        assertEquals(supplier, result)
    }

    @Test
    fun `get with non-existent serviceClass should return null`() {
        // No arrange

        val result = sut[String::class.java]

        assertNull(result)
    }
    //endregion

    //region set
    @Test
    fun `set with non-existent serviceClass should add correct ServiceSupplier`() {
        val supplier: Supplier<String> = { "Value" }
        val expectedServiceSupplier = ServiceSupplier(String::class.java, supplier)

        sut[String::class.java] = supplier

        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single { it.serviceClass == String::class.java })
    }

    @Test
    fun `set with existent serviceClass should overwrite ServiceSupplier`() {
        sut.serviceSuppliers.add(ServiceSupplier(String::class.java) { "Old value" })
        val newSupplier: Supplier<String> = { "New value" }
        val expectedServiceSupplier = ServiceSupplier(String::class.java, newSupplier)

        sut[String::class.java] = newSupplier

        assertEquals(1, sut.serviceSuppliers.size)
        assertEquals(expectedServiceSupplier, sut.serviceSuppliers.single())
    }
    //endregion

}
