package com.softtech.jpos.enumer;

import com.softtech.jpos.constants.Constants;

/**
 * @author Kisman Hong
 * Command enum class, to declare SALE, ECHO, etc.
 */
public enum CommandType {
	SALE(Constants.SALE),
	ECHO(Constants.ECHO),
	VOID(Constants.VOID),
	REVERSAL(Constants.REVERSAL),
	VOID_REVERSAL(Constants.VOID_REVERSAL),
	SALE_RESPONSE(Constants.SALE_RESPONSE),
	ECHO_RESPONSE(Constants.ECHO_RESPONSE),
	VOID_RESPONSE(Constants.VOID_RESPONSE),
	REVERSAL_RESPONSE(Constants.REVERSAL_RESPONSE),
	VOID_REVERSAL_RESPONSE(Constants.VOID_REVERSAL_RESPONSE)
	;
	
	private String value;
	
	private CommandType(String value) {
		this.value = value;
	}
	
	public String getString(){
		return value;
	}
}
