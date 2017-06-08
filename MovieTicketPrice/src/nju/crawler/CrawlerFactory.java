package nju.crawler;

import nju.crawler.impl.MaoCrawler;
import nju.crawler.impl.NuoCrawler;
import nju.crawler.impl.TimeCrawler;
import nju.type.Platform;

public class CrawlerFactory {
	public static int NUM_OF_TARGET = 3;

	private static Crawler timeCrawler = new TimeCrawler();
	private static Crawler maoCrawler = new MaoCrawler();
	private static Crawler nuoCrawler = new NuoCrawler();
	
	private static Crawler[] crawlers = new Crawler[]{timeCrawler, maoCrawler, nuoCrawler};
	
	public static Crawler getCrawler(Platform platform) {
		switch(platform) {
		case TIME_NET:return getTimeCrawler();
		case MAO_YAN:return getMaoCrawler();
		case NUO_MI:return getNuoCrawler();
		}
		return null;
	}
	
	public static boolean finish() {
		boolean result = true;
		for(int i = 0; i < NUM_OF_TARGET; i++) {
			result = result && crawlers[i].finished;
		}
		return result;
	}


	public static Crawler getTimeCrawler() {
		if(timeCrawler == null) {
			timeCrawler = new TimeCrawler();
		}
		return timeCrawler;
	}

	public static Crawler getMaoCrawler() {
		if(maoCrawler == null) {
			maoCrawler = new MaoCrawler();
		}
		return maoCrawler;
	}

	public static Crawler getNuoCrawler() {
		if(nuoCrawler == null) {
			nuoCrawler = new NuoCrawler();
		}
		return nuoCrawler;
	}

}
