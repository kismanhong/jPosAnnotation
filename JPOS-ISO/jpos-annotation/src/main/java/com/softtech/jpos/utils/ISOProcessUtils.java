package com.softtech.jpos.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.softtech.jpos.annotation.Echo;
import com.softtech.jpos.annotation.EchoResponse;
import com.softtech.jpos.annotation.JPos;
import com.softtech.jpos.annotation.Reversal;
import com.softtech.jpos.annotation.ReversalResponse;
import com.softtech.jpos.annotation.Sale;
import com.softtech.jpos.annotation.Ignore;
import com.softtech.jpos.annotation.SaleResponse;
import com.softtech.jpos.annotation.VoidReversal;
import com.softtech.jpos.annotation.VoidReversalResponse;
import com.softtech.jpos.constants.Constants;
import com.softtech.jpos.enumer.CommandType;
import com.softtech.jpos.exception.JPosAnnotationException;
import com.softtech.jpos.model.Info;

/**
 * @author Kisman Hong
 * Utils class for helping gain information, such as tag info from the class
 */
public class ISOProcessUtils {

	/**
	 * @param clazz
	 * @param commandType
	 * @return List<Info>
	 * getting information from Class based on annotation tag
	 */
	private static List<Info> getConfigs(Class<?> clazz, CommandType commandType){
		List<Info> infos = new ArrayList<Info>();
		collectInfo(infos, clazz, commandType);
		return infos;
	}
	
	/**
	 * @param c	 
	 * @return true | false
	 * checking class type
	 */
	private static boolean isJavaType(Class<?> c) {
		if (c.isPrimitive() || c == Byte.class || c == Short.class || c == Integer.class
				|| c == Long.class || c == Float.class || c == Double.class
				|| c == Boolean.class || c == Character.class || c == String.class) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param infos
	 * @param clazz
	 * @param commandType
	 * getting informations from class based on {@link CommandType}
	 * this method will not be called all the time, if not exists in {@link Constants} isoConfigs (static variable), collectInfo will be called
	 */
	public static void collectInfo(List<Info> infos, Class<?> clazz, CommandType commandType){
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.getAnnotation(Ignore.class) == null){
				Class<?> type = field.getType();
				if(isJavaType(type)){
					if(CommandType.SALE == commandType){
						Sale sale = field.getAnnotation(Sale.class);
						if(sale != null){					
							Info saleInfo = new Info(field.getName(), sale.fldNo(), sale.padType(), sale.padCharacter(), sale.padLength());
							infos.add(saleInfo);
						}
					}else if(CommandType.ECHO == commandType){
						Echo echo = field.getAnnotation(Echo.class);
						if(echo != null){					
							Info echoInfo = new Info(field.getName(), echo.fldNo(), echo.padType(), echo.padCharacter(), echo.padLength());
							infos.add(echoInfo);
						}
					}else if(CommandType.VOID == commandType){
						com.softtech.jpos.annotation.Void vd = field.getAnnotation(com.softtech.jpos.annotation.Void.class);
						if(vd != null){
							Info voidInfo = new Info(field.getName(), vd.fldNo(), vd.padType(), vd.padCharacter(), vd.padLength());
							infos.add(voidInfo);
						}
					}else if(CommandType.REVERSAL == commandType){
						Reversal reversal = field.getAnnotation(Reversal.class);
						if(reversal != null){
							Info reversalInfo = new Info(field.getName(), reversal.fldNo(), reversal.padType(), reversal.padCharacter(), reversal.padLength());
							infos.add(reversalInfo);
						}
					}else if(CommandType.VOID_REVERSAL == commandType){
						VoidReversal voidReversal = field.getAnnotation(VoidReversal.class);
						if(voidReversal != null){
							Info voidReversalInfo = new Info(field.getName(), voidReversal.fldNo(), voidReversal.padType(), voidReversal.padCharacter(), 
									voidReversal.padLength());
							infos.add(voidReversalInfo);
						}
					}if(CommandType.SALE_RESPONSE == commandType){
						SaleResponse sale = field.getAnnotation(SaleResponse.class);
						if(sale != null){					
							Info saleInfo = new Info(field.getName(), sale.fldNo(), sale.padType(), sale.padCharacter(), sale.padLength());
							infos.add(saleInfo);
						}
					}else if(CommandType.ECHO_RESPONSE == commandType){
						EchoResponse echo = field.getAnnotation(EchoResponse.class);
						if(echo != null){					
							Info echoInfo = new Info(field.getName(), echo.fldNo(), echo.padType(), echo.padCharacter(), echo.padLength());
							infos.add(echoInfo);
						}
					}else if(CommandType.VOID_RESPONSE == commandType){
						com.softtech.jpos.annotation.VoidResponse vd = field.getAnnotation(com.softtech.jpos.annotation.VoidResponse.class);
						if(vd != null){
							Info voidInfo = new Info(field.getName(), vd.fldNo(), vd.padType(), vd.padCharacter(), vd.padLength());
							infos.add(voidInfo);
						}
					}else if(CommandType.REVERSAL_RESPONSE == commandType){
						ReversalResponse reversal = field.getAnnotation(ReversalResponse.class);
						if(reversal != null){
							Info reversalInfo = new Info(field.getName(), reversal.fldNo(), reversal.padType(), reversal.padCharacter(), reversal.padLength());
							infos.add(reversalInfo);
						}
					}else if(CommandType.VOID_REVERSAL_RESPONSE == commandType){
						VoidReversalResponse voidReversal = field.getAnnotation(VoidReversalResponse.class);
						if(voidReversal != null){
							Info voidReversalInfo = new Info(field.getName(), voidReversal.fldNo(), voidReversal.padType(), voidReversal.padCharacter(), 
									voidReversal.padLength());
							infos.add(voidReversalInfo);
						}
					}
				}else if("interface java.util.Set".equalsIgnoreCase(type.toString()) || "interface java.util.List".equalsIgnoreCase(type.toString()) || 
						"interface java.util.Map".equalsIgnoreCase(type.toString())){
					Type chieldType = field.getGenericType();   
					    if (chieldType instanceof ParameterizedType) {  
					        ParameterizedType pt = (ParameterizedType) chieldType;  
					        type = (Class<?>) (pt.getActualTypeArguments()[0]); 
					    }  
					Info info = new Info(field.getName());
					info.setFieldName(field.getName());
					infos.add(info);
					collectInfo(info.getInfos(), type, commandType);
				}else{	
					Info info = new Info(field.getName());
					info.setFieldName(field.getName());
					infos.add(info);
					collectInfo(info.getInfos(), type, commandType);
				}
			}
		}
	}
	
	/**
	 * @param clazz
	 * @return {@link HashMap}
	 * @throws JPosAnnotationException
	 * getting header info, such as mti or packager class
	 */
	private static HashMap<String, Object> getHeadConfigs(Class<?> clazz) throws JPosAnnotationException{
		JPos jpos = clazz.getAnnotation(JPos.class);
		HashMap<String, Object> headConfigs = new HashMap<String, Object>();
		if(jpos != null){
			headConfigs.put(Constants.ECHO_MTI, jpos.echoMTI());
			headConfigs.put(Constants.SALE_MTI, jpos.saleMTI());
			headConfigs.put(Constants.REVERSAL_MTI, jpos.reversalMTI());
			headConfigs.put(Constants.VOID_MTI, jpos.voidMTI());
			headConfigs.put(Constants.VOID_REVERSAL_MTI, jpos.voidReversalMTI());
			try {
				headConfigs.put(Constants.PACKAGER_CLASS, jpos.packager().newInstance());
			} catch (Exception e) {
				throw new JPosAnnotationException("Invalid packager @JPos, caused by "+e.getMessage());
			}
		}else{
			throw new JPosAnnotationException("@JPos tag must be declare at "+clazz.getCanonicalName());
		}
		return headConfigs;
	}
	
	/**
	 * @param clazz
	 * @param commandType
	 * @return List<Info>
	 * if infos already exists then get from Constants.isoConfigs, else getConfigs then put it to Constants.isoConfigs
	 */
	public static List<Info> loadConfigs(Class<?> clazz, CommandType commandType){
		@SuppressWarnings("unchecked")
		List<Info> configs = (List<Info>) Constants.isoConfigs.get(clazz.getCanonicalName() + commandType);
		if(configs == null){
			configs = getConfigs(clazz, commandType);
			Constants.isoConfigs.put(clazz.getCanonicalName() + commandType, configs);
		}
		return configs;
	}
	
	/**
	 * @param clazz
	 * @return {@link HashMap}
	 * @throws JPosAnnotationException
	 * Header configuration that declared by {@link JPos}
	 */
	public static HashMap<String, Object> getHeadInfo(Class<?> clazz) throws JPosAnnotationException{
		@SuppressWarnings("unchecked")
		HashMap<String, Object> headInfos = (HashMap<String, Object>) Constants.isoConfigs.get(clazz.getCanonicalName() + Constants.HEAD);
		if(headInfos == null){
			headInfos = getHeadConfigs(clazz);
			Constants.isoConfigs.put(clazz.getCanonicalName() + Constants.HEAD, headInfos);
		}
		return headInfos;
	}
}
