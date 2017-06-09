package nju.integration;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.ApplicationContext;

import nju.dao.SaveDao;
import nju.model.CinemaInfo;
import nju.model.MovieInfo;
import nju.model.PlatformInfo;
import nju.type.Platform;

/**
 * IntegrationData->PlatformInfo
 * 同时也管理MovieInfo,CinemaInfo
 * */


public class DataIntegrator {
	
	private SaveDao saveDao;
	
	public void integrator() {
		ApplicationContext context = MyApplicationContextUtil.getContext();
		
//		int i = 0;
//		while(true) {
//			context = MyApplicationContextUtil.getContext();
//			i++;
//			if(i == 100 || context != null) {
//
//				break;
//			}
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		if(context != null) {
//			saveDao = (SaveDao) context.getBean("saveDao");
//			save();
//		}
		save();
	}
	
	private void save() {
		//将数据存档数据库中
		//猫眼
//		(new Thread(new Runnable() {
//			public void run() {
//				save_maoyanData();
//			}
//		})).start();
//		
//		//糯米
//		(new Thread(new Runnable() {
//			public void run() {
//				save_nuomiData();
//			}
//		})).start();
//		
		//时光网
		(new Thread(new Runnable() {
			public void run() {
				save_timeData();
			}
		})).start();		
	}
	
	private void save_maoyanData() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();     
		//猫眼电影信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[0]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
                Element e = iterator.next();  
                Iterator<Element> infos = e.elementIterator();  
                
            	MovieInfo movieInfo = new MovieInfo();
                while(infos.hasNext()) {   	
                	
                	Element info = infos.next();
					switch (info.getName()) {
					case "movie_name":
						movieInfo.setMovie_name(info.getText());
						break;
					case "tag":
						movieInfo.setTag(info.getText());
						break;
					case "country":
						movieInfo.setCountry(info.getText());
						break;
					case "duration":
						movieInfo.setDuration(info.getText());
						break;
					case "score":
						movieInfo.setScore(info.getText());
						break;
					case "movie_img":
						movieInfo.setImg_url(info.getText());
						break;
					case "platform":
						movieInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);

                	}
                }
            	//saveDao.updateMovie(movieInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[1]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				CinemaInfo cinemaInfo = new CinemaInfo();
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "cinema_name":
						cinemaInfo.setCinema_name(info.getText());
						break;
					case "cinema_address":
						cinemaInfo.setAddress(info.getText());
						break;

					}
				}
            	//saveDao.updateCinema(cinemaInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[2]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				PlatformInfo platformInfo = new PlatformInfo();
				String movieName = "";
				String cinemaName = "";
				
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "movie_name":
						movieName = info.getText();
						break;
					case "cinema_address":
						cinemaName = info.getText();
						break;
					case "date":
						platformInfo.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(info.getText()).getTime()));
						break;
					case "price":
						platformInfo.setPrice(info.getText());
						break;
					case "platform":
						platformInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);
						break;
					}
				}
//            	saveDao.updateBuyInfo(platformInfo, movieName, cinemaName);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	private void save_nuomiData() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();     
		//猫眼电影信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[3]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
                Element e = iterator.next();  
                Iterator<Element> infos = e.elementIterator();  
                
            	MovieInfo movieInfo = new MovieInfo();
                while(infos.hasNext()) {   	
                	
                	Element info = infos.next();
					switch (info.getName()) {
					case "movie_name":
						movieInfo.setMovie_name(info.getText());
						break;
					case "tag":
						movieInfo.setTag(info.getText());
						break;
					case "country":
						movieInfo.setCountry(info.getText());
						break;
					case "duration":
						movieInfo.setDuration(info.getText());
						break;
					case "score":
						movieInfo.setScore(info.getText());
						break;
					case "movie_img":
						movieInfo.setImg_url(info.getText());
						break;
					case "platform":
						movieInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);

                	}
                }
            	//saveDao.updateMovie(movieInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[4]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				CinemaInfo cinemaInfo = new CinemaInfo();
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "cinema_name":
						cinemaInfo.setCinema_name(info.getText());
						break;
					case "cinema_address":
						cinemaInfo.setAddress(info.getText());
						break;

					}
				}
            	//saveDao.updateCinema(cinemaInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[5]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				PlatformInfo platformInfo = new PlatformInfo();
				String movieName = "";
				String cinemaName = "";
				
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "movie_name":
						movieName = info.getText();
						break;
					case "cinema_name":
						cinemaName = info.getText();
						break;
					case "date":
						platformInfo.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(info.getText()).getTime()));
						break;
					case "price":
						platformInfo.setPrice(info.getText());
						break;
					case "platform":
						platformInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);
						break;
					}
				}
//            	saveDao.updateBuyInfo(platformInfo, movieName, cinemaName);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	private void save_timeData() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();     
		//猫眼电影信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[6]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
                Element e = iterator.next();  
                Iterator<Element> infos = e.elementIterator();  
                
            	MovieInfo movieInfo = new MovieInfo();
            	movieInfo.setCountry("");
                while(infos.hasNext()) {   	
                	
                	Element info = infos.next();
					switch (info.getName()) {
					case "name":
						movieInfo.setMovie_name(info.getText());
						break;
					case "category":
						movieInfo.setTag(info.getText());
						break;
					case "timeSpan":
						movieInfo.setDuration(info.getText());
						break;
					case "score":
						movieInfo.setScore(info.getText());
						break;
					case "imgURL":
						movieInfo.setImg_url(info.getText());
						break;
					case "platform":
						movieInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);

                	}
                }
            	//saveDao.updateMovie(movieInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[7]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				CinemaInfo cinemaInfo = new CinemaInfo();
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "name":
						cinemaInfo.setCinema_name(info.getText());
						break;
					case "address":
						cinemaInfo.setAddress(info.getText());
						break;

					}
				}
            	//saveDao.updateCinema(cinemaInfo);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
        //猫眼电影院信息
        try {
            SAXReader reader = new SAXReader();  
            Document document = reader.read(new File(XmlSchemaManager.xmlPath[8]));  
            Element root = document.getRootElement();  
            Iterator<Element> iterator = root.elementIterator();  
            while(iterator.hasNext()){  
				Element e = iterator.next();
				Iterator<Element> infos = e.elementIterator();

				PlatformInfo platformInfo = new PlatformInfo();
				String movieName = "";
				String cinemaName = "";
				while (infos.hasNext()) {

					Element info = infos.next();
					switch (info.getName()) {
					case "filmName":
						movieName = info.getText();
						break;
					case "cinemaName":
						cinemaName = info.getText();
						break;
					case "time":
						Element date_ele = (Element) info.elementIterator().next();
						platformInfo.setDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date_ele.getText()).getTime()));
						break;
					case "cost":
						platformInfo.setPrice(info.getText());
						break;
					case "platform":
						platformInfo.setPlatform(Platform.values()[Integer.parseInt(info.getText())]);
						break;
					}
				}
				
//            	saveDao.updateBuyInfo(platformInfo, movieName, cinemaName);
            }           
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
	}
	public static void main(String[] args) {
		new DataIntegrator().integrator();
	}
}
