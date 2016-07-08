package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DBcom2 {
	private static  String driver = "com.mysql.jdbc.Driver";
	private static  String url = "jdbc:mysql://127.0.0.1:3306/economic";
	private static  String user = "com";
	private static  String password = "123456";
	
	public DBcom2(){
		connectDriver();
	}

	public  static boolean connectDriver() {
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
	
	public static Connection getconnection(){
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public  static Statement creatstate(Connection con) {
		try {		
			Statement st = con.createStatement();
			return st;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("连接数据库失败！");
			return null;
		}
	}

	public static ResultSet query(Statement st, String sql) {
		try {
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean update(Statement st, String sql) {
		try {
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public  static boolean close(ResultSet rs,Statement st,Connection con) {
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
