package nju.integration;

import java.util.ArrayList;

import nju.vo.IntegrationData;
import nju.vo.XmlData;

/**
 * 负责将xml格式的数据用xslt抽取出需要的数据（格式）
 * XmlData->IntegrationData
 * */
public class XsltTranslator {
	public ArrayList<IntegrationData> translate(ArrayList<XmlData> xmlData) {
		return new ArrayList<IntegrationData>();
	}
}
