package nl.jvhaastert.dependencyinjection.exceptions

public abstract class DependencyInjectionException : Exception {

    public constructor(message: String) : super(message)
    public constructor(message: String, cause: Throwable?) : super(message, cause)

}
