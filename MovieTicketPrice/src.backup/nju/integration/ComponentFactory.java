package nju.integration;

public class ComponentFactory {

	private static XsltTranslator xsltTranslatro;
	private static XmlSchemaManager xmlSchemaManager;
	private static DataIntegrator dataIntegrator;
	private static CrawlerLog crawlerLog;
	

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
	
	public static CrawlerLog getCrawlerLog() {
		if(crawlerLog == null) {
			crawlerLog = new CrawlerLog();
		}
		
		return crawlerLog;
	}
}
