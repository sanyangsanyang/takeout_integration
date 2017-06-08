package nju.crawler.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import nju.crawler.Crawler;
import nju.crawler.CrawlerManager;
import nju.crawler.tool.CrawlerLog;
import nju.crawler.tool.UrlRequest;

public class TaoCrawler extends Crawler{
	private String MAIN_URL = "https://dianying.taobao.com/showList.htm?n_s=new";
	public TaoCrawler() {
		super();
	}

	@Override
	public void start(CrawlerManager manager) {
		// TODO Auto-generated method stub
		String result = UrlRequest.sendRequest(MAIN_URL);
		
		if(result == null){
			CrawlerLog.log("淘票票主界面获取失败，请�?查淘票票主界面url(MainCrawler).");
			finished = true;
			return;
		}
		
		//电影地址
		String regex = "http://dianying.taobao.com/showDetail.htm\\?showId=.{0,6}&amp;n_s=new&source=current";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(result);
		ArrayList<String> filmUrls = new ArrayList<String>();
		while(matcher.find()){
			filmUrls.add(matcher.group());
        }    
		
		try {
			System.out.println(test(filmUrls.get(0)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//接下来获取每部电影的价格信息的html
		for(String filmUrl : filmUrls) {	

		}
	}


	public  String test(String url) throws Exception{
		File pathToFirefoxBinary = new File("D:\\firefox\\firefox.exe");  
		FirefoxBinary firefoxbin = new FirefoxBinary(pathToFirefoxBinary);  
		WebDriver driver = new FirefoxDriver(firefoxbin,null);//这里使用这个构�?�方法�??  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.navigate().to(url);
		
		System.out.println(url);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getPageSource());
		
		return "";
	} 
	
	public static void main(String[] args) {
		(new TaoCrawler()).start(new CrawlerManager());
	}
}
