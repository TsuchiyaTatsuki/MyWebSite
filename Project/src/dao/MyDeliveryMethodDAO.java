package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.MyDBManager;
import beans.MyDeliveryMethodDataBeans;

public class MyDeliveryMethodDAO {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static MyDeliveryMethodDAO getInstance() {
		return new MyDeliveryMethodDAO();
	}

	/**
	 * DBに登録されている配送方法を取得
	 * @return {DeliveryMethodDataBeans}
	 * @throws SQLException
	 */
	public static ArrayList<MyDeliveryMethodDataBeans> getAllDeliveryMethodDataBeans() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM delivery_method");

			ResultSet rs = st.executeQuery();

			ArrayList<MyDeliveryMethodDataBeans> deliveryMethodDataBeansList = new ArrayList<MyDeliveryMethodDataBeans>();
			while (rs.next()) {
				MyDeliveryMethodDataBeans dm = new MyDeliveryMethodDataBeans();
				dm.setId(rs.getInt("id"));
				dm.setName(rs.getString("name"));
				dm.setPrice(rs.getInt("price"));
				deliveryMethodDataBeansList.add(dm);
			}

			System.out.println("searching all DeliveryMethodDataBeans has been completed");

			return deliveryMethodDataBeansList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 配送方法をIDをもとに取得
	 * @param DeliveryMethodId
	 * @return DeliveryMethodDataBeans
	 * @throws SQLException
	 */
	public static MyDeliveryMethodDataBeans getDeliveryMethodDataBeansByID(int dmId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM delivery_method WHERE id = ?");
			st.setInt(1, dmId);

			ResultSet rs = st.executeQuery();

			MyDeliveryMethodDataBeans dmdb = new MyDeliveryMethodDataBeans();
			if (rs.next()) {
				dmdb.setId(rs.getInt("id"));
				dmdb.setName(rs.getString("name"));
				dmdb.setPrice(rs.getInt("price"));
			}

			System.out.println("searching DeliveryMethodDataBeans by DeliveryMethodID has been completed");

			return dmdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 *購入IDによる配送方法検索
	 * @param buyId
	 * @return dmdb DeliveryMethodDataBeans
	 *             配送方法の情報に対応するデータを持つJavaBeans
	 * @throws SQLException
	 */
	public static MyDeliveryMethodDataBeans getDeliveryMethodDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement(
					"SELECT m_delivery_method.name,"
							+ " m_delivery_method.price"
							+ " FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON m_delivery_method.id = t_buy.delivery_method_id"
							+ " WHERE t_buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			MyDeliveryMethodDataBeans dmdb = new MyDeliveryMethodDataBeans();

			while (rs.next()) {
				dmdb.setName(rs.getString("name"));
				dmdb.setPrice(rs.getInt("delivery_method.price"));

			}

			System.out.println("searching DeliveryMethodDataBeans by BuyID has been completed");
			return dmdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
