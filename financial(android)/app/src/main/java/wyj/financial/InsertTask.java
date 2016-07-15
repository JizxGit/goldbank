package wyj.financial;

import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import wyj.financial.common.Data;
import wyj.financial.entity.Record;

/**
 * Created by wyj19 on 2016-7-10.
 */

class InsertTask extends AsyncTask<String, Integer, String> {
    RecordActivity RecordAct;

    double balance=0;
    boolean ans=false;

    public InsertTask(RecordActivity RecordAct) {
        super();
        this.RecordAct = RecordAct;
    }

    @Override
    protected String doInBackground(String... strings) {


        String query = "select * from economic where userid="+RecordAct.getID()+" order by id DESC";

        System.out.println(query);

        List<Record> Records =  Data.search(query);

        balance=Records.get(0).getBalance();
        balance+=RecordAct.getIn();
        balance-=RecordAct.getOut();

        GregorianCalendar getMessageTime = new GregorianCalendar();
        int year = getMessageTime.get(Calendar.YEAR);
        int month = getMessageTime.get(Calendar.MONTH) + 1;
        int day = getMessageTime.get(Calendar.DATE);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "HHmmss");
        String time = sdf.format(d);

        query="insert into economic (year,month,day,time,kind,moneyin,moneyout,balance,detail,userid) values("
                + year
                + ","
                + month
                + ","
                + day
                + ",'"
                + time
                + "','"
                + RecordAct.getKind()
                + "','"
                + RecordAct.getIn()
                + "','"
                + RecordAct.getOut()
                + "','"
                + balance
                + "','" + RecordAct.getDetail() + "',"+RecordAct.getID()+")";


        //Log.i("ID",homepage.getID()+"");
        System.out.println(query);
        ans=Data.modify(query);

//        String targetURL = "http://10.0.2.2:8080/RestfulTest/services/test/modify";
//
//
//        try {
//            URL restServiceURL = new URL(targetURL);
//            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
//                    .openConnection();
//            httpConnection.setRequestMethod("POST");
//            httpConnection.setRequestProperty("Accept", "text/plain");
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
//            Records =new ArrayList();
//            while ((output = responseBuffer.readLine()) != null) {
//                if (output.equals("false")) {
//                    System.out.println("insert false");
//                    break;
//                }else{
//                    System.out.println("insert true");
//                    break;
//                }
//
//
//            }
//
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
//        }

        return null;
    }
    protected void onPostExecute(String result){
        RecordAct.done(ans);
    }

}