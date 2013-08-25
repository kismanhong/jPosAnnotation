package com.softtech.hong;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.softtech.jpos.enumer.CommandType;
import com.softtech.jpos.exception.JPosAnnotationException;
import com.softtech.jpos.test.model.CardAcceptor;
import com.softtech.jpos.test.model.CardAcceptorInfo;
import com.softtech.jpos.test.model.RequestMessage;
import com.softtech.jpos.utils.ISOParser;

/**
 * @author Kisman Hong
 * jUnit testing for JPos Annotation
 */
public class ISOParserTest {
	
	private RequestMessage requestMessage;
	
	/**
	 * making the request object, just an example
	 */
	@Before
	public void before(){
		this.requestMessage = createRequestMessage();
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * calling createSaleISO, see {@link RequestMessage}, 
	 * @Sale tagged to {@link CardAcceptor (cardNo, cardExpire, cvv, {@link CardAcceptorInfo} (test))}, processingCode, amount, traceNumber, posEntryMode,
	 *  	networkInternationalId, posConditionCode, terminalId, acceptorId
	 */
	@Test
	public void testCreateSaleISO() throws JPosAnnotationException, ISOException{
		String message = ISOUtil.hexString(ISOParser.createSaleISO(requestMessage).pack());
		System.out.println("Sale ISO Message : " +message);
		Assert.assertNotNull(message);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * convert back the Sale ISO Message to {@link RequestMessage} based on @Sale tag
	 */
	@Test
	public void testPackSaleISOToRequestMessage() throws JPosAnnotationException, ISOException{
		RequestMessage reqMessage = (RequestMessage) ISOParser.saleISOToObject(RequestMessage.class, ISOParser.createSaleISO(requestMessage));
		System.out.println("Sale ISO to Request Message : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * calling createEchoISO, see {@link RequestMessage}, 
	 * @Echo tagged to processingCode only, this is an example only
	 */
	@Test
	public void testCreateEchoISO() throws JPosAnnotationException, ISOException{
		String message = ISOUtil.hexString(ISOParser.createEchoISO(requestMessage).pack());
		System.out.println("Echo ISO Message : " +message);
		Assert.assertNotNull(message);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * {@link RequestMessage}
	 */
	@Test
	public void testPackEchoISOToRequestMessage() throws JPosAnnotationException, ISOException{
		RequestMessage reqMessage = (RequestMessage) ISOParser.echoISOToObject(RequestMessage.class, ISOParser.createEchoISO(requestMessage));
		System.out.println("Echo ISO to Request Message : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage.getProcessingCode()); // I just put @Echo to processingCode, see com.softtech.jpos.test.model.RequestMessage
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * calling createVoidISO, see {@link RequestMessage}, 
	 * @Echo tagged to traceNumber only, this is an example only
	 */
	@Test
	public void testCreateVoidISO() throws JPosAnnotationException, ISOException{
		String message = ISOUtil.hexString(ISOParser.createVoidISO(requestMessage).pack());
		System.out.println("Void ISO Message : " +message);
		Assert.assertNotNull(message);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * {@link RequestMessage}
	 */
	@Test
	public void testPackVoidISOToRequestMessage() throws JPosAnnotationException, ISOException{
		RequestMessage reqMessage = (RequestMessage) ISOParser.voidISOToObject(RequestMessage.class, ISOParser.createVoidISO(requestMessage));
		System.out.println("Void ISO to Request Message : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage.getTraceNumber()); // I just put @Void to traceNumber, see com.softtech.jpos.test.model.RequestMessage
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * calling createReversalISO, see {@link RequestMessage}, 
	 * @Reversal tagged to terminalId only, this is an example only
	 */
	@Test
	public void testCreateReversalISO() throws JPosAnnotationException, ISOException{
		String message = ISOUtil.hexString(ISOParser.createReversalISO(requestMessage).pack());
		System.out.println("Reversal ISO Message : " +message);
		Assert.assertNotNull(message);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * {@link RequestMessage}
	 */
	@Test
	public void testPackReversalISOToRequestMessage() throws JPosAnnotationException, ISOException{
		RequestMessage reqMessage = (RequestMessage) ISOParser.reversalISOToObject(RequestMessage.class, ISOParser.createReversalISO(requestMessage));
		System.out.println("Reversal ISO to Request Message : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage.getTerminalId()); // I just put @Reversal to terminalId, see com.softtech.jpos.test.model.RequestMessage
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * calling createReversalISO, see {@link RequestMessage}, 
	 * @VoidReversal tagged to acceptorId only, this is an example only
	 */
	@Test
	public void testCreateVoidReversalISO() throws JPosAnnotationException, ISOException{
		String message = ISOUtil.hexString(ISOParser.createVoidReversalISO(requestMessage).pack());
		System.out.println("Void Reversal ISO Message : " +message);
		Assert.assertNotNull(message);
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * {@link RequestMessage}
	 */
	@Test
	public void testPackVoidReversalISOToRequestMessage() throws JPosAnnotationException, ISOException{
		RequestMessage reqMessage = (RequestMessage) ISOParser.voidReversalISOToObject(RequestMessage.class, ISOParser.createVoidReversalISO(requestMessage));
		System.out.println("Void Reversal ISO to Request Message : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage.getAcceptorId()); // I just put @VoidReversal to acceptorId, see com.softtech.jpos.test.model.RequestMessage
	}
	
	/**
	 * @throws JPosAnnotationException
	 * @throws ISOException
	 * {@link RequestMessage}
	 */
	@Test
	public void testISOResponseToRequestMessage() throws JPosAnnotationException, ISOException{
		ISOMsg isoMsg = ISOParser.createSaleISO(requestMessage);
		RequestMessage reqMessage = (RequestMessage) ISOParser.isoToObject(RequestMessage.class, isoMsg, CommandType.SALE_RESPONSE);
		System.out.println("ISO Response Object : "+ToStringBuilder.reflectionToString(reqMessage));
		Assert.assertNotNull(reqMessage.getPosConditionCode()); // I just put @SaleResponse to postConditionCode, see com.softtech.jpos.test.model.RequestMessage
	}
	
	/**
	 * create sample request message to be converted
	 * @return {@link RequestMessage}
	 */
	private RequestMessage createRequestMessage(){
		CardAcceptorInfo cardAcceptorInfo = new CardAcceptorInfo("testing");
		CardAcceptor cardAcceptor = new CardAcceptor("4111111111111111", "1308", "123", cardAcceptorInfo);
		// cardAcceptor, processingCode, amount, traceNumber, posEntryMode, networkInternationalId, posConditionCode, terminalId, acceptorId
		RequestMessage requestMessage = new RequestMessage(cardAcceptor, "000000", 300000L, "000123", "012", "012", "00", "12345678", "123456789012345" );    
		return requestMessage;
	}
}
