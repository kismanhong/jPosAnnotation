 JPos Annotation (Complete documentation will be commited soon)
 
 
Easy to Create ISO Message from mapping class

Declaring class and make some tags :

RequestMessage.java

package com.softtech.jpos.test.model;

import com.softtech.jpos.annotation.Echo;
import com.softtech.jpos.annotation.JPos;
import com.softtech.jpos.annotation.Reversal;
import com.softtech.jpos.annotation.Sale;
import com.softtech.jpos.annotation.SaleResponse;
import com.softtech.jpos.annotation.Void;
import com.softtech.jpos.annotation.VoidReversal;
import com.softtech.jpos.enumer.PadType;

/**
 * @author Kisman Hong
 *
 */
@JPos(echoMTI = "0800", reversalMTI = "0400", saleMTI = "0200", voidMTI = "0800", voidReversalMTI="0300")
public class RequestMessage {
	
	private CardAcceptor cardAcceptor;
	
	@Sale(fldNo = 3, padType = PadType.ZERO, padLength = 6)
	@Echo(fldNo = 3)
	private String processingCode;
	
	@Sale(fldNo = 4)
	private Long amount;
	
	@Sale(fldNo = 11)
	@Void(fldNo = 11)
	private String traceNumber;

	@Sale(fldNo = 22)
	private String posEntryMode;
	
	@Sale(fldNo = 24)
	private String networkInternationalId;
	
	@Sale(fldNo = 25)
	@SaleResponse(fldNo = 25)
	private String posConditionCode;
	
	@Sale(fldNo = 41)
	@Reversal(fldNo = 41)
	private String terminalId;
	
	@Sale(fldNo = 42)
	@VoidReversal(fldNo = 42)
	private String acceptorId;
	
	public RequestMessage(){}

	public RequestMessage(CardAcceptor cardAcceptor, String processingCode, Long amount,
			String traceNumber, String posEntryMode,
			String networkInternationalId, String posConditionCode,
			String terminalId, String acceptorId) {
		super();
		this.cardAcceptor = cardAcceptor;
		this.processingCode = processingCode;
		this.amount = amount;
		this.traceNumber = traceNumber;
		this.posEntryMode = posEntryMode;
		this.networkInternationalId = networkInternationalId;
		this.posConditionCode = posConditionCode;
		this.terminalId = terminalId;
		this.acceptorId = acceptorId;
	}

	public CardAcceptor getCardAcceptor() {
		return cardAcceptor;
	}

	public void setCardAcceptor(CardAcceptor cardAcceptor) {
		this.cardAcceptor = cardAcceptor;
	}

	public String getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(String processingCode) {
		this.processingCode = processingCode;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getTraceNumber() {
		return traceNumber;
	}

	public void setTraceNumber(String traceNumber) {
		this.traceNumber = traceNumber;
	}

	public String getPosEntryMode() {
		return posEntryMode;
	}

	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}

	public String getNetworkInternationalId() {
		return networkInternationalId;
	}

	public void setNetworkInternationalId(String networkInternationalId) {
		this.networkInternationalId = networkInternationalId;
	}

	public String getPosConditionCode() {
		return posConditionCode;
	}

	public void setPosConditionCode(String posConditionCode) {
		this.posConditionCode = posConditionCode;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getAcceptorId() {
		return acceptorId;
	}

	public void setAcceptorId(String acceptorId) {
		this.acceptorId = acceptorId;
	}
	
}

CardAcceptor.java

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

CardAcceptorInfo.java

package com.softtech.jpos.test.model;

import com.softtech.jpos.annotation.Echo;
import com.softtech.jpos.annotation.Sale;

/**
 * @author Kisman Hong
 *
 */
public class CardAcceptorInfo {
	
	@Sale(fldNo = 48)
	@Echo(fldNo = 48)
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

Create Sale ISO Message :
ISOMsg isoMsg = ISOParser.createSaleISO(requestMessage);

Create Echo ISO Message :
ISOMsg isoMsg = ISOParser.createEchoISO(requestMessage);

Create Void ISO Message :
ISOMsg isoMsg = ISOParser.createVoidISO(requestMessage);

Create Reversal ISO Message :
ISOMsg isoMsg = ISOParser.createReversalISO(requestMessage);

Create Void Reversal ISO Message :
ISOMsg isoMsg = ISOParser.createVoidReversalISO(requestMessage);

Create Sale Request from ISO Message :
RequestMessage reqMessage = (RequestMessage) ISOParser.saleISOToObject(RequestMessage.class, isoMsg);

Create Echo Request from ISO Message :
RequestMessage reqMessage = (RequestMessage) ISOParser.echoISOToObject(RequestMessage.class, isoMsg);

Create Void Request from ISO Message :
RequestMessage reqMessage = (RequestMessage) ISOParser.voidISOToObject(RequestMessage.class, isoMsg);

Create Reversal Request from ISO Message :
RequestMessage reqMessage = (RequestMessage) ISOParser.reversalISOToObject(RequestMessage.class, isoMsg);

Create Void Reversal Request from ISO Message :
RequestMessage reqMessage = (RequestMessage) ISOParser.voidReversalISOToObject(RequestMessage.class, isoMsg);

Convert ISO Message to Sale Response :
RequestMessage reqMessage = (RequestMessage) ISOParser.isoToObject(RequestMessage.class, isoMsg, CommandType.SALE_RESPONSE);
CommandType => SALE_RESPONSE, ECHO_RESPONSE, VOID_RESPONSE, REVERSAL_RESPONSE, VOID_REVERSAL_RESPONSE
