package nju.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nju.integration.ThreadManager;

public class CrawlerListener implements ServletContextListener {
	private ThreadManager myThread;

	public void contextDestroyed(ServletContextEvent sc) {
		if (myThread != null && myThread.isInterrupted()) {
			myThread.interrupt();
		}
	}

	public void contextInitialized(ServletContextEvent sc) {
		String str = null;
		if (str == null && myThread == null) {
			myThread = new ThreadManager();
			myThread.start(); // servlet 上下文初始化时启动 socket
		}
	}
}
