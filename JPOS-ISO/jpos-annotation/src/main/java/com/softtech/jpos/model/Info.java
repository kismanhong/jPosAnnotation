package com.softtech.jpos.model;

import java.util.ArrayList;
import java.util.List;

import com.softtech.jpos.enumer.PadType;

/**
 * @author Kisman Hong
 * class for collecting field info
 */
public class Info {
	
	private String fieldName;
	
	private int fldNo;
	
	private PadType padType;
	
	private char padCharacter;
	
	private int padLength;
	
	private List<Info> infos = new ArrayList<Info>();
	
	public Info(String fieldName) {
		super();
		this.fieldName = fieldName;
	}

	public Info(String fieldName, int fldNo,
			PadType padType, char padCharacter, int padLength) {
		super();
		this.fieldName = fieldName;
		this.fldNo = fldNo;
		this.padType = padType;
		this.padCharacter = padCharacter;
		this.padLength = padLength;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFldNo() {
		return fldNo;
	}

	public void setFldNo(int fldNo) {
		this.fldNo = fldNo;
	}

	public PadType getPadType() {
		return padType;
	}

	public void setPadType(PadType padType) {
		this.padType = padType;
	}

	public char getPadCharacter() {
		return padCharacter;
	}

	public void setPadCharacter(char padCharacter) {
		this.padCharacter = padCharacter;
	}

	public int getPadLength() {
		return padLength;
	}

	public void setPadLength(int padLength) {
		this.padLength = padLength;
	}

	public List<Info> getInfos() {
		return infos;
	}

	public void setInfos(List<Info> infos) {
		this.infos = infos;
	}	
	
	public void addInfo(Info info){
		this.infos.add(info);
	}
	
}
