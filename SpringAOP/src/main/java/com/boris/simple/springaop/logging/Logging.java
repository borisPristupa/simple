package com.boris.simple.springaop.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Logging {
    /**
     *
     * @return the name of the public method in the annotated class that returns <b>boolean</b>, which
     * determines whether logging should be applied or not. By default logging is enabled.
     */
    String needed() default "";
}
