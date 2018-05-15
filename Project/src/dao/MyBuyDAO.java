package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import base.MyDBManager;
import beans.MyBuyDataBeans;

public class MyBuyDAO {
	//インスタンスオブジェクトを返却させてコードの簡略化
	public static MyBuyDAO getInstance() {
		return new MyBuyDAO();
	}

	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(MyBuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO buy_detail(user_id,delivery_method_id,total_price,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getDelivertMethodId());
			st.setInt(3, bdb.getTotalPrice());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
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
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 				購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static MyBuyDataBeans getBuyDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM buy_detail"
							+ " JOIN delivery_method"
							+ " ON buy_detail.delivery_method_id = delivery_method.id"
							+ " WHERE buy_detail.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			MyBuyDataBeans bdb = new MyBuyDataBeans();

			if (rs.next()) {

				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));
			}

			System.out.println("searching BuyDataBeans by buyID has been completed");

			return bdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//購入履歴取得
	public static List<MyBuyDataBeans> buyListSearch(int id, int page, int pageMaxHistory) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = MyDBManager.getConnection();
			int start = (page - 1) * pageMaxHistory;

			st = con.prepareStatement("SELECT * FROM buy_detail"
					+ " INNER JOIN delivery_method"
					+ " ON buy_detail.delivery_method_id = delivery_method.id"
					+ " WHERE buy_detail.user_id= ?"
					+ " ORDER BY buy_detail.id"
					+ " DESC LIMIT ?,?");
			st.setInt(1, id);
			st.setInt(2, start);
			st.setInt(3, pageMaxHistory);

			ResultSet rs = st.executeQuery();
			List<MyBuyDataBeans> buyList = new ArrayList<MyBuyDataBeans>();

			while (rs.next()) {
				MyBuyDataBeans buyDataBeans = new MyBuyDataBeans();

				buyDataBeans.setId(rs.getInt("id"));
				buyDataBeans.setUserId(rs.getInt("user_id"));
				buyDataBeans.setTotalPrice(rs.getInt("total_price"));
				buyDataBeans.setDelivertMethodId(rs.getInt("delivery_method_id"));
				buyDataBeans.setBuyDate(rs.getTimestamp("create_date"));
				buyDataBeans.setDeliveryMethodName(rs.getString("name"));
				buyDataBeans.setDeliveryMethodPrice(rs.getInt("price"));
				buyList.add(buyDataBeans);
			}
			System.out.println("searching BuyDataBeansList by BuyID has been completed");
			return buyList;

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

	}

	public static double getBuyHistoryCount(int id) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from buy_detail where user_id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			double count = 0.0;
			if (rs.next()) {
				count = Double.parseDouble(rs.getString("cnt"));
			}

			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
