package com.ajax.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ajax.test.dao.UserInfoDAO;
import com.ajax.test.servlet.InitServlet;

public class UserInfoDAOImpl implements UserInfoDAO {
	@Override
	public int insertUserInfo(Map<String, Object> uMap) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		conn = InitServlet.getConnection();
		String sql = "insert into user_info(ui_num, ui_name, ui_age, ui_birth,"
				+ " ui_id, ui_password, ui_phone, ui_email, ui_credat, ui_nickname)"
				+ " values(seq_ui_num.nextval,?,?,?,?,?,?,?,sysdate,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uMap.get("ui_name").toString());
			ps.setInt(2, Integer.parseInt(uMap.get("ui_age").toString()));
			ps.setString(3, uMap.get("ui_birth").toString());
			ps.setString(4, uMap.get("ui_id").toString());
			ps.setString(5, uMap.get("ui_password").toString());
			ps.setString(6, uMap.get("ui_phone").toString());
			ps.setString(7, uMap.get("ui_email").toString());
			ps.setString(8, uMap.get("ui_nickname").toString());
			result = ps.executeUpdate();
			conn.commit();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public int updateUserInfo(Map<String, Object> uMap) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		conn = InitServlet.getConnection();
		String sql = "update user_info set ui_name=?, ui_age=?, ui_birth=?,"
				+ " ui_password=?, ui_phone=?, ui_email=?, ui_nickname=?" + " where ui_num=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uMap.get("ui_name").toString());
			ps.setInt(2, Integer.parseInt(uMap.get("ui_age").toString()));
			ps.setString(3, uMap.get("ui_birth").toString());
			ps.setString(4, uMap.get("ui_password").toString());
			ps.setString(5, uMap.get("ui_phone").toString());
			ps.setString(6, uMap.get("ui_email").toString());
			ps.setString(7, uMap.get("ui_nickname").toString());
			ps.setInt(8, Integer.parseInt(uMap.get("ui_num").toString()));
			result = ps.executeUpdate();
			conn.commit();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public int deleteUserInfo(Map<String, Object> uMap) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		conn = InitServlet.getConnection();
		String sql = "delete from user_info where ui_num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uMap.get("ui_num").toString());
			result = ps.executeUpdate();
			conn.commit();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(ps, conn);
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> selectUserInfoList(Map<String, Object> uMap) {
		List<Map<String, Object>> rList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = InitServlet.getConnection();
		String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, ui_phone,"
				+ " ui_email, ui_credat, ui_nickname from user_info";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Map<String, Object> ui = new HashMap<>();
				ui.put("ui_num", rs.getInt("ui_num"));
				ui.put("ui_name", rs.getString("ui_name"));
				ui.put("ui_age", rs.getInt("ui_age"));
				ui.put("ui_birth", rs.getString("ui_birth"));
				ui.put("ui_id", rs.getString("ui_id"));
				ui.put("ui_phone", rs.getString("ui_phone"));
				ui.put("ui_email", rs.getString("ui_email"));
				ui.put("ui_credat", rs.getString("ui_credat"));
				ui.put("ui_nickname", rs.getString("ui_nickname"));
				rList.add(ui);
			}
			return rList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public Map<String, Object> selectUserInfo(Map<String, Object> uMap) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> ui = new HashMap<>();
		conn = InitServlet.getConnection();
		String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, ui_password, ui_phone,"
				+ " ui_email, ui_credat, ui_nickname from user_info where ui_num=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(uMap.get("ui_num").toString()));
			rs = ps.executeQuery();
			if (rs.next()) {
				ui.put("ui_num", rs.getInt("ui_num"));
				ui.put("ui_name", rs.getString("ui_name"));
				ui.put("ui_age", rs.getInt("ui_age"));
				ui.put("ui_birth", rs.getString("ui_birth"));
				ui.put("ui_id", rs.getString("ui_id"));
				ui.put("ui_password", rs.getString("ui_password"));
				ui.put("ui_phone", rs.getString("ui_phone"));
				ui.put("ui_email", rs.getString("ui_email"));
				ui.put("ui_credat", rs.getString("ui_credat"));
				ui.put("ui_nickname", rs.getString("ui_nickname"));
			}
			return ui;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}

	@Override
	public Map<String, Object> selectUserInfoByUiId(String uiId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Object> ui = new HashMap<>();
		conn = InitServlet.getConnection();
		String sql = "select ui_num, ui_name, ui_age, ui_birth, ui_id, ui_password, ui_phone,"
				+ " ui_email, ui_credat, ui_nickname from user_info where ui_id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, uiId);
			rs = ps.executeQuery();
			if (rs.next()) {
				ui.put("ui_num", rs.getInt("ui_num"));
				ui.put("ui_name", rs.getString("ui_name"));
				ui.put("ui_age", rs.getInt("ui_age"));
				ui.put("ui_birth", rs.getString("ui_birth"));
				ui.put("ui_id", rs.getString("ui_id"));
				ui.put("ui_password", rs.getString("ui_password"));
				ui.put("ui_phone", rs.getString("ui_phone"));
				ui.put("ui_email", rs.getString("ui_email"));
				ui.put("ui_credat", rs.getString("ui_credat"));
				ui.put("ui_nickname", rs.getString("ui_nickname"));
				return ui;				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			InitServlet.close(rs, ps, conn);
		}
		return null;
	}	

}
