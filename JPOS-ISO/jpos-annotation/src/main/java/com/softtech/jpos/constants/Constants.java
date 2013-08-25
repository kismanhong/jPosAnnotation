package com.softtech.jpos.constants;

import java.util.Hashtable;

/**
 * @author Kisman Hong
 * declaring constants here
 */
public class Constants {
	
	public static final String HEAD 					= "-head";
		
	public static final String SALE 					= "@Sale";
	
	public static final String ECHO	 					= "@Echo";
	
	public static final String VOID 					= "@Void";
	
	public static final String REVERSAL 				= "@Reversal";
	
	public static final String VOID_REVERSAL			= "@VoidReversal";
	
	public static final String SALE_RESPONSE 			= "@SaleResponse";
	
	public static final String ECHO_RESPONSE	 		= "@EchoResponse";
	
	public static final String VOID_RESPONSE 			= "@VoidResponse";
	
	public static final String REVERSAL_RESPONSE 		= "@ReversalResponse";
	
	public static final String VOID_REVERSAL_RESPONSE	= "@VoidReversalResponse";
	
	public static final String SALE_MTI 				= "sale-mti";
	
	public static final String ECHO_MTI 				= "echo-mti";
	
	public static final String VOID_MTI 				= "void-mti";
	
	public static final String REVERSAL_MTI 			= "reversal-mti";
	
	public static final String VOID_REVERSAL_MTI 		= "void-reversal-mti";
	
	public static final String PACKAGER_CLASS 			= "packager-class";

	public static Hashtable<String, Object> isoConfigs 	= new Hashtable<String, Object>();	
	
}
