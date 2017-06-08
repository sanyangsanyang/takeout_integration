package nju.crawler.tool;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgGetter {
	private static String img_path = "D:\\workspace\\img\\";
	public static void get(String urlStr, String path) {
		   URL url = null;
	       try {
	              url = new URL(urlStr);
	       } catch (MalformedURLException e2) {
	             e2.printStackTrace();
	             return;
	       }

	       InputStream is = null;
	        try {
	            is = url.openStream();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	            return;
	        }

	        OutputStream os = null;
	        try{

	            os = new FileOutputStream(img_path + path);
	            int bytesRead = 0;
	            byte[] buffer = new byte[8192];
	            while((bytesRead = is.read(buffer,0,8192))!=-1){
	            os.write(buffer,0,bytesRead);
	       }
	       }catch(FileNotFoundException e){
	           e.printStackTrace();
	           return;
	       } catch (IOException e) {
	           e.printStackTrace();
	           return;
	      }
	}
	
	public static void main(String[] args) {
		String url = "http://p1.meituan.net/movie/f013c57e9506cd2e7c609397c8da04d9213647.jpg@464w_644h_1e_1c";
		String path = "WebContent/img/test.jpg";
		ImgGetter.get(url, path);
	}
}
