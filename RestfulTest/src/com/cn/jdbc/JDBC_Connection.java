package com.cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class JDBC_Connection {
	
	static String drivername = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/economic";
	static String username ="com";
	static String password = "123456";
	//������̬������
	static{
		try{
			Class.forName(drivername);//��������
			System.out.println("���������ɹ�");
		}catch (ClassNotFoundException e) {
			System.out.println("��������ʧ��!��������!");
			e.printStackTrace();
		}
	}
	/**
	 * �������ݿ�ķ���
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try{
			conn = (Connection) DriverManager.getConnection(url, username, password);//��������
			System.out.println("�������ݿ�ɹ���");
		} catch (SQLException e){
			System.out.println("�������ݿ�ʧ�ܣ�����url��username����password");
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �÷������ڹرս���������Ӻ�Statement����
	 * @param rs
	 * @param conn
	 * @param stmt
     */
	public static void free(ResultSet rs,Connection conn ,Statement stmt){
		
		try {
			if(rs != null)
				rs.close();
		}catch (SQLException e ){
			System.out.println("�ر�ResultSetʧ�ܣ�");
			e.printStackTrace();
		}finally{
			
			try{
				if(conn != null)
					conn.close();
			}catch(SQLException e){
				System.out.println("�ر�Connectionʧ�ܣ�");
				e.printStackTrace();
			}finally{
				try{
					if(stmt != null)
						stmt.close();
				}catch(SQLException e){
					System.out.println("�ر�Statementʧ�ܣ�");
					e.printStackTrace();
				}
			}
		}
	}
}
