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
