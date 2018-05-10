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

			st = con.prepareStatement(
					"SELECT * FROM item inner join category on item.category = category.id WHERE item.id = ?");
			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();

			MyItemDataBeans item = new MyItemDataBeans();
			if (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setGender(rs.getInt("gender"));
				item.setCategoryId(rs.getInt("category"));
				item.setCategory(rs.getString("category_name"));
				item.setDetail(rs.getString("item_detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("image") != null ? rs.getString("image") : "fuku_tatamu.png");
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

	//性別ごとにカテゴリー取得
	public static ArrayList<MyItemDataBeans> getCategoryByGender(int gender) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			if(gender == 2) {
				st = con.prepareStatement("SELECT * FROM category");
			} else {
				st = con.prepareStatement("SELECT * FROM category WHERE gender = ?");
				st.setInt(1, gender);
			}
			ResultSet rs = st.executeQuery();

			ArrayList<MyItemDataBeans> cateList = new ArrayList<MyItemDataBeans>();
			while (rs.next()) {
				MyItemDataBeans gcate = new MyItemDataBeans();
				gcate.setId(rs.getInt("id"));
				gcate.setName(rs.getString("category_name"));
				gcate.setGender(rs.getInt("gender"));

				cateList.add(gcate);
			}
			System.out.println("searching category by gender has been completed");
			return cateList;
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

	//アイテムリストの取得
	public static ArrayList<MyItemDataBeans> getAllItem() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement(
					"SELECT item.id, item.category, item.name, item.gender, category.category_name, item.price FROM item inner join category on item.category = category.id");
			ResultSet rs = st.executeQuery();
			ArrayList<MyItemDataBeans> itemList = new ArrayList<MyItemDataBeans>();

			while (rs.next()) {
				MyItemDataBeans item = new MyItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setGender(rs.getInt("gender"));
				item.setCategory(rs.getString("category_name"));
				item.setCategoryId(rs.getInt("category"));
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

	//商品情報の更新
	public void itemUpdate(MyItemDataBeans updateidb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = MyDBManager.getConnection();
			st = con.prepareStatement(
					"update item set name=?, gender=?, category=?, item_detail=?, price=?, image=? where id=?");

			st.setString(1, updateidb.getName());
			st.setInt(2, updateidb.getGender());
			st.setInt(3, updateidb.getCategoryId());
			st.setString(4, updateidb.getDetail());
			st.setInt(5, updateidb.getPrice());
			st.setString(6, updateidb.getFileName());
			st.setInt(7, updateidb.getId());

			st.executeUpdate();
			System.out.println("update item has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//商品削除
	public void itemDelete(int id) throws SQLException {

		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			st = con.prepareStatement(
					"delete from item where id = ?");

			st.setInt(1, id);
			st.executeUpdate();
			System.out.println("delete item has been completed");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//商品新規登録
	public void addNewItem(MyItemDataBeans newItem) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = MyDBManager.getConnection();
			st = con.prepareStatement("INSERT INTO item(name,gender,category,item_detail,price,image) VALUES(?,?,?,?,?,?)");

			st.setString(1, newItem.getName());
			st.setInt(2, newItem.getGender());
			st.setInt(3, newItem.getCategoryId());
			st.setString(4, newItem.getDetail());
			st.setInt(5, newItem.getPrice());
			st.setString(6, newItem.getFileName());
			st.executeUpdate();
			System.out.println("inserting item has been completed");
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