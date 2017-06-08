package nju.crawler;


/**
 * 每个爬虫开自己的新线程爬,爬完后将finished设为true
 * 爬数据时对比数据库中的数据来爬，只有新的数据才调用manager.add方法来添加html
 * */
public abstract class Crawler {
	public boolean finished;
	public Crawler() {
		finished = false;
	}

	public void crawling(CrawlerManager manager) {
		// TODO Auto-generated method stub
		(new Thread(new Runnable() {
			public void run() {
				start(manager);
			}
		})).start();
	}
	
	public abstract void start(CrawlerManager manager);
}
