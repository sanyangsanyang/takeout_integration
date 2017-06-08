package nju.crawler.tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 通过url请求对应的html
 * 设置超时时间为5s
 * 超时或请求错误时返回null
 */
public class UrlRequest {
	public static String sendRequest(String urlStr) {
        BufferedReader in = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
        	
            URL realUrl = new URL(urlStr);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection .setConnectTimeout(5000);
            connection .setReadTimeout(5000);
            
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                map.get(key);
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
           
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return stringBuilder.toString();
	}
	
	 public static String getRedirectUrl(String path) throws Exception {  
	        HttpURLConnection conn = (HttpURLConnection) new URL(path)  
	                .openConnection();  
	        conn.setInstanceFollowRedirects(false);  
	        conn.setConnectTimeout(5000);  
	        return conn.getHeaderField("Location");  
	}  
	
	public static void main(String[] args) {
		System.out.println((new UrlRequest()).sendRequest("https://dianying.taobao.com/showList.htm?n_s=new"));
	}
}
