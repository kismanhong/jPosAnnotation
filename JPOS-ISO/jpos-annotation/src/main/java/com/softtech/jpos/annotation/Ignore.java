package com.softtech.jpos.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Kisman Hong
 * ignoring object to be scanned, example RequestMessage.cardAcceptor
 * if cardAcceptor is tagged by {@link Ignore}, will not be scanned to get the informations from cardAcceptor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Ignore {

}
