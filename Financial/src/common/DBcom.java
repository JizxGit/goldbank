package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBcom {
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/economic";
	private String user = "com";
	private String password = "123456";
	private Connection con = null;
	private Statement st = null;
	
	public DBcom(){
		//初始化数据库驱动
		connectDriver();
	}

	//可以直接初始化，得到需要的Statement
	public Statement initState() {
		getconnection();
		st=creatstate();
		return st;
	}

	//初始化驱动的功能方法
	public boolean connectDriver() {
		try {
			Class.forName(driver);
			return true;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "数据库驱动不存在!", "警告", JOptionPane.WARNING_MESSAGE);
			e.getStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//单独得到所需的Connection
	public Connection getconnection() {
		try {
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	//得到所需的Statement
	public Statement creatstate() {
		try {
			st = con.createStatement();
			return st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接数据库失败！");
			return null;
		}
	}

	//建立连接后，可以直接使用的query方法
	public ResultSet query(String sql) {
		try {
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	//建立连接后，可以直接使用的update方法
	public boolean update(String sql) {
		try {
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

    //关闭数据库的连接
	public boolean close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭连接失败！");
			return false;
		}
	}
}
