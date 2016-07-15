package wyj.financial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import wyj.financial.common.Round;
import wyj.financial.entity.Record;

public class SearchActivity extends AppCompatActivity {
    private int ID = 0;
    private CheckBox time_CB;
    private CheckBox kind_CB;
    private Spinner kind_SP;
    private Spinner year_SP;
    private Spinner month_SP;
    private Button search_button;
    private ListView result_LV;
    private List<String> yearlist = new ArrayList<String>();
    private List<String> monthlist = new ArrayList<String>();

    private String query;
    private List<Record> records = new ArrayList<Record>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if(ID==0)
            ID = getIntent().getIntExtra("ID", 1);
        init();
    }

    private void init() {
        time_CB = (CheckBox) findViewById(R.id.time_checkBox);
        kind_CB = (CheckBox) findViewById(R.id.kind_checkBox);
        kind_SP = (Spinner) findViewById(R.id.kind_spinner);
        year_SP = (Spinner) findViewById(R.id.year_spinner);
        month_SP = (Spinner) findViewById(R.id.month_spinner);
        search_button = (Button) findViewById(R.id.StartSearch_button);
        result_LV = (ListView) findViewById(R.id.result_listView);
        Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            yearlist.add(year - i + "");
        }
        for (int i = 0; i < 12; i++) {
            monthlist.add(i + 1 + "");
        }

        ArrayAdapter<String> _Adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearlist);
        year_SP.setAdapter(_Adapter1);

        ArrayAdapter<String> _Adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthlist);
        month_SP.setAdapter(_Adapter2);


        String[] mItems = getResources().getStringArray(R.array.alltype);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        kind_SP.setAdapter(_Adapter);

        year_SP.setEnabled(false);
        month_SP.setEnabled(false);
        kind_SP.setEnabled(false);

        time_CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (time_CB.isChecked()) {
                    year_SP.setEnabled(true);
                    month_SP.setEnabled(true);
                } else {
                    year_SP.setEnabled(false);
                    month_SP.setEnabled(false);
                }
            }
        });
        kind_CB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (kind_CB.isChecked()) {
                    kind_SP.setEnabled(true);
                } else {
                    kind_SP.setEnabled(false);

                }
            }
        });


    }

    public void search(View view) {
        query = "select * from economic where userid= " + ID + " ";
        if (kind_CB.isChecked()) {
            query += "and kind='" + kind_SP.getSelectedItem() + "' ";
        }
        if (time_CB.isChecked()) {
            query += "and year='" + year_SP.getSelectedItem() + "'and month='" + month_SP.getSelectedItem()
                    + "' ";
        }


        query += "order by id DESC";

        if (!(time_CB.isChecked() || kind_CB.isChecked()))
            query = "select * from economic where userid = "
                    + ID + " order by id DESC";
        new SearchTask(this).execute();

    }

    public void drawListView(){
        if(records==null){
            result_LV.setAdapter(null);
            return;
        }
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,     Object>>();
        Iterator it = records.iterator();
        while(it.hasNext())
        {
            Record record = (Record)it.next();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("recordtime", record.getYear()+"."+record.getMonth()+"."+record.getDay()+" "+record.getTime());
            map.put("recordkind",record.getKind());
            if(record.getMoneyin()<1e-8){
                map.put("moneytittle","支出：");
                map.put("recordmoney", Round.round(record.getMoneyout()));
            }
            else {
                map.put("moneytittle", "收入：");
                map.put("recordmoney", Round.round(record.getMoneyin()));
            }
            map.put("recorddetail",record.getDetail());
            listItem.add(map);
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this,listItem,//需要绑定的数据
                R.layout.item,
                new String[] {"recordtime","recordkind","moneytittle","recordmoney","recorddetail"},
                new int[] {R.id.recordtime,R.id.recordkind,R.id.moneytittle,R.id.recordmoney,R.id.recorddetail}
        );

        result_LV.setAdapter(mSimpleAdapter);



}

    public int getID() {
        return ID;
    }


    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getQuery() {
        return query;
    }
    public boolean OnKeyDown(int keyCode,KeyEvent
            event){

        if (keyCode== KeyEvent.KEYCODE_BACK)
        {
            this.finish();
        }
        return false;
    }
}
