package com.softtech.jpos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jpos.iso.packager.ISO87BPackager;

/**
 * @author Kisman Hong
 * packager and mti information is declared by using this tag,
 * for future, this will be used for declaring channel too.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public abstract @interface JPos {
	public abstract Class<?> packager() default ISO87BPackager.class;
	public abstract String saleMTI();
	public abstract String reversalMTI();
	public abstract String echoMTI();
	public abstract String voidMTI();
	public abstract String voidReversalMTI();
}
