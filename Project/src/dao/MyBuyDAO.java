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
						"INSERT INTO t_buy(user_id,total_price,delivery_method_id,create_date) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, bdb.getUserId());
				st.setInt(2, bdb.getTotalPrice());
				st.setInt(3, bdb.getDelivertMethodId());
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
						"SELECT * FROM t_buy"
								+ " JOIN m_delivery_method"
								+ " ON t_buy.delivery_method_id = m_delivery_method.id"
								+ " WHERE t_buy.id = ?");
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

		public static List<MyBuyDataBeans> buyListSearch(int userId) throws SQLException {
			Connection con = null;
			PreparedStatement st = null;

			try {
				con = MyDBManager.getConnection();

				st = con.prepareStatement("SELECT * FROM t_buy"
						+ " JOIN m_delivery_method"
						+ " ON t_buy.delivery_method_id = m_delivery_method.id"
						+ " WHERE t_buy.user_id= ?");
				st.setInt(1, userId);

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

	}
