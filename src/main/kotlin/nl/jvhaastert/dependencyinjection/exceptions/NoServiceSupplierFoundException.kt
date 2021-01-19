package nl.jvhaastert.dependencyinjection.exceptions

public class NoServiceSupplierFoundException public constructor(serviceClass: Class<*>) :
    DependencyInjectionException("No service supplier found for type ${serviceClass.simpleName}")
