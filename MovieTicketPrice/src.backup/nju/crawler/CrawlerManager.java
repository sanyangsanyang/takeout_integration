package nju.crawler;

import java.util.ArrayList;

import nju.type.Platform;
import nju.vo.HtmlData;
import nju.vo.XmlData;

//负责管理不同网页爬虫，将结果返回给数据集成层
public class CrawlerManager {
	private ArrayList<XmlData> data;
	
	public CrawlerManager() {
		data = new ArrayList<XmlData>();
	}
	
	public ArrayList<XmlData> getNewData() {
		//
		for(int i = 0; i < CrawlerFactory.NUM_OF_TARGET; i++) {
			Crawler crawler = CrawlerFactory.getCrawler(Platform.values()[i]);
			crawler.crawling(this);
		}
		
		//每间�?5秒判断一次是否读�?
		while(!CrawlerFactory.finish()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return data;
	}
	
	public synchronized void add(XmlData XmlData) {
		data.add(XmlData);
	}
}
