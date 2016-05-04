package cn.sheetanchor.wechat.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 调用图灵机器人api接口，获取智能回复内容
 * @author pamchen-1
 *
 */
public class TulingApiProcess {
	/**
	 * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
	 * @param content
	 * @return
	 */
	public static String getTulingResult(String content){
		//图灵机器人数据库接口
		String APIKEY = "438cc658c632496fa4dc33511cb2674d";
		StringBuffer sb = new StringBuffer();
		String result = "";
		try {
			String INFO = URLEncoder.encode(content, "utf-8"); 
			String getURL = "http://www.tuling123.com/openapi/api?key="
					+ APIKEY + "&info=" + INFO;
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			JSONObject json = new JSONObject(sb.toString());
			result = json.getString("text");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 调用图灵机器人平台接口 
	 * 描述：@param args
	 * 描述：@throws IOException
	 * 作者：herosky
	 */
	public static void main(String[] args) throws IOException { 

	    String APIKEY = "438cc658c632496fa4dc33511cb2674d"; 
	    String INFO = "猴赛雷"; 
	    String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO; 
	    URL getUrl = new URL(getURL); 
	    HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
	    connection.connect(); 

	    // 取得输入流，并使用Reader读取 
	    BufferedReader reader = new BufferedReader(new InputStreamReader( connection.getInputStream(), "utf-8"));
	    StringBuffer sb = new StringBuffer(); 
	    String line = ""; 
		String result = null;
	    while ((line = reader.readLine()) != null) { 
	        sb.append(line); 
	    } 
	    reader.close(); 
	    // 断开连接 
	    connection.disconnect(); 
	    System.out.println(sb); 
	    JSONObject json;
	    try {
	    	json = new JSONObject(sb.toString());
	    	if(100000==json.getInt("code")){  
	            result = json.getString("text");  
	        }  
			result = new String(result.getBytes(), "UTF-8");
			System.out.println("问："+INFO);
			System.out.println("图灵："+result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
}