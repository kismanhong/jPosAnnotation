package com.softtech.jpos.test.model;

import com.softtech.jpos.annotation.Echo;
import com.softtech.jpos.annotation.Sale;
import com.softtech.jpos.annotation.Ignore;

/**
 * @author Kisman Hong
 *
 */
public class CardAcceptor {
	@Sale(fldNo = 2)
	@Echo(fldNo = 2)
	private String cardNo;
	
	@Sale(fldNo = 14)
	private String cardExpire;
	
	@Sale(fldNo = 48)
	private String cvv;
	
	@Ignore
	private CardAcceptorInfo cardAcceptorInfo;

	public CardAcceptor() {
		super();
	}

	public CardAcceptor(String cardNo, String cardExpire, String cvv,
			CardAcceptorInfo cardAcceptorInfo) {
		super();
		this.cardNo = cardNo;
		this.cardExpire = cardExpire;
		this.cvv = cvv;
		this.cardAcceptorInfo = cardAcceptorInfo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardExpire() {
		return cardExpire;
	}

	public void setCardExpire(String cardExpire) {
		this.cardExpire = cardExpire;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public CardAcceptorInfo getCardAcceptorInfo() {
		return cardAcceptorInfo;
	}

	public void setCardAcceptorInfo(CardAcceptorInfo cardAcceptorInfo) {
		this.cardAcceptorInfo = cardAcceptorInfo;
	}
	
}
