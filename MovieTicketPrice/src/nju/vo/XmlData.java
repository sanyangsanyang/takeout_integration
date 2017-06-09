package nju.vo;

import nju.type.XmlType;

public class XmlData {
	
	protected XmlType type;
	protected String xmlData;
	
	public XmlData() {
		
	}
	
	public XmlData(XmlType type, String xmlData) {
		this.type = type;
		this.xmlData = xmlData;
	}
	
	public XmlType getType() {
		return type;
	}
	public void setType(XmlType type) {
		this.type = type;
	}
	public String getXmlData() {
		return xmlData;
	}
	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

}
