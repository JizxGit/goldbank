package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import entity.Record;
import entity.User;

public class Data {
	
	
	public static boolean linkTest() {
		boolean ans = false;
		String targetURL = "http://localhost:8080/RestfulTest/services/test/ts";
		try {
			URL restServiceURL = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
					.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setRequestProperty("Accept", "text/plain");
			httpConnection.setDoOutput(false);// 是否输入参数


			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : "
								+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;
			
			List<User> Users = null;
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println("Output from Server:  \n"+output);
				if (output.equals("true")){
					ans=true;
					break;
				}
									
			}
			

			httpConnection.disconnect();

		} catch (MalformedURLException e) {
			return false;
		

		} catch (IOException e) {
			
			return false;
		}
		return ans;
	}
	

	public static User login(String query) {
		User ans = null;
		String targetURL = "http://localhost:8080/RestfulTest/services/test/user";
		try {
			URL restServiceURL = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
					.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setDoOutput(true);// 是否输入参数

			StringBuffer params = new StringBuffer();
			params.append(query);
			byte[] bypes = params.toString().getBytes();
			httpConnection.getOutputStream().write(bypes);// 输入参数

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : "
								+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;
			System.out.println("Output from Server:  \n");
			List<User> Users = null;
			while ((output = responseBuffer.readLine()) != null) {
				if (output.equals("null")){
					ans=null;
					break;
				}
					
				System.out.println(output);
				output = "["+output.substring(output.indexOf("{\"ID\""),
						output.lastIndexOf("\"}") + 2)+"]";
				System.out.println(output);
				JSONArray json = JSONArray.fromObject(output);
				Users = (List<User>) JSONArray.toCollection(json, User.class);
				Iterator it = Users.iterator();
				while (it.hasNext()) {

					User user = (User) it.next();
					System.out.println(user.getID());
					ans=user;
				}
					
			}
			

			httpConnection.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("URLE");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("IOE");
			e.printStackTrace();

		}
		return ans;
	}
	
	public static List<Record> search(String query) {
		List<Record> Records = null;
		String targetURL = "http://localhost:8080/RestfulTest/services/test/record";
		try {
			URL restServiceURL = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
					.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Accept", "application/json");
			httpConnection.setDoOutput(true);// 是否输入参数

			StringBuffer params = new StringBuffer();
			params.append(query);
			byte[] bypes = params.toString().getBytes();
			httpConnection.getOutputStream().write(bypes);// 输入参数

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : "
								+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;
			System.out.println("Output from Server:  \n");
			
			while ((output = responseBuffer.readLine()) != null) {
				if (output.equals("null")){
					Records=null;
					break;
				}					
				System.out.println(output);
				output = "["+output.substring(output.indexOf("{\"ba"),
						output.lastIndexOf("\"}") + 2)+"]";
				System.out.println(output);
				JSONArray json = JSONArray.fromObject(output);
				Records = (List<Record>) JSONArray.toCollection(json, Record.class);				
			}			
			httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Records;
	}
	public static boolean modify(String query) {
		boolean ans = false;
		String targetURL = "http://localhost:8080/RestfulTest/services/test/modify";
		try {
			URL restServiceURL = new URL(targetURL);
			HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
					.openConnection();
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Accept", "text/plain");
			httpConnection.setDoOutput(true);// 是否输入参数

			StringBuffer params = new StringBuffer();
			params.append(query);
			byte[] bypes = params.toString().getBytes();
			httpConnection.getOutputStream().write(bypes);// 输入参数

			if (httpConnection.getResponseCode() != 200) {
				throw new RuntimeException(
						"HTTP GET Request Failed with Error code : "
								+ httpConnection.getResponseCode());
			}

			BufferedReader responseBuffer = new BufferedReader(
					new InputStreamReader((httpConnection.getInputStream())));

			String output;
						
			while ((output = responseBuffer.readLine()) != null) {
				System.out.println(output);				
				if (output.equals("false")){
					ans=false;
					break;
				}
				ans = true;				
			}			
			httpConnection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ans;
	}
}
