package nju.integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * 供DataIntegrator调用来验证IntegrationData
 * */
public class XmlSchemaManager {
	public static String[] xsdPath = new String[]{
			"D:\\workspace\\xsds\\maoyan\\maoyan_movie.xsd",
			"D:\\workspace\\xsds\\maoyan\\maoyan_cinema.xsd",
			"D:\\workspace\\xsds\\maoyan\\maoyan_buyinfo.xsd",
			"D:\\workspace\\xsds\\nuomi\\nuomi_movie.xsd",
			"D:\\workspace\\xsds\\nuomi\\nuomi_cinema.xsd",
			"D:\\workspace\\xsds\\nuomi\\nuomi_buyinfo.xsd",
			"D:\\workspace\\xsds\\time\\time_movie.xsd",
			"D:\\workspace\\xsds\\time\\time_cinema.xsd",
			"D:\\workspace\\xsds\\time\\time_buyinfo.xsd",
	};
	
	public static String[] xmlPath = new String[]{
			"D:\\workspace\\xmls\\maoyan\\maoyan_movie.xml",
			"D:\\workspace\\xmls\\maoyan\\maoyan_cinema.xml",
			"D:\\workspace\\xmls\\maoyan\\maoyan_buyinfo.xml",
			"D:\\workspace\\xmls\\nuomi\\nuomi_movie.xml",
			"D:\\workspace\\xmls\\nuomi\\nuomi_cinema.xml",
			"D:\\workspace\\xmls\\nuomi\\nuomi_buyinfo.xml",
			"D:\\workspace\\xmls\\time\\mtime_filmInfo.xml",
			"D:\\workspace\\xmls\\time\\mtime_cinemaInfo.xml",
			"D:\\workspace\\xmls\\time\\mtime_buyInfo.xml",
	};
	
	private SchemaFactory schemaFactory;
	public XmlSchemaManager() {
		schemaFactory = SchemaFactory
				  .newInstance("http://www.w3.org/2001/XMLSchema");
	}
	public boolean validate() {


		boolean result = true;;
		
		for(int i = 0; i < xsdPath.length; i++) {
			result = result && validate(xsdPath[i], xmlPath[i]);
		}

		return result;
	}
	
	private boolean validate(String xsdPath, String xmlPath) {
		System.out.println("验证:" + xsdPath + "-" + xmlPath);
		try {
			File schemaFile = new File(xsdPath);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(xmlPath);
			validator.validate(source);
		} catch (Exception e) {
			//记录错误信息
			
			File log = new File("D:\\workspace\\xmls\\log.txt");
			try {
				PrintWriter pw = new PrintWriter(log);
				pw.write("验证失败：错误信息如下\n");
				pw.write(e.getMessage());
				pw.close();
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println((new XmlSchemaManager()).validate());

	}
}

