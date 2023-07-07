package etu2064.framework.myAnnotations;

import java.lang.annotation.*;
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    boolean singleton();
}
