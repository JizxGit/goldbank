package wyj.financial;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import wyj.financial.common.Data;
import wyj.financial.entity.Record;

/**
 * Created by wyj19 on 2016-7-10.
 */

class LoginTask extends AsyncTask<String, Integer, String> {
    HomePageActivity homepage;

    double balance=0;
    double amountin=0;
    double amountout=0;
    public LoginTask(HomePageActivity homepage) {
        super();
        this.homepage = homepage;
    }

    @Override
    protected String doInBackground(String... strings) {
        boolean ans = false;

        GregorianCalendar getMessageTime = new GregorianCalendar();
        int year = getMessageTime.get(Calendar.YEAR);
        int month = getMessageTime.get(Calendar.MONTH) + 1;
        int day = getMessageTime.get(Calendar.DATE);

        String query = "select * from economic where userid="+homepage.getID()+" order by id DESC";
        //Log.i("ID",homepage.getID()+"");
        System.out.println(query);
        //String targetURL = "http://10.0.2.2:8080/RestfulTest/services/test/record";

        List<Record> Records =  Data.search(query);

        balance=Records.get(0).getBalance();

        Iterator it = Records.iterator();

        while (it.hasNext()) {
            Record record = (Record) it.next();
            Log.i("record",record.getMoneyin()+"");
            if (record.getMoneyin() != 0)
                amountin = amountin + record.getMoneyin();
            if (record.getMoneyout()!= 0)
                amountout = amountout + record.getMoneyout();
        }

//
//        try {
//            URL restServiceURL = new URL(targetURL);
//            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
//                    .openConnection();
//            httpConnection.setRequestMethod("POST");
//            httpConnection.setRequestProperty("Accept", "application/json");
//            httpConnection.setDoOutput(true);// 是否输入参数
//
//            StringBuffer params = new StringBuffer();
//            params.append(query);
//            byte[] bypes = params.toString().getBytes();
//            httpConnection.getOutputStream().write(bypes);// 输入参数
//
//            if (httpConnection.getResponseCode() != 200) {
//                throw new RuntimeException(
//                        "HTTP Post Request Failed with Error code : "
//                                + httpConnection.getResponseCode());
//            }
//
//            BufferedReader responseBuffer = new BufferedReader(
//                    new InputStreamReader((httpConnection.getInputStream())));
//
//            String output;
//
//            List<Record> Records =new ArrayList();
//            while ((output = responseBuffer.readLine()) != null) {
//                if (output.equals("null")) {
//                    System.out.println("noID");
//                    break;
//                }
//
//                //System.out.println(output);
//                output = "["+output.substring(output.indexOf("{\"ba"),
//                        output.lastIndexOf("\"}") + 2)+"]";
//                System.out.println(output);
//
//
//                JSONArray json = new JSONArray(output);
//
//
//
//                for (int i = 0; i < json.length(); i++) {
//                    //获取每一个JsonObject对象
//                    JSONObject tmpObject = json.getJSONObject(i);
//                    Record tmpRecord = new Record();
//                    tmpRecord.setID(tmpObject.getInt("ID"));
//                    tmpRecord.setBalance(tmpObject.getDouble("balance"));
//                    tmpRecord.setMoneyin(tmpObject.getDouble("moneyin"));
//                    tmpRecord.setMoneyout(tmpObject.getDouble("moneyout"));
//                    tmpRecord.setDay(tmpObject.getInt("day"));
//                    tmpRecord.setMonth(tmpObject.getInt("month"));
//                    tmpRecord.setYear(tmpObject.getInt("year"));
//                    tmpRecord.setDetail(tmpObject.getString("detail"));
//                    tmpRecord.setKind(tmpObject.getString("kind"));
//                    tmpRecord.setUserid(tmpObject.getInt("userid"));
//                    tmpRecord.setTime(tmpObject.getString("time"));
//                    //ans=tmpUser;
//                    Records.add(tmpRecord);
//                }
//
//                balance= json.getJSONObject(1).getDouble("balance");
//
//                Iterator it = Records.iterator();
//                while (it.hasNext()) {
//
//                    Record record = (Record) it.next();
//                    Log.i("record",record.getMoneyin()+"");
//                    if (record.getMoneyin() != 0)
//                        amountin = amountin + record.getMoneyin();
//                    if (record.getMoneyout()!= 0)
//                        amountout = amountout + record.getMoneyout();
//                }


//            }

//
//            httpConnection.disconnect();
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        return null;
    }
    protected void onPostExecute(String result){
        homepage.dataInit(balance,amountin,amountout);
    }

}