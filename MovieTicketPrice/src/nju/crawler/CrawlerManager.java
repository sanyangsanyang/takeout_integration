package nju.crawler;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import nju.type.Platform;
import nju.vo.XmlData;

//负责管理不同网页爬虫，将结果返回给数据集成层
public class CrawlerManager {
	
	public void getNewData() {
		//
		for(int i = 0; i < CrawlerFactory.NUM_OF_TARGET; i++) {
			Crawler crawler = CrawlerFactory.getCrawler(Platform.values()[i]);
			crawler.crawling();
		}

		while(!CrawlerFactory.finished()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
