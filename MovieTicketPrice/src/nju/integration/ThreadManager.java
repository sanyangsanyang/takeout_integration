package nju.integration;

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
				Thread.sleep(3600000); // 每隔半个时执行一次
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



			isRunning = false;
		}
	}
	
	private void execute() {
//		ComponentFactory.getCrawlerLog().log("开始爬取数据.");
//		
//		//开始爬数据并存到文件里，爬好后返回
//		ComponentFactory.getCrawlerManager().getNewData();
		
		//验证数据格式，如果错了就报告，错的那个文件删除
		if(ComponentFactory.getXmlSchemaManager().validate()) {
			System.out.println("xml文件验证成功，将数据写入数据库中");
			
			//存放数据到数据库中
			ComponentFactory.getDataIntegrator().integrator();
		
			System.out.println("电影数据成功更新！下一次更新将在半小时内开始");
		} else {
			System.out.println("xml文件验证失败，请检查log");
		}
		

	}
	

}
