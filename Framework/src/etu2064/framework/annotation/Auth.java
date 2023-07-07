package etu2064.framework.myAnnotations;

import java.lang.annotation.*;
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    String profile() default "";
}
