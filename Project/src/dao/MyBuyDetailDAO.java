package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.MyDBManager;
import beans.MyBuyDetailDataBeans;
import beans.MyItemDataBeans;

public class MyBuyDetailDAO {
	//インスタンスオブジェクトを返却させてコードの簡略化
		public static MyBuyDetailDAO getInstance() {
			return new MyBuyDetailDAO();
		}

		/**
		 * 購入詳細登録処理
		 * @param bddb BuyDetailDataBeans
		 * @throws SQLException
		 * 			呼び出し元にスローさせるため
		 */
		public static void insertBuyDetail(MyBuyDetailDataBeans bddb) throws SQLException {
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = MyDBManager.getConnection();
				st = con.prepareStatement(
						"INSERT INTO t_buy_detail(buy_id,item_id) VALUES(?,?)");
				st.setInt(1, bddb.getBuyId());
				st.setInt(2, bddb.getItemId());
				st.executeUpdate();
				System.out.println("inserting BuyDetail has been completed");

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
		 * @return {BuyDataDetailBeans}
		 * @throws SQLException
		 */
		public ArrayList<MyBuyDetailDataBeans> getBuyDataBeansListByBuyId(int buyId) throws SQLException {
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = MyDBManager.getConnection();

				st = con.prepareStatement("SELECT * FROM t_buy_detail WHERE buy_id = ?");
				st.setInt(1, buyId);

				ResultSet rs = st.executeQuery();
				ArrayList<MyBuyDetailDataBeans> buyDetailList = new ArrayList<MyBuyDetailDataBeans>();

				while (rs.next()) {
					MyBuyDetailDataBeans bddb = new MyBuyDetailDataBeans();
					bddb.setId(rs.getInt("id"));
					bddb.setBuyId(rs.getInt("buy_id"));
					bddb.setItemId(rs.getInt("item_id"));
					buyDetailList.add(bddb);
				}

				System.out.println("searching BuyDataBeansList by BuyID has been completed");
				return buyDetailList;
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
	     * 購入IDによる購入詳細情報検索
	     * @param buyId
	     * @return buyDetailItemList ArrayList<ItemDataBeans>
	     *             購入詳細情報のデータを持つJavaBeansのリスト
	     * @throws SQLException
	     */
		public static ArrayList<MyItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException {
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = MyDBManager.getConnection();

				st = con.prepareStatement(
						"SELECT m_item.id,"
						+ " m_item.name,"
						+ " m_item.price"
						+ " FROM t_buy_detail"
						+ " JOIN m_item"
						+ " ON t_buy_detail.item_id = m_item.id"
						+ " WHERE t_buy_detail.buy_id = ?");
				st.setInt(1, buyId);

				ResultSet rs = st.executeQuery();
				ArrayList<MyItemDataBeans> buyDetailItemList = new ArrayList<MyItemDataBeans>();

				while (rs.next()) {
					MyItemDataBeans idb = new MyItemDataBeans();
					idb.setId(rs.getInt("id"));
					idb.setName(rs.getString("name"));
					idb.setPrice(rs.getInt("price"));


					buyDetailItemList.add(idb);
				}

				System.out.println("searching ItemDataBeansList by BuyID has been completed");
				return buyDetailItemList;
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
