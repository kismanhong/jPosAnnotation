package com.softtech.jpos.test.model;

import com.softtech.jpos.annotation.Sale;

/**
 * @author Kisman Hong
 *
 */
public class CardAcceptorInfo {
	
	@Sale(fldNo = 48)
//	@Echo(fldNo = 48)
	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public CardAcceptorInfo(String test) {
		super();
		this.test = test;
	}
	
}
