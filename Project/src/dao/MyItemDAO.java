package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.MyDBManager;
import beans.MyItemDataBeans;

public class MyItemDAO {

	// インスタンスオブジェクトを返却させてコードの簡略化
	public static MyItemDAO getInstance() {
		return new MyItemDAO();
	}

	/**
	 * ランダムで引数指定分のItemDataBeansを取得
	 * @param limit 取得したいかず
	 * @return <ItemDataBeans>
	 * @throws SQLException
	 */
	public static ArrayList<MyItemDataBeans> getRandItem(int limit) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM item ORDER BY RAND() LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();

			ArrayList<MyItemDataBeans> itemList = new ArrayList<MyItemDataBeans>();

			while (rs.next()) {
				MyItemDataBeans item = new MyItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setGender(rs.getInt("gender"));
				item.setCategory(rs.getString("category"));
				item.setDetail(rs.getString("item_detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("image"));
				itemList.add(item);
			}
			System.out.println("getAllItem completed");
			return itemList;
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
	 * 商品IDによる商品検索
	 * @param itemId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static MyItemDataBeans getItemByItemID(int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM item inner join category on item.category = category.id WHERE item.id = ?");
			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();

			MyItemDataBeans item = new MyItemDataBeans();
			if (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setGender(rs.getInt("gender"));
				item.setCategory(rs.getString("category_name"));
				item.setDetail(rs.getString("item_detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("image") != null ? rs.getString("image"):"img/fuku_tatamu.png");
			}

			System.out.println("searching item by itemID has been completed");

			return item;
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
	 * 商品検索
	 * @param searchWord
	 * @param pageNum
	 * @param pageMaxItemCount
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<MyItemDataBeans> getItemsByItemName(String searchWord, int pageNum, int pageMaxItemCount)
			throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startiItemNum = (pageNum - 1) * pageMaxItemCount;
			con = MyDBManager.getConnection();

			if (searchWord.length() == 0) {
				// 全検索
				st = con.prepareStatement("SELECT * FROM m_item ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startiItemNum);
				st.setInt(2, pageMaxItemCount);
			} else {
				// 商品名検索
				st = con.prepareStatement("SELECT * FROM m_item WHERE name like ?  ORDER BY id ASC LIMIT ?,? ");
				st.setString(1, "%" + searchWord + "%");
				st.setInt(2, startiItemNum);
				st.setInt(3, pageMaxItemCount);
			}

			ResultSet rs = st.executeQuery();
			ArrayList<MyItemDataBeans> itemList = new ArrayList<MyItemDataBeans>();

			while (rs.next()) {
				MyItemDataBeans item = new MyItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("image"));
				itemList.add(item);
			}
			System.out.println("get Items by itemName has been completed");
			return itemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	public static ArrayList<MyItemDataBeans> getAllItem() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement(
					"SELECT item.id, item.name, item.gender, category.category_name, item.price FROM item inner join category on item.category = category.id");
			ResultSet rs = st.executeQuery();
			ArrayList<MyItemDataBeans> itemList = new ArrayList<MyItemDataBeans>();

			while (rs.next()) {
				MyItemDataBeans item = new MyItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setGender(rs.getInt("gender"));
				item.setCategory(rs.getString("category_name"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));

				itemList.add(item);

			}
			System.out.println("get Items by itemName has been completed");
			return itemList;

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
	 * 商品総数を取得
	 *
	 * @param searchWord
	 * @return
	 * @throws SQLException
	 */
	public static double getItemCount(String searchWord) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from m_item where name like ?");
			st.setString(1, "%" + searchWord + "%");
			ResultSet rs = st.executeQuery();
			double coung = 0.0;
			while (rs.next()) {
				coung = Double.parseDouble(rs.getString("cnt"));
			}
			return coung;
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