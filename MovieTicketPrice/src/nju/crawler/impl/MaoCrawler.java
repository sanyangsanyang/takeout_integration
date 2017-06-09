package nju.crawler.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import nju.crawler.Crawler;
import nju.crawler.CrawlerManager;
import nju.crawler.tool.ImgGetter;
import nju.type.Platform;
import nju.type.XmlType;
import nju.vo.XmlData;

public class MaoCrawler extends Crawler {
	private static String MAIN_URL = "http://www.meituan.com/dianying/zuixindianying";
	private Document movie_doc;
	private Element movies;
	
	private Document cinema_doc;
	private Element cinemas_ele;
	
	private Document platform_doc;
	private Element platform_infos;
	public MaoCrawler() {
		super();
		movies = new Element("movies");
	    movie_doc = new Document(movies);
	    
	    cinemas_ele = new Element("cinemas");
	    cinema_doc = new Document(cinemas_ele);
	    
	    platform_infos = new Element("platform_infos");
	    platform_doc = new Document(platform_infos);
	}

	@Override
	public void start() {
		ChromeDriver dr = new ChromeDriver();
		dr.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		dr.get(MAIN_URL);
		
		dr.findElement(By.xpath("/html/body/div[last()]/div/div[last()]/div[last()]/span")).click();
		WebElement eleRoot = dr.findElement(By.xpath("//*[@id='content']/div"));
		List<WebElement> eles = eleRoot.findElements(By.className("movie-cell"));


		for (WebElement ele : eles) {

			WebElement ele2 = ele.findElement(By.xpath("div/a"));
	
			ele2.click();

			Set<String> handles = dr.getWindowHandles();
			String currentWindow = dr.getWindowHandle();
			// 排除当前窗口的句柄，则剩下是新窗口
			
			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {
				if (currentWindow == it.next())
					continue;
				
				dr.switchTo().window(it.next());
			}

			
			
			try{

				List<WebElement > date_eles = dr.findElements(By.xpath("//*[@id='filter']/div/div/div[1]/ul/li"));
				
				
				//取三天
				int size = date_eles.size() >= 3 ? 3 : date_eles.size();
				
				Iterator<WebElement> iterator = date_eles.iterator();

				ArrayList<String> date_urls = new ArrayList<String>();
				ArrayList<String> dates = new ArrayList<String>();
				for (int i = 0; i < size; i++) {
					WebElement e = iterator.next();
					 date_urls.add(e.findElement(By.cssSelector("a")).getAttribute("href"));
					 dates.add(e.findElement(By.cssSelector("a")).getAttribute("gaevent").substring(12));
				}
				
				int i = 0;
				String score = "0";
				String name = "";
				Iterator<String> data_i = dates.iterator();
				for(String url1 : date_urls) {
					dr.get(url1);
					String cur_data = data_i.next();
					
					List<WebElement > page_eles = dr.findElements(By.xpath("//*[@id='content']/div[1]/div"));
					ArrayList<String> page_urls = new ArrayList<String>();
					page_urls.add(url1);
					if(page_eles.size() == 4) {
						List<WebElement > page_eles1 = dr.findElements(By.xpath("//*[@id='content']/div[1]/div[last()]/ul/li"));

						for (WebElement w : page_eles1) {
							String cl = w.getAttribute("class");
							if(cl.equals("current")) {
								continue;
							} else if(cl.equals("next")) {
								break;
							} else {
								page_urls.add(w.findElement(By.cssSelector("a")).getAttribute("href"));
							}
						}
					}
					

					
					for(String page_url : page_urls) {
						dr.get(page_url);
						dr.findElement(By.xpath("/html/body/div[last()]/div/div[last()]/div[last()]/span")).click();
						

						//爬当页的数据
						if(i == 0) {
							i++;
							//第一面要爬电影信息(1个)
							//name,info(tag, duration),img_path
							
							WebElement movie_info = dr.findElement(By.xpath("//*[@id='bd']/div[3]"));
							
							//获取评分
							WebElement score_container = movie_info.findElement(By.xpath("div[1]/div[2]"));
							if(score_container.findElement(By.xpath("span")).getAttribute("class").equals("movie-wish")) {
								//
							} else {
								score = score_container.findElement(By.xpath("strong")).getText().charAt(0) + score_container.findElement(By.xpath("strong/strong")).getText();
							}
							
							//获取名字
							name = movie_info.findElement(By.xpath("div[2]/div[1]/div[2]/h2")).getText();
							
							
							//获取 标签，地区，时长 
							String tag = movie_info.findElement(By.xpath("div[2]/div[1]/section/dl/dd[2]")).getText().replaceAll(",", "/");
							String site = movie_info.findElement(By.xpath("div[2]/div[1]/section/dl/dd[4]")).getText();
							String duration = movie_info.findElement(By.xpath("div[2]/div[1]/section/dl/dd[5]")).getText();
							String info = tag + "-" + site + "-" + duration;
							
							//获取图片并保存本地路径
							String img_url = movie_info.findElement(By.xpath("div[2]/div[1]/div[1]/img")).getAttribute("src");
							

							
							//生成xmldata
							
//							System.out.println("name:" + name + "\n"
//									+ "info:" + info + "\n"
//											+ "path:" + path + "\n\n");
							Element movie_root = new Element("movie");
							
							Element name_ele = new Element("movie_name");
							Element tag_ele = new Element("tag");
							Element site_ele = new Element("country");
							Element duration_ele = new Element("duration");
							Element score_ele = new Element("score");
							Element path_ele = new Element("movie_img");
							Element platform_ele = new Element("platform");
							
							
							name_ele.addContent(name);
							String[] infos = info.split("-");
							tag_ele.addContent(infos[0]);
							site_ele.addContent(infos[1]);
							duration_ele.addContent(infos[2]);
							score_ele.addContent(score);
							path_ele.addContent(img_url);
							platform_ele.addContent(Platform.MAO_YAN.ordinal() + "");

							movie_root.addContent(name_ele);
							movie_root.addContent(tag_ele);
							movie_root.addContent(site_ele);
							movie_root.addContent(duration_ele);
							movie_root.addContent(score_ele);
							movie_root.addContent(path_ele);
							movie_root.addContent(platform_ele);
							
							movies.addContent(movie_root);
							System.out.println("猫眼网:" + name + " 信息开始爬取。");
						}
						
						//每个界面爬 电影院信息和详细信息(n个),价格信息(n个)保存为xml格式
						//平台信息 
						//电影院列表
						List<WebElement> cinemas = dr.findElements(By.xpath("//*[@id='J-cinema-info-list']/div"));
						
						try{
						for(WebElement cinema_info : cinemas) {
							//获取分数 price,data(哪天的票),platform,score,movie_name,cinema_name
							String cinema_name = cinema_info.findElement(By.xpath("div[1]/h4/a")).getText();
							String cinema_address=cinema_info.findElement(By.xpath("div[1]/dl[1]/dd[1]")).getText();
							
//							System.out.println("cinema:"+ cinema_name + "\n"
//									+ "address:" + cinema_address + "\n\n");
							
							Element cinema = new Element("cinema");
							
							Element cinema_name_tag = new Element("cinema_name");
							Element cinema_address_tag = new Element("cinema_address");
							
							cinema_name_tag.addContent(cinema_name);
							cinema_address_tag.addContent(cinema_address);
							
							cinema.addContent(cinema_name_tag);
							cinema.addContent(cinema_address_tag);
							
							cinemas_ele.addContent(cinema);
							
							String movie_name = name;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
							Date date = new Date(sdf.parse(cur_data).getTime());
							
							
							//获取price
							
							String price_msg_str = "";
							List<WebElement> price_msg = cinema_info.findElements(By.xpath("div[3]/div[2]/p/em/i"));
								for (WebElement i_tag : price_msg) {
									if (!price_msg_str.equals("")) {
										price_msg_str += "+";
									}

									String img_msg = i_tag.getAttribute("style");

									String regex = "s0.*\\)"; //
									Pattern pattern = Pattern.compile(regex);
									Matcher matcher = pattern.matcher(img_msg);

									matcher.find();
									String img_url = matcher.group(0);

									String result = "http://" + img_url.substring(0, img_url.length() - 2);
									String path = "maoyan_digit\\" + Calendar.getInstance().getTimeInMillis() + ".jpg";

									price_msg_str += path;

									ImgGetter.get(result, path);

									String regex1 = "on:\\s.*px"; //
									Pattern pattern1 = Pattern.compile(regex1);
									Matcher matcher1 = pattern1.matcher(img_msg);
									matcher1.find();
									String pos = matcher1.group(0);
									price_msg_str += "+" + pos.substring(4);
								}

								
								// System.out.println("movie:" + movie_name +
								// "\n"
								// + "score:"+movie_score +"\n"
								// + "date:"+(new
								// SimpleDateFormat("yyyy-MM-dd").format(date))
								// + "\n"
								// + "platform:" + "猫眼\n"
								// + "cinema_name:" + cinema_name + "\n");
								// + "price:" + price_msg_str + "\n\n");
							
								Element platform_info = new Element("platform_info");
								
								Element movie_name1 = new Element("movie_name");
								Element cinema_name1 = new Element("cinema_name");
								Element date_tag = new Element("date");
								Element price_tag = new Element("price");
								Element platform_tag = new Element("platform");
								
								movie_name1.addContent(movie_name);
								cinema_name1.addContent(cinema_name);
								date_tag.addContent((new SimpleDateFormat("yyyy-MM-dd")).format(date));
								price_tag.addContent(price_msg_str);
								platform_tag.addContent(Platform.MAO_YAN.ordinal() + "");
								
								platform_info.addContent(movie_name1);
								platform_info.addContent(cinema_name1);
								platform_info.addContent(date_tag);
								platform_info.addContent(price_tag);
								platform_info.addContent(platform_tag);
								
								platform_infos.addContent(platform_info);
							}
							
						} catch (Exception e) {

							e.printStackTrace();
							Thread.sleep(100000);
							continue;
						}
					}				
				}
				
				
				

			} catch (Exception e) {
				
				e.printStackTrace();
			}

			dr.close();
			dr.switchTo().window(currentWindow);
			
		}
		
		dr.close();
		dr.quit();
		finished = true;
		
		saveXml();
	}
	private void saveXml() {
		String movieXmlPath = "D:\\workspace\\xmls\\maoyan\\maoyan_movie.xml";
		String cinemaXmlPath = "D:\\workspace\\xmls\\maoyan\\maoyan_cinema.xml";
		String platformXmlPath = "D:\\workspace\\xmls\\maoyan\\maoyan_buyinfo.xml";
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
		(new MaoCrawler()).start();
	}
}
