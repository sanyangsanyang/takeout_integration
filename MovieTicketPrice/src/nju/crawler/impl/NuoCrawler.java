package nju.crawler.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import nju.crawler.Crawler;
import nju.type.Platform;

public class NuoCrawler extends Crawler {

	// 南京电影票
	private static String MAIN_URL = "https://dianying.nuomi.com/movie/movielist?cityId=315";
	private ChromeDriver dr;
	private Document movie_doc;
	private Element movies;
	
	private Document cinema_doc;
	private Element cinemas;
	
	private Document platform_doc;
	private Element platform_infos;

	public NuoCrawler() {
		super();
		movies = new Element("movies");
	    movie_doc = new Document(movies);
	    
	    cinemas = new Element("cinemas");
	    cinema_doc = new Document(cinemas);
	    
	    platform_infos = new Element("platform_infos");
	    platform_doc = new Document(platform_infos);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		try {
			// 位置，主界面，电影列表，有翻页，用循环处理翻页
			dr = new ChromeDriver();
			dr.get(MAIN_URL);

			dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			while (true) {
				// 翻页动作
				viewFilmPage();
				List<WebElement> page_buttons = dr.findElements(By.xpath("//*[@id='pagerContainer']/div/a"));
				Iterator<WebElement> iterator = page_buttons.iterator();
				boolean hasNext = true;
				while (iterator.hasNext()) {
					if (iterator.next().getAttribute("class").equals("mod-page-item mod-page-item-active")) {
						// 判断如果有下一页就继续读，否则跳出

						if (iterator.hasNext()) {
							// 跳到下一面
							iterator.next().click();
							Thread.sleep(2000);
						} else {
							hasNext = false;
						}
						break;
					}
				}
				if (!hasNext)
					break;
			}

			Thread.sleep(1000);
			dr.close();
			dr.quit();
		} catch (Exception e) {
			// 最外层异常
			e.printStackTrace();
			dr.close();
			dr.quit();
		}

		finished = true;
		
		saveXml();
		
	}

	// 查询一页的电影信息
	private void viewFilmPage() {
		// 点进一个电影，顺便获取该电影的信息 标签，地区，时长 图片 名字
		List<WebElement> film_tags = dr.findElements(By.xpath("//*[@id='pageletMovielist']/div[1]/div"));

		// 对每一部电影
		ChromeDriver movie_dr = new ChromeDriver();
		movie_dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		int i = 0;
		for (WebElement film_tag : film_tags) {
			i++;
			;
			try {
				String name = film_tag.findElement(By.xpath("div[1]/div/p[1]")).getText().split(" ")[0];
				String img_src = film_tag.findElement(By.xpath("div[1]/a/img")).getAttribute("src");

				String tag = film_tag.findElement(By.xpath("div[1]/div/ul/li[1]")).getText().substring(3)
						.replaceAll(",", "/");
				String duration = film_tag.findElement(By.xpath("div[1]/div/ul/li[2]")).getText().substring(3);

				// 进入电影界面
				// 用新窗口进入
				String movie_id = dr.findElement(By.xpath("//*[@id='pageletMovielist']/div[1]/div[" + i + "]/div[2]/a"))
						.getAttribute("data-data");
				movie_id = movie_id.substring(11, movie_id.length() - 1);
				String movie_detail_url = "https://dianying.nuomi.com/movie/detail?cityId=315&movieId=" + movie_id;
				// 读取电影院和平台信息
				movie_dr.get(movie_detail_url);
				

				// 获取最后一个地区信息
				String site = movie_dr.findElement(By.xpath("//*[@id='detailIntro']/div/div[2]/div[2]/p[3]")).getText()
						.split("\\|")[0].trim();
				String info = tag + "-" + site + "-" + duration;
				String score = movie_dr.findElement(By.xpath("//*[@id='detailIntro']/div/div[2]/div[1]/span")).getText();
				save_movie_info(name, info, score, img_src);

				viewCinema(movie_dr, name);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(10000000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
		}

		movie_dr.close();
		movie_dr.quit();
	}

	// 切换日期和获取公共信息（score和movename）
	private void viewCinema(ChromeDriver movie_dr, String movie_name) {
		// movie_dr不用关
		// 3个日期的界面

		

		List<WebElement> data_tags = movie_dr.findElements(By.xpath("//*[@id='dateList']/li"));
		int size = data_tags.size() >= 3 ? 3 : data_tags.size();
		for (int i = 1; i <= size; i++) {
			WebElement date_tag = movie_dr.findElement(By.xpath("//*[@id='dateList']/li[" + i + "]/span"));
			date_tag.click();
			String date_str = date_tag.getText();
			viewData(movie_dr, movie_name,  date_str);
		}

	}

	private void viewData(ChromeDriver movie_dr, String movie_name, String date_str) {
		// 每个当前界面保存电影院和电影信息
		// 点开下拉框知道没有新的电影院信息了
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement more_cinema = movie_dr.findElement(By.id("moreCinema"));

		while (more_cinema.getText().equals("点击查看更多影院  >")) {
			more_cinema = movie_dr.findElement(By.id("moreCinema"));
			while (true) {
				try {
					more_cinema.click();
					Thread.sleep(500);
					break;
				} catch (Exception e) {
					more_cinema = movie_dr.findElement(By.id("moreCinema"));
					continue;
				}
			}
		}

		// 开始爬

		List<WebElement> cinema_price_tags = movie_dr.findElements(By.xpath("//*[@id='pageletCinemalist']/li"));

		String date = translateDateStr(date_str);
		//日期
		for (WebElement info_tag : cinema_price_tags) {			
			// 影院
			String cinema_name = info_tag.findElement(By.xpath("div[1]/p[1]/span")).getText();
			String cinema_address = info_tag.findElement(By.xpath("div[1]/p[2]/span")).getText();
			
			save_cinema_info(cinema_name, cinema_address);
			
			// 平台价格
			String price = "0";
			try{
				price = info_tag.findElement(By.xpath("div[3]/p/em[2]")).getText();
			} catch(Exception e) {
				continue;
			}
			
			save_platform_info(movie_name, cinema_name, date,  price);
		}
	}

	// 保存电影信息xml
	private void save_movie_info(String name, String info,String score, String path) {
		
		Element movie_root = new Element("movie");

		Element name_ele = new Element("movie_name");
		Element tag_ele = new Element("tag");
		Element site_ele = new Element("country");
		Element score_ele = new Element("score");
		Element duration_ele = new Element("duration");
		Element path_ele = new Element("movie_img");
		Element platform_ele = new Element("platform");
		
		name_ele.addContent(name);
		
		String[] infos = info.split("-");
		tag_ele.addContent(infos[0]);
		site_ele.addContent(infos[1]);
		duration_ele.addContent(infos[2]);
		score_ele.addContent(score);
		path_ele.addContent(path);
		platform_ele.addContent(Platform.MAO_YAN.ordinal() + "");
		
		movie_root.addContent(name_ele);
		movie_root.addContent(tag_ele);
		movie_root.addContent(site_ele);
		movie_root.addContent(duration_ele);
		movie_root.addContent(score_ele);
		movie_root.addContent(path_ele);
		movie_root.addContent(platform_ele);	
		
		movies.addContent(movie_root);
		System.out.println("糯米网:" + name + " 信息开始爬取。");
	}

	private void save_cinema_info(String cinema_name, String cinema_address) {
		Element cinema = new Element("cinema");

		Element cinema_name_tag = new Element("cinema_name");
		Element cinema_address_tag = new Element("cinema_address");

		cinema_name_tag.addContent(cinema_name);
		cinema_address_tag.addContent(cinema_address);

		cinema.addContent(cinema_name_tag);
		cinema.addContent(cinema_address_tag);

		cinemas.addContent(cinema);
	}

	private void save_platform_info(String movie_name, String cinema_name, String date, String price_msg_str) {
		Element platform_info = new Element("platform_info");
		
		Element movie_name1 = new Element("movie_name");
		Element cinema_name1 = new Element("cinema_name");
		Element date_tag = new Element("date");
		Element price_tag = new Element("price");
		Element platform_tag = new Element("platform");
		
		movie_name1.addContent(movie_name);
		cinema_name1.addContent("cinema_name");
		date_tag.addContent(date);
		price_tag.addContent(price_msg_str);
		platform_tag.addContent(Platform.MAO_YAN.ordinal() + "");
		
		platform_info.addContent(movie_name1);
		platform_info.addContent(cinema_name1);
		platform_info.addContent(date_tag);
		platform_info.addContent(price_tag);
		platform_info.addContent(platform_tag);
		
		platform_infos.addContent(platform_info);
	}
	
	private String translateDateStr(String dateStr) {
		String[] dates = dateStr.trim().substring(2).replace('月', ' ').replace('日', ' ').split(" ");
		String month = dates[0];
		String day = dates[1];
		
		if(month.length() == 1) {
			month = "0" + month;
		} 
		
		if(day.length() == 1) {
			day = "0" + day;
		}
		
		return "2017-" + month + "-" + day;
	}
	
	private void saveXml() {
		String movieXmlPath = "D:\\workspace\\xmls\\nuomi\\nuomi_movie.xml";
		String cinemaXmlPath = "D:\\workspace\\xmls\\nuomi\\nuomi_cinema.xml";
		String platformXmlPath = "D:\\workspace\\xmls\\nuomi\\nuomi_buyinfo.xml";
		//电影
		{
			Format format = Format.getPrettyFormat();
			// 设置xml的编码
			format.setEncoding("utf-8");
			XMLOutputter out = new XMLOutputter(format);
			try {
				out.output(movie_doc, new FileOutputStream(new File(movieXmlPath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//电影院
		{
			Format format = Format.getPrettyFormat();
			// 设置xml的编码
			format.setEncoding("utf-8");
			XMLOutputter out = new XMLOutputter(format);
			try {
				out.output(cinema_doc, new FileOutputStream(new File(cinemaXmlPath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//平台信息
		{
			Format format = Format.getPrettyFormat();
			// 设置xml的编码
			format.setEncoding("utf-8");
			XMLOutputter out = new XMLOutputter(format);
			try {
				out.output(platform_doc, new FileOutputStream(new File(platformXmlPath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	public static void main(String[] args) {
		(new NuoCrawler()).start();
	}

}
