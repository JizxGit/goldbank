package wyj.financial;

import android.os.AsyncTask;
import android.util.Log;

import wyj.financial.common.Data;
import wyj.financial.entity.User;

/**
 * Created by wyj19 on 2016-7-10.
 */
public class NewLogin extends AsyncTask<String, Integer, String> {

    MainActivity main;
    String username;
    String password;
    User ans = null;
    boolean link=false;
    public NewLogin(MainActivity main,String username,String password) {
        super();
        this.main = main;
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... strings) {

        String query = "select * from user where password = '" +password + "'and name= '" +username + "'";

        if(Data.linkTest()){
            link=true;
            ans = Data.Login(query);
        }



        return null;
    }




    @Override
    protected void onPostExecute(String result){
        if(ans==null)
        {
            Log.i("login","失败");
            if(link==false){
                main.ShowAlert("网络问题", "请检查网络连接");
            }else {
                main.ShowAlert("登陆失败", "登陆失败，用户名或密码错误");
            }

            //new AlertDialog.Builder(main).setTitle("标题").setMessage("登陆失败，用户名或密码错误").setPositiveButton("确定", null).show();
        }else{
            Log.i("login","成功");
            main.user=ans;
            main.ShowAlert("登陆成功","登陆成功");
            main.Logined(ans.getID());
        }
    }
}
