package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import base.MyDBManager;
import beans.MyUserDataBeans;
import ecsite.EcHelper;

public class MyUserDAO {

	public static MyUserDAO getInstance() {
		return new MyUserDAO();
	}

	public static MyUserDataBeans getUserIdName(String loginId, String password) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		MyUserDataBeans lud = new MyUserDataBeans();
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM user WHERE login_id = ?");
			st.setString(1, loginId);

			ResultSet rs = st.executeQuery();

			int userId = 0;
			String name = null;
			while (rs.next()) {
				if (EcHelper.getSha256(password).equals(rs.getString("password"))) {
					userId = rs.getInt("id");
					name = rs.getString("name");
					System.out.println("login succeeded");
					break;
				}
			}
			lud.setId(userId);
			lud.setName(name);

			System.out.println("searching userId by loginId has been completed");
			return lud;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

	}

	public static MyUserDataBeans getUserDataBeansByUserId(int userId) throws SQLException {
		MyUserDataBeans udb = new MyUserDataBeans();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement("SELECT id, name, login_id, birth_date, address, password FROM user WHERE id =" + userId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				udb.setId(rs.getInt("id"));
				udb.setBirthDate(rs.getDate("birth_date"));
				udb.setName(rs.getString("name"));
				udb.setLoginId(rs.getString("login_id"));
				udb.setAddress(rs.getString("address"));
				udb.setPassword(rs.getString("password"));

			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return udb;
	}

	public void insertUser(MyUserDataBeans udb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = MyDBManager.getConnection();
			st = con.prepareStatement("INSERT INTO user(login_id,name,birth_date,address,password,create_date,update_date) VALUES(?,?,?,?,?,?,?)");

			String birthDate = new SimpleDateFormat("yyyy-MM-dd").format(udb.getBirthDate());

			st.setString(1, udb.getLoginId());
			st.setString(2, udb.getName());
			st.setString(3, birthDate);
			st.setString(4, udb.getAddress());
			st.setString(5, EcHelper.getSha256(udb.getPassword()));
			st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();
			System.out.println("inserting user has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void infoChangeUser(MyUserDataBeans udb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = MyDBManager.getConnection();
			st = con.prepareStatement("update user set name=?, birth_date=?, address=?, login_id=?, update_date=? where id=?");

			String birthDate = new SimpleDateFormat("yyyy-MM-dd").format(udb.getBirthDate());

			st.setString(1, udb.getName());
			st.setString(2, birthDate);
			st.setString(3, udb.getAddress());
			st.setString(4, udb.getLoginId());
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setInt(6, udb.getId());

			st.executeUpdate();
			System.out.println("update user has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public void passChangeUser(int id, String newPassword) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement("update user set password=? where id=?");

			st.setString(1, EcHelper.getSha256(newPassword));
			st.setInt(2, id);

			st.executeUpdate();
			System.out.println("update userPass has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static boolean isOverlapLoginId(String loginId) throws SQLException {
		// 重複しているかどうか表す変数
		boolean isOverlap = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = MyDBManager.getConnection();
			// 入力されたlogin_idが存在するか調べる
			st = con.prepareStatement("SELECT login_id FROM user WHERE login_id = ?");
			st.setString(1, loginId);
			ResultSet rs = st.executeQuery();

			System.out.println("searching loginId by inputLoginId has been completed");

			if (rs.next()) {
				isOverlap = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("overlap check has been completed");
		return isOverlap;
	}

}
