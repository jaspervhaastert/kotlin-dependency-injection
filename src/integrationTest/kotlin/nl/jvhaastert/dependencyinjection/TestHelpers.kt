package nl.jvhaastert.dependencyinjection

import org.junit.jupiter.api.Assertions

inline fun <reified T> assertInstanceOf(actual: Any?) = Assertions.assertTrue { actual is T }
