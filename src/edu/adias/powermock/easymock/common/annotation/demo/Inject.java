package edu.adias.powermock.easymock.common.annotation.demo;

import java.lang.annotation.*;

/**
 * A custom marker annotation to demonstrate how to use PowerMock to set field
 * dependencies. In a real application these dependencies are set by an external
 * dependency injection framework using reflection.
 */
@Target( { ElementType.FIELD, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {

}
