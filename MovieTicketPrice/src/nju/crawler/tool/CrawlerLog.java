package nju.crawler.tool;

public class CrawlerLog {
	public static void log(String msg) {
		//向爬取中写入一条记录，格式为  当前时间:msg
		System.out.println(msg);
	}
}
