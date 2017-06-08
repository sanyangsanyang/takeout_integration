package nju.integration;

import java.util.ArrayList;

import nju.crawler.CrawlerManager;
import nju.vo.HtmlData;
import nju.vo.IntegrationData;
import nju.vo.XmlData;

public class ThreadManager extends Thread {
	private static boolean isRunning;

	public ThreadManager() {
		isRunning = false;
	}

	@Override
	public void run() {
		while (true) {
			if (isRunning || isInterrupted())
				return;

			isRunning = true;
			
			// 爬一次数据
			execute();
			try {
				Thread.sleep(3600000); // 每隔一小时执行一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



			isRunning = false;
		}
	}
	
	private void execute() {
		
		ComponentFactory.getCrawlerLog().log("开始爬取数据.");
		
		//调用HtmlTranslatro将html界面转换为标准的xml格式
		ArrayList<XmlData> xmlData = ComponentFactory.getCrawlerManager().getNewData();
		
		//调用XstlTranslatro从xml中抽取出有用的数据，依然为xml格式
		ArrayList<IntegrationData> integrationData = ComponentFactory.getXsltTranslatro().translate(xmlData);
		
		//验证数据
		ArrayList<IntegrationData> validatedData = ComponentFactory.getXmlSchemaManager().validate(integrationData);
		
		//存放数据
		boolean result = ComponentFactory.getDataIntegrator().integrator(validatedData);
		
		//写入更新结果
		if(result) {
			ComponentFactory.getCrawlerLog().log("更新成功.");
		} else {
			ComponentFactory.getCrawlerLog().log("更新失败。");
		}
		
	}
}
