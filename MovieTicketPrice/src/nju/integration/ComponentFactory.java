package nju.integration;

import nju.crawler.CrawlerManager;

public class ComponentFactory {

	private static CrawlerManager crawlerManager;
	private static XsltTranslator xsltTranslatro;
	private static XmlSchemaManager xmlSchemaManager;
	private static DataIntegrator dataIntegrator;
	private static WorkLog crawlerLog;
	
	public static CrawlerManager getCrawlerManager() {
		if(crawlerManager == null) {
			crawlerManager = new CrawlerManager();
		}
		
		return crawlerManager;
	}
	
	public static XsltTranslator getXsltTranslatro() {
		
		if(xsltTranslatro == null) {
			xsltTranslatro = new XsltTranslator();
		}
		
		return xsltTranslatro;
	}

	public static XmlSchemaManager getXmlSchemaManager() {
		
		if(xmlSchemaManager == null) {
			 xmlSchemaManager = new XmlSchemaManager();
		}
		
		return xmlSchemaManager;
	}

	public static DataIntegrator getDataIntegrator() {
		
		if(dataIntegrator == null) {
			dataIntegrator = new DataIntegrator();
		}
		
		return dataIntegrator;
	}
	
	public static WorkLog getCrawlerLog() {
		if(crawlerLog == null) {
			crawlerLog = new WorkLog();
		}
		
		return crawlerLog;
	}
}
