package nju.vo;

import java.util.ArrayList;

import nju.type.Platform;

public class HtmlData {

	private String url;
	private Platform platform;
	private ArrayList<String> xml_data;
	
	public HtmlData(String url, Platform platform, ArrayList<String>xml_data) {
		this.url = url;
		this.platform = platform;
		this.xml_data = xml_data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public ArrayList<String> getXml_data() {
		return xml_data;
	}

	public void setXml_data(ArrayList<String> xml_data) {
		this.xml_data = xml_data;
	}
}
