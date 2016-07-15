package wyj.financial.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wyj.financial.entity.Record;
import wyj.financial.entity.User;

/**
 * Created by wyj19 on 2016-7-11.
 */
public class Data {
    static String IP = "http://192.168.23.1:8080/";
    //static String IP = "http://10.0.2.2:8080/";
    public static boolean linkTest() {
        boolean ans = false;
        String targetURL = IP+"/RestfulTest/services/test/ts";
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


    public static List<Record> search(String query) {
        List<Record> Records = null;
        String targetURL = IP+"RestfulTest/services/test/record";
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
                if (output.equals("null")) {
                    Records = null;
                    break;
                }

                System.out.println(output);
                output = "[" + output.substring(output.indexOf("{\"ba"),
                        output.lastIndexOf("\"}") + 2) + "]";
                System.out.println(output);
                JSONArray json = new JSONArray(output);
                Records = new ArrayList<Record>();


                for (int i = 0; i < json.length(); i++) {
                    //获取每一个JsonObject对象
                    JSONObject tmpObject = json.getJSONObject(i);
                    Record tmpRecord = new Record();

                    System.out.println(tmpObject.getInt("ID"));
                    System.out.println(tmpObject.getDouble("balance"));
                    tmpRecord.setID(tmpObject.getInt("ID"));
                    tmpRecord.setBalance(tmpObject.getDouble("balance"));
                    tmpRecord.setMoneyin(tmpObject.getDouble("moneyin"));
                    tmpRecord.setMoneyout(tmpObject.getDouble("moneyout"));
                    tmpRecord.setDay(tmpObject.getInt("day"));
                    tmpRecord.setMonth(tmpObject.getInt("month"));
                    tmpRecord.setYear(tmpObject.getInt("year"));
                    tmpRecord.setDetail(tmpObject.getString("detail"));
                    tmpRecord.setKind(tmpObject.getString("kind"));
                    tmpRecord.setUserid(tmpObject.getInt("userid"));
                    tmpRecord.setTime(tmpObject.getString("time"));
                    //ans=tmpUser;
                    Records.add(tmpRecord);
                }


            }


            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Records;
    }

    public static User Login(String query) {
        User ans=null;
        String targetURL = IP+"/RestfulTest/services/test/user";
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
                        "HTTP Post Request Failed with Error code : "
                                + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(
                    new InputStreamReader((httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:  \n");
            List<User> Users =new ArrayList();
            while ((output = responseBuffer.readLine()) != null) {
                if (output.equals("null")) {
                    ans = null;
                    break;
                }

                //System.out.println(output);
                output = "[" + output.substring(output.indexOf("{\"ID\""),
                        output.lastIndexOf("\"}") + 2) + "]";
                System.out.println(output);


                JSONArray json = new JSONArray(output);


                for (int i = 0; i < json.length(); i++) {
                    //获取每一个JsonObject对象
                    JSONObject tmpObject = json.getJSONObject(i);
                    User tmpUser = new User();
                    tmpUser.setID(tmpObject.getInt("ID"));
                    tmpUser.setImage(tmpObject.getString("image"));
                    tmpUser.setPassword(tmpObject.getString("password"));
                    tmpUser.setMail(tmpObject.getString("mail"));
                    tmpUser.setName(tmpObject.getString("name"));
                    //ans=tmpUser;
                    Users.add(tmpUser);
                }

                Iterator it = Users.iterator();
                while (it.hasNext()) {

                    User user = (User) it.next();
                    System.out.println(user.getID());
                    ans = user;
                }


            }


            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static boolean modify(String query) {
        String targetURL = IP+"RestfulTest/services/test/modify";

        boolean ans=false;

        try

        {
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
                        "HTTP Post Request Failed with Error code : "
                                + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(
                    new InputStreamReader((httpConnection.getInputStream())));

            String output;

            //Records = new ArrayList();
            while ((output = responseBuffer.readLine()) != null) {
                if (output.equals("false")) {
                    System.out.println("insert false");
                    ans=false;
                    break;
                } else {
                    System.out.println("insert true");
                    ans=true;
                    break;
                }


            }


            httpConnection.disconnect();
            //ans=true;
        }

        catch(MalformedURLException e)

        {
            ans=false;
            e.printStackTrace();

        }

        catch(IOException e)

        {
            ans=false;
            e.printStackTrace();

        }

        return ans;
    }
}
