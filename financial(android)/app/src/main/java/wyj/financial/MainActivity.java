package wyj.financial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import wyj.financial.entity.User;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    EditText Username;
    EditText Password;
    Button Login;
    User user;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private void initView() {
        Username = (EditText) findViewById(R.id.editText_Username);
        Password = (EditText) findViewById(R.id.editText_Password);
        Login = (Button) findViewById(R.id.button_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Username.getText().toString();
                String password = Password.getText().toString();

                if(username.equals(""))
                    Toast.makeText(MainActivity.this,"登陆失败，请填写用户名",Toast.LENGTH_SHORT).show();
                    //new AlertDialog.Builder(MainActivity.this).setTitle("登陆失败").setMessage("登陆失败，请填写用户名").setPositiveButton("确定", null).show();
                else if(password.equals(""))
                    Toast.makeText(MainActivity.this,"登陆失败，请填写密码",Toast.LENGTH_SHORT).show();
                    //new AlertDialog.Builder(MainActivity.this).setTitle("登陆失败").setMessage("登陆失败，请填写密码").setPositiveButton("确定", null).show();
                else
                    new NewLogin(MainActivity.this,username,password).execute();

            }

        });

//        tv=(TextView)findViewById(R.id.MAIN_TV);
//
//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Random r=new Random();
//                tv.setTextColor(Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
//            }
//        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }
    public void ShowAlert(String title,String content)
    {
        Toast.makeText(MainActivity.this,content,Toast.LENGTH_SHORT).show();
        //new AlertDialog.Builder(this).setTitle(title).setMessage(content).setPositiveButton("确定", null).show();
    }
    public void Logined(int ID)
    {
        Intent intent = new Intent();
        intent.putExtra("ID",ID);
        /* 指定intent要启动的类 */
        intent.setClass(this, HomePageActivity.class);
        /* 启动一个新的Activity */
        this.startActivity(intent);
        /* 关闭当前的Activity */
        //this.finish();

    }



}
