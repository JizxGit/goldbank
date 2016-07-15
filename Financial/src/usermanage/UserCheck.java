package usermanage;

import common.Data;

public class UserCheck {
	//检查用户名是否可用，如果已经存在返回false，否则ture
	public static boolean CheckUsername(String username){
		String query="select * from user where name= '"+username+"'";
		if( Data.login(query)==null)
		{
			return true;
		}
		return false;
	}
	

}
