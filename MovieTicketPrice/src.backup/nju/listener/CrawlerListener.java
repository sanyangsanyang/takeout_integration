package nju.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nju.integration.CrawlerThread;

public class CrawlerListener implements ServletContextListener {
	private CrawlerThread myThread;

	public void contextDestroyed(ServletContextEvent sc) {
		if (myThread != null && myThread.isInterrupted()) {
			myThread.interrupt();
		}
	}

	public void contextInitialized(ServletContextEvent sc) {
		String str = null;
		if (str == null && myThread == null) {
			myThread = new CrawlerThread();
			myThread.start(); // servlet 上下文初始化时启�? socket
		}
	}
}
