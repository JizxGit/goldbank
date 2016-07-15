package wyj.financial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import wyj.financial.common.Round;

public class HomePageActivity extends AppCompatActivity {

    private int ID;
    private TextView balancetv;
    private TextView incometv;
    private TextView expensetv;

    @Override
    protected void onResume() {
        super.onResume();
        new LoginTask(HomePageActivity.this).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ID=getIntent().getIntExtra("ID",1);
        new LoginTask(HomePageActivity.this).execute();



        setContentView(R.layout.activity_home_page);
    }


    public int getID() {
        return ID;
    }

    private void init(){
        balancetv=(TextView)findViewById(R.id.Balance_TV);
        incometv=(TextView)findViewById(R.id.Income_TV);
        expensetv=(TextView)findViewById(R.id.Expense_TV);

    }
    public void dataInit(double balance,double income,double expense){

        init();
        balancetv.setText(Round.round(balance));
        incometv.setText(Round.round(income)+"");
        expensetv.setText(Round.round(expense)+"");

//        balancetv.setText("test");
//        incometv.setText("test");
//        expensetv.setText("test");
    }
    public void jumpToRecord(View v)
    {
        Intent intent = new Intent();
        String type="expensetype";
        RadioButton iRB= (RadioButton)findViewById(R.id.Income_radioButton);
        Log.i("check",iRB.isChecked()+"");
        if(iRB.isChecked())
            type="incometype";
        intent.putExtra("ID",ID);
        intent.putExtra("type",type);

        /* 指定intent要启动的类 */
        intent.setClass(this, RecordActivity.class);
        /* 启动一个新的Activity */
        this.startActivity(intent);
        /* 关闭当前的Activity */
        //this.finish();
    }
    public void jumpToSearch(View v)
    {
        Intent intent = new Intent();

        intent.putExtra("ID",ID);

        /* 指定intent要启动的类 */
        intent.setClass(this, SearchActivity.class);
        /* 启动一个新的Activity */
        this.startActivity(intent);
        /* 关闭当前的Activity */
        //this.finish();
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
