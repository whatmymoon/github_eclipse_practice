package com.ezen.word.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

public class DataBaseManager {

	
	@Autowired // 의존객체 주입 -> 자동주입을 가능하게 해주는 어너테이션
	private DataBaseUserInfo dbi;

	/*
	private DataBaseManager(DataBaseUserInfo dbi) {
		this.dbi = dbi;
	}
	*/

	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(dbi.getDriver());
			con = DriverManager.getConnection(dbi.getUrl(), dbi.getId(), dbi.getPw());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}