package wyj.financial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends AppCompatActivity {
    private Spinner RecordSpinner;
    private int ID;
    private String type;

    private TextView tittle_TV;
    private EditText money_ET;
    private EditText detail_ET;
    private String kind;
    //private String detail;
    private double in;
    private double out;
    private String detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        ID=getIntent().getIntExtra("ID",1);
        type=getIntent().getStringExtra("type");
        Log.i("type",type);
        detail_ET=(EditText)findViewById(R.id.detail_editText);
        money_ET=(EditText)findViewById(R.id.mony_editText);
        init();
    }

    private void init(){
        tittle_TV=(TextView)findViewById(R.id.RecordTittle_textView);

        if(type.equals("incometype")){
            tittle_TV.setText("收入录入");
            RecordSpinner = (Spinner) findViewById(R.id.Record_spinner);
            // 建立数据源
            String[] mItems = getResources().getStringArray(R.array.incometype);
            // 建立Adapter并且绑定数据源
            ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
            //绑定 Adapter到控件
            RecordSpinner.setAdapter(_Adapter);
        }else
        {
            tittle_TV.setText("支出录入");
            RecordSpinner = (Spinner) findViewById(R.id.Record_spinner);
            // 建立数据源
            String[] mItems = getResources().getStringArray(R.array.expensetype);
            // 建立Adapter并且绑定数据源
            ArrayAdapter<String> _Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems);
            //绑定 Adapter到控件
            RecordSpinner.setAdapter(_Adapter);
        }
    }
    public void insert(View v)
    {
        String money=money_ET.getText().toString();
        if(money.equals(""))
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        else if(money.length() > 10) {
            Toast.makeText(this,"输入金额太长",Toast.LENGTH_SHORT).show();
        }else{


            if(type.equals("incometype")){
                in=Double.parseDouble(money);
            }else{
                out=Double.parseDouble(money);
            }
            detail=detail_ET.getText().toString();
            kind=RecordSpinner.getSelectedItem().toString();
            new InsertTask(this).execute();
        }
    }
    public void done(boolean b){
        if(b){
        Toast.makeText(this,"录入完毕",Toast.LENGTH_SHORT).show();
        money_ET.setText("");
        detail_ET.setText("");
        }else{

        }

    }

    public int getID() {
        return ID;
    }

    public double getIn() {
        return in;
    }

    public double getOut() {
        return out;
    }

    public String getDetail() {
        return detail;
    }

    public String getKind() {
        return kind;
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
