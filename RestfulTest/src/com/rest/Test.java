package com.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.cn.jdbc.JDBC_Connection;
import com.mysql.jdbc.Statement;
import com.sun.jersey.spi.resource.Singleton;

@Path("test")
@Singleton
public class Test {
	@POST
	@Path("user")
	@Produces("application/json")
	public List<User> getUser(String query) {
		List<User> Users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(query);
		try {
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User User = new User();
				User.setID(rs.getInt("ID"));
				User.setName(rs.getString("name"));
				User.setPassword(rs.getString("password"));
				User.setMail(rs.getString("mail"));
				User.setImage(rs.getString("image"));
				Users.add(User);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, (Statement) pstmt);
		}
		return Users;
	}

	@POST
	@Path("record")
	@Produces("application/json")
	public List<Record> getRecords(String query) {
		List<Record> Records = new ArrayList<Record>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = JDBC_Connection.getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Record Record = new Record();
				Record.setID(rs.getInt("ID"));
				Record.setYear(rs.getInt("year"));
				Record.setMonth(rs.getInt("month"));
				Record.setDay(rs.getInt("day"));
				Record.setTime(rs.getString("time"));
				Record.setMoneyin(rs.getDouble("moneyin"));
				Record.setMoneyout(rs.getDouble("moneyout"));
				Record.setBalance(rs.getDouble("balance"));
				Record.setKind(rs.getString("kind"));
				Record.setDetail(rs.getString("detail"));
				Record.setUserid(rs.getInt("userid"));
				Records.add(Record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, (Statement) pstmt);
		}
		return Records;
	}

	@POST
	@Path("modify")
	@Produces("text/plain")
	public String modify(String query) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String state = "false";
		try {
			conn = JDBC_Connection.getConnection();
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			state = "true";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBC_Connection.free(rs, conn, (Statement) pstmt);
		}
		return state;
	}

	@GET
	@Path("ts")
	@Produces("text/plain")
	public String test1() {
		return "true";
	}
}
