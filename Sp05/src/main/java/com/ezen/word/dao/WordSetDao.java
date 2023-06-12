package com.ezen.word.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ezen.word.dto.WordSet;
import com.ezen.word.util.DataBaseManager;

public class WordSetDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired // 의존 객체 주입 -> 자동주입을 가능하게 해주는 어너테이션
	DataBaseManager dbm;
	
	/*
	public WordSetDao( DataBaseManager dbm) {
		this.dbm = dbm;
	}
	*/

	public void insertWord(WordSet wordSet) {
		
		String sql = "insert into wordset values(? , ?)";
		con = dbm.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wordSet.getWordKey());
			pstmt.setString(2, wordSet.getWordValue());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(con, pstmt, rs);
		}
		
	}

	public ArrayList<WordSet> selectAll() {
		
		ArrayList<WordSet> list = new ArrayList<WordSet>();
		String sql = "select * from wordset";
		con = dbm.getConnection(); // this.dbm is null!!!
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WordSet ws = new WordSet(
					rs.getString("wordkey"),
					rs.getString("wordvalue")
				);
				list.add(ws);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			dbm.close(con, pstmt, rs);
		}
		return list;
	}

	public ArrayList<WordSet> wordSelectAll() {
		
		ArrayList<WordSet> list = new ArrayList<WordSet>();
		String sql = "select * from wordset";
		con = dbm.getConnection(); // this.dbm is null!!! Autowired - > WorldSelectAllService 클래스에 써서 dao 자동 주입 되게 하기!
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				WordSet ws = new WordSet(
					rs.getString("wordkey"),
					rs.getString("wordvalue")
				);
				list.add(ws);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			dbm.close(con, pstmt, rs);
		}
		return list;
	}

	public WordSet searchWord(String input) {
		
		WordSet ws = null;
		String sql = "select * from wordset where wordkey = ?";
		con = dbm.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, input);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ws = new WordSet(
					rs.getString("wordKey"),
					rs.getString("wordValue")
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(con, pstmt, rs);
		}
		
		return ws;
	}
	
}
