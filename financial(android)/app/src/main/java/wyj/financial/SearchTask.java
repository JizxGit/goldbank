package wyj.financial;

import android.os.AsyncTask;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import wyj.financial.common.Data;
import wyj.financial.entity.Record;

/**
 * Created by wyj19 on 2016-7-11.
 */

class SearchTask extends AsyncTask<String, Integer, String> {
    SearchActivity SearchAct;


    public SearchTask(SearchActivity SearchAct) {
        super();
        this.SearchAct = SearchAct;
    }

    @Override
    protected String doInBackground(String... strings) {


        GregorianCalendar getMessageTime = new GregorianCalendar();
        int year = getMessageTime.get(Calendar.YEAR);
        int month = getMessageTime.get(Calendar.MONTH) + 1;
        int day = getMessageTime.get(Calendar.DATE);


        //Log.i("ID",homepage.getID()+"");
        System.out.println(SearchAct.getQuery());
        //String targetURL = "http://10.0.2.2:8080/RestfulTest/services/test/record";

        List<Record> Records =  Data.search(SearchAct.getQuery());

        SearchAct.setRecords(Records);

        //Iterator it = Records.iterator();

        return null;
    }
    protected void onPostExecute(String result){
        SearchAct.drawListView();
    }

}