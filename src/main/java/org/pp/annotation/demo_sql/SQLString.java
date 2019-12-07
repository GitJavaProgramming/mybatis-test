package org.pp.annotation.demo_sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString{
	int value() default 0;
	String name() default "";
	Constraints constraints() default @Constraints;
}