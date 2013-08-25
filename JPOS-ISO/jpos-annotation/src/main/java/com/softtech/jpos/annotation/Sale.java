package com.softtech.jpos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.jpos.iso.ISOMsg;

import com.softtech.jpos.enumer.PadType;

/**
 * @author Kisman Hong
 * for sale purpose, field that is tagged by this class will be included for composing {@link ISOMsg}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public abstract @interface Sale {
	public abstract int fldNo(); 
	public abstract PadType padType() default PadType.NONE;
	public abstract char padCharacter() default ' ';
	public abstract int padLength() default 0;
}
