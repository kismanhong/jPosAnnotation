package com.softtech.jpos.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.ISOUtil;

import com.softtech.jpos.annotation.Ignore;
import com.softtech.jpos.annotation.Sale;
import com.softtech.jpos.constants.Constants;
import com.softtech.jpos.enumer.CommandType;
import com.softtech.jpos.enumer.PadType;
import com.softtech.jpos.exception.JPosAnnotationException;
import com.softtech.jpos.model.Info;

/**
 * @author Kisman Hong
 * class for parsing from object to {@link ISOMsg} and vice versa
 */
public class ISOParser {
	
	/**
	 * @param arg
	 * @return {@link ISOMsg}
	 * @throws JPosAnnotationException
	 * tagged your fields in the class with {@link Sale} then calling this method, the {@link ISOMsg} will be constructed
	 */
	public static ISOMsg createSaleISO(Object arg) throws JPosAnnotationException{
		HashMap<String, Object> headInfo = (HashMap<String, Object>) ISOProcessUtils.getHeadInfo(arg.getClass());
		ISOMsg isoMsg = new ISOMsg();
		try {
			isoMsg.setPackager((ISOPackager) headInfo.get(Constants.PACKAGER_CLASS));
			isoMsg.setMTI((String) headInfo.get(Constants.SALE_MTI));
			List<Info> saleInfos = ISOProcessUtils.loadConfigs(arg.getClass(), CommandType.SALE);
			setISOMsg(arg, saleInfos, isoMsg, CommandType.SALE);
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot create SALE ISO, caused by : "+e.getMessage());
		}
		return isoMsg;
	}
	
	/**
	 * @param arg
	 * @return {@link ISOMsg}
	 * @throws JPosAnnotationException
	 * tagged your fields in the class with {@link Echo} then calling this method, the {@link ISOMsg} will be constructed
	 */
	public static ISOMsg createEchoISO(Object arg) throws JPosAnnotationException{
		HashMap<String, Object> headInfo = (HashMap<String, Object>) ISOProcessUtils.getHeadInfo(arg.getClass());
		ISOMsg isoMsg = new ISOMsg();
		try {
			isoMsg.setPackager((ISOPackager) headInfo.get(Constants.PACKAGER_CLASS));
			isoMsg.setMTI((String) headInfo.get(Constants.ECHO_MTI));
			List<Info> echoInfos = ISOProcessUtils.loadConfigs(arg.getClass(), CommandType.ECHO);
			setISOMsg(arg, echoInfos, isoMsg, CommandType.ECHO);
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot create ECHO ISO, caused by : "+e.getMessage());
		}
		return isoMsg;
	}
	
	/**
	 * @param arg
	 * @return {@link ISOMsg}
	 * @throws JPosAnnotationException
	 * tagged your fields in the class with {@link Void} then calling this method, the {@link ISOMsg} will be constructed
	 */
	public static ISOMsg createVoidISO(Object arg) throws JPosAnnotationException{
		HashMap<String, Object> headInfo = (HashMap<String, Object>) ISOProcessUtils.getHeadInfo(arg.getClass());
		ISOMsg isoMsg = new ISOMsg();
		try {
			isoMsg.setPackager((ISOPackager) headInfo.get(Constants.PACKAGER_CLASS));
			isoMsg.setMTI((String) headInfo.get(Constants.VOID_MTI));
			List<Info> voidInfos = ISOProcessUtils.loadConfigs(arg.getClass(), CommandType.VOID);
			setISOMsg(arg, voidInfos, isoMsg, CommandType.VOID);
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot create VOID ISO, caused by : "+e.getMessage());
		}
		return isoMsg;
	}
	
	/**
	 * @param arg
	 * @return {@link ISOMsg}
	 * @throws JPosAnnotationException
	 * tagged your fields in the class with {@link Reversal} then calling this method, the {@link ISOMsg} will be constructed
	 */
	public static ISOMsg createReversalISO(Object arg) throws JPosAnnotationException{
		HashMap<String, Object> headInfo = (HashMap<String, Object>) ISOProcessUtils.getHeadInfo(arg.getClass());
		ISOMsg isoMsg = new ISOMsg();
		try {
			isoMsg.setPackager((ISOPackager) headInfo.get(Constants.PACKAGER_CLASS));
			isoMsg.setMTI((String) headInfo.get(Constants.REVERSAL_MTI));
			List<Info> reversalInfos = ISOProcessUtils.loadConfigs(arg.getClass(), CommandType.REVERSAL);
			setISOMsg(arg, reversalInfos, isoMsg, CommandType.REVERSAL);
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot create REVERSAL ISO, caused by : "+e.getMessage());
		}
		return isoMsg;
	}
	
	/**
	 * @param arg
	 * @return {@link ISOMsg}
	 * @throws JPosAnnotationException
	 * tagged your fields in the class with {@link VoidReversal} then calling this method, the {@link ISOMsg} will be constructed
	 */
	public static ISOMsg createVoidReversalISO(Object arg) throws JPosAnnotationException{
		HashMap<String, Object> headInfo = (HashMap<String, Object>) ISOProcessUtils.getHeadInfo(arg.getClass());
		ISOMsg isoMsg = new ISOMsg();
		try {
			isoMsg.setPackager((ISOPackager) headInfo.get(Constants.PACKAGER_CLASS));
			isoMsg.setMTI((String) headInfo.get(Constants.VOID_REVERSAL_MTI));
			List<Info> reversalInfos = ISOProcessUtils.loadConfigs(arg.getClass(), CommandType.VOID_REVERSAL);
			setISOMsg(arg, reversalInfos, isoMsg, CommandType.VOID_REVERSAL);
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot create REVERSAL ISO, caused by : "+e.getMessage());
		}
		return isoMsg;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert Sale {@link ISOMsg} to {@link Object} based on {@link Sale} tag in class
	 */
	public static Object saleISOToObject(Class<?> clazz, ISOMsg isoMsg) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, CommandType.SALE);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage() + ", please check your constractor, " +
					"you need to define empty constructor");
		}
		setObject(object, infos, isoMsg, CommandType.SALE);
		return object;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert Echo {@link ISOMsg} to {@link Object} based on {@link Echo} tag in class
	 */
	public static Object echoISOToObject(Class<?> clazz, ISOMsg isoMsg) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, CommandType.ECHO);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage());
		}
		setObject(object, infos, isoMsg, CommandType.ECHO);
		return object;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert Void {@link ISOMsg} to {@link Object} based on {@link Void} tag in class
	 */
	public static Object voidISOToObject(Class<?> clazz, ISOMsg isoMsg) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, CommandType.VOID);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage());
		}
		setObject(object, infos, isoMsg, CommandType.VOID);
		return object;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert Reversal {@link ISOMsg} to {@link Object} based on {@link Reversal} tag in class
	 */
	public static Object reversalISOToObject(Class<?> clazz, ISOMsg isoMsg) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, CommandType.REVERSAL);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage());
		}
		setObject(object, infos, isoMsg, CommandType.REVERSAL);
		return object;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert VoidReversal {@link ISOMsg} to {@link Object} based on {@link VoidReversal} tag in class
	 */
	public static Object voidReversalISOToObject(Class<?> clazz, ISOMsg isoMsg) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, CommandType.VOID_REVERSAL);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage());
		}
		setObject(object, infos, isoMsg, CommandType.VOID_REVERSAL);
		return object;
	}
	
	/**
	 * @param clazz
	 * @param isoMsg
	 * @return {@link Object}
	 * @throws JPosAnnotationException
	 * convert {@link ISOMsg} to {@link Object} based on {@link CommandType}
	 */
	public static Object isoToObject(Class<?> clazz, ISOMsg isoMsg, CommandType commandType) throws JPosAnnotationException{
		List<Info> infos = ISOProcessUtils.loadConfigs(clazz, commandType);
		Object object;
		try {
			object = clazz.newInstance();
		} catch (Exception e) {
			throw new JPosAnnotationException("Cannot instatiate class : "+clazz.getCanonicalName() + " caused by : "+e.getMessage());
		}
		setObject(object, infos, isoMsg, commandType);
		return object;
	}
		
	/**
	 * @param arg
	 * @param infos
	 * @param isoMsg
	 * @throws JPosAnnotationException
	 * setting {@link ISOMsg} to every fields based on tag decalaration
	 */
	private static void setISOMsg( Object arg, List<Info> infos, ISOMsg isoMsg, CommandType commandType) throws JPosAnnotationException{
		for (Info info : infos) {
			if(info.getInfos().size() == 0 && info.getPadType() != null){
				try {
					Object value = MethodUtils.invokeMethod(arg, ReflectionUtils.getGetMethodName(info.getFieldName()));
					isoMsg.set(info.getFldNo(), valueExamination(info, value.toString()));
				} catch (Exception e) {
					throw new JPosAnnotationException("Something happened at field "+info.getFieldName() +
							", can be null or there is no field tagged by " +commandType.getString() + ", message : "+e.getMessage());
				} 
			}else{
				try {
					setISOMsg(MethodUtils.invokeMethod(arg, ReflectionUtils.getGetMethodName(info.getFieldName())), info.getInfos(), isoMsg, commandType);
				} catch (Exception e) {
					throw new JPosAnnotationException("Something happened when setISOMsg, field "+info.getFieldName()+" seem to be undefine well / maybe null, " +
							"caused by : "+e.getMessage());
				}
			}
		}
	}
	
	/**
	 * @param info
	 * @param value
	 * @return {@link String}
	 * @throws ISOException
	 * checking padding and converted based on {@link PadType}
	 */
	private static String valueExamination(Info info, String value) throws ISOException{
		switch (info.getPadType()) {
			case ZERO:
				value = ISOUtil.zeropad(value, info.getPadLength());
				break;
			case PAD_LEFT:
				value = ISOUtil.padleft(value, info.getPadLength(), info.getPadCharacter());
				break;
			case PAD_RIGHT:
				value = ISOUtil.padright(value, info.getPadLength(), info.getPadCharacter());
				break;
			default:
				break;
		}
		return value;
	}
	
	/**
	 * @param object
	 * @param infos
	 * @param isoMsg
	 * @throws JPosAnnotationException
	 * helper method for set value to object
	 */
	private static void setObject( Object object, List<Info> infos, ISOMsg isoMsg, CommandType commandType) throws JPosAnnotationException{
		try {
			for (Info info : infos) {
				Field field = object.getClass().getDeclaredField(info.getFieldName());
				if(field.getAnnotation(Ignore.class) == null){
					field.setAccessible(true);
								
					if(info.getInfos().size() == 0 && info.getPadType() != null){
						try {
							setValue(object, field, isoMsg.getValue(info.getFldNo()), info.getFldNo());
						} catch (Exception e) {
							throw new JPosAnnotationException(e);
						} 
					}else{
						try {
							Object fieldClass = field.getType().newInstance();
							setObject(fieldClass, info.getInfos(), isoMsg, commandType);
						} catch (Exception e) {
							throw new JPosAnnotationException("Something happened when setObject, field "+info.getFieldName()+" seem to be undefine well / maybe null, " +
									"caused by : "+e.getMessage()+ ", please check your constractor, you need to define empty constructor");
						}
					}
				}
				
			}
		} catch (Exception e) {
			throw new JPosAnnotationException(
					"Cannot convert sale ISO to Class : " + object.getClass().getCanonicalName() + " caused by : " + e.getMessage());
		}
	}
	
	/**
	 * @param object
	 * @param field
	 * @param value
	 * @param fieldNo
	 * @throws JPosAnnotationException
	 * setting value execution, based on field's type
	 */
	private static void setValue(Object object, Field field, Object value, int fieldNo) throws JPosAnnotationException{
		Class<?> type = field.getType();
		try {			
			Method method = object.getClass().getDeclaredMethod(ReflectionUtils.getSetMethodName(field.getName()), type);
			if (ClassUtils.isAssignable(type, String.class) || type.isEnum()){
				method.invoke(object, value);
			} else if (ClassUtils.isAssignable(type, Integer.class)){
				method.invoke(object, new Integer(value.toString()));
			} else if (ClassUtils.isAssignable(type, Long.class)){				
				method.invoke(object, new Long(value.toString()));
			} else if (ClassUtils.isAssignable(type, Float.class)){
				method.invoke(object, new Float(value.toString()));
			} else if (ClassUtils.isAssignable(type, Double.class)){
				method.invoke(object, new Double(value.toString()));
			} else if (ClassUtils.isAssignable(type, Boolean.class)){
				method.invoke(object, new Boolean(value.toString()));
			} else if (ClassUtils.isAssignable(type, Byte.class)){
				method.invoke(object, new Byte(value.toString()));
			} else if (ClassUtils.isAssignable(type, Short.class)){
				method.invoke(object, new Short(value.toString()));
			} else{
				method.invoke(object, value);
			}
		} catch (Exception e) {
			throw new JPosAnnotationException("Problem when setting value to "+ ReflectionUtils.getSetMethodName(field.getName() + ", parameterType = "
					+type.getCanonicalName() + ", ISO Msg Field No = "+fieldNo +", caused by : "+e.getMessage()));
		} 
	}
}
