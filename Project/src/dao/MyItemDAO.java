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
	public static ArrayList<MyItemDataBeans> getRandItem(int limit, int genderId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			if (genderId == 2) {
				st = con.prepareStatement("SELECT * FROM item ORDER BY RAND() LIMIT ? ");
				st.setInt(1, limit);
			} else {
				st = con.prepareStatement("SELECT * FROM item WHERE gender=? ORDER BY RAND() LIMIT ? ");
				st.setInt(1, genderId);
				st.setInt(2, limit);
			}

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
				item.setFileName(rs.getString("image") != null ? rs.getString("image") : "fuku_tatamu.png");
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

	//カテゴリーでランダムにアイテム取得
	public static ArrayList<MyItemDataBeans> getRandItemByCategory(int limit, int categoryId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM item WHERE category=? ORDER BY RAND() LIMIT ? ");
			st.setInt(1, categoryId);
			st.setInt(2, limit);
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
				item.setFileName(rs.getString("image") != null ? rs.getString("image") : "fuku_tatamu.png");
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
			if (gender == 2) {
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
	public ArrayList<MyItemDataBeans> getItems(String searchWord, int categoryId, int genderId, int sortId, int page,
			int pageMaxItemCount)
			throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			int startiItemNum = (page - 1) * pageMaxItemCount;
			con = MyDBManager.getConnection();
			switch (sortId) {
			case 0:
				if (genderId != 2 || categoryId != 0) {
					if (genderId != 2 && categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE gender=? and category=? ORDER BY id ASC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? and category=?  ORDER BY id ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, categoryId);
							st.setInt(4, startiItemNum);
							st.setInt(5, pageMaxItemCount);
						}
					} else if (genderId != 2) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement("SELECT * FROM item WHERE gender=? ORDER BY id ASC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? ORDER BY id ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					} else if (categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement("SELECT * FROM item WHERE category=? ORDER BY id ASC LIMIT ?,? ");
							st.setInt(1, categoryId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and category=? ORDER BY id ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					}
				} else {
					if (searchWord.length() == 0) {
						// 全検索
						st = con.prepareStatement("SELECT * FROM item ORDER BY id ASC LIMIT ?,? ");
						st.setInt(1, startiItemNum);
						st.setInt(2, pageMaxItemCount);
					} else {
						// 商品名検索
						st = con.prepareStatement("SELECT * FROM item WHERE name like ?  ORDER BY id ASC LIMIT ?,? ");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, startiItemNum);
						st.setInt(3, pageMaxItemCount);
					}
				}

				break;
			case 1:
				if (genderId != 2 || categoryId != 0) {
					if (genderId != 2 && categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE gender=? and category=? ORDER BY price DESC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? and category=?  ORDER BY price DESC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, categoryId);
							st.setInt(4, startiItemNum);
							st.setInt(5, pageMaxItemCount);
						}
					} else if (genderId != 2) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE gender=? ORDER BY price DESC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? ORDER BY price DESC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					} else if (categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE category=? ORDER BY price DESC LIMIT ?,? ");
							st.setInt(1, categoryId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and category=? ORDER BY price DESC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					}
				} else {
					if (searchWord.length() == 0) {
						// 全検索
						st = con.prepareStatement("SELECT * FROM item ORDER BY price DESC LIMIT ?,? ");
						st.setInt(1, startiItemNum);
						st.setInt(2, pageMaxItemCount);
					} else {
						// 商品名検索
						st = con.prepareStatement(
								"SELECT * FROM item WHERE name like ?  ORDER BY price DESC LIMIT ?,? ");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, startiItemNum);
						st.setInt(3, pageMaxItemCount);
					}
				}
				break;
			case 2:
				if (genderId != 2 || categoryId != 0) {
					if (genderId != 2 && categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE gender=? and category=? ORDER BY price ASC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? and category=?  ORDER BY price ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, categoryId);
							st.setInt(4, startiItemNum);
							st.setInt(5, pageMaxItemCount);
						}
					} else if (genderId != 2) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE gender=? ORDER BY price ASC LIMIT ?,? ");
							st.setInt(1, genderId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and gender=? ORDER BY price ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, genderId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					} else if (categoryId != 0) {
						if (searchWord.length() == 0) {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE category=? ORDER BY price ASC LIMIT ?,? ");
							st.setInt(1, categoryId);
							st.setInt(2, startiItemNum);
							st.setInt(3, pageMaxItemCount);
						} else {
							st = con.prepareStatement(
									"SELECT * FROM item WHERE name like ? and category=? ORDER BY price ASC LIMIT ?,? ");
							st.setString(1, "%" + searchWord + "%");
							st.setInt(2, categoryId);
							st.setInt(3, startiItemNum);
							st.setInt(4, pageMaxItemCount);
						}
					}
				} else {
					if (searchWord.length() == 0) {
						// 全検索
						st = con.prepareStatement("SELECT * FROM item ORDER BY price ASC LIMIT ?,? ");
						st.setInt(1, startiItemNum);
						st.setInt(2, pageMaxItemCount);
					} else {
						// 商品名検索
						st = con.prepareStatement(
								"SELECT * FROM item WHERE name like ?  ORDER BY price ASC LIMIT ?,? ");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, startiItemNum);
						st.setInt(3, pageMaxItemCount);
					}
				}
				break;
			default:
				break;
			}
			ResultSet rs = st.executeQuery();
			ArrayList<MyItemDataBeans> itemList = new ArrayList<MyItemDataBeans>();

			while (rs.next()) {
				MyItemDataBeans item = new MyItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setGender(rs.getInt("gender"));
				item.setCategoryId(rs.getInt("category"));
				item.setDetail(rs.getString("item_detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("image") != null ? rs.getString("image") : "fuku_tatamu.png");
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
	public static double getItemCount(String searchWord, int categoryId, int genderId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = MyDBManager.getConnection();
			if (genderId != 2 || categoryId != 0) {
				if (genderId != 2 && categoryId != 0) {
					if (searchWord.length() == 0) {
						st = con.prepareStatement(
								"SELECT count(*) as cnt FROM item WHERE gender=? and category=?");
						st.setInt(1, genderId);
						st.setInt(2, categoryId);
					} else {
						st = con.prepareStatement(
								"SELECT count(*) as cnt FROM item WHERE name like ? and gender=? and category=?");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, genderId);
						st.setInt(3, categoryId);
					}
				} else if (genderId != 2) {
					if (searchWord.length() == 0) {
						st = con.prepareStatement("SELECT count(*) as cnt FROM item WHERE gender=?");
						st.setInt(1, genderId);
					} else {
						st = con.prepareStatement(
								"SELECT count(*) as cnt FROM item WHERE name like ? and gender=?");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, genderId);
					}
				} else if (categoryId != 0) {
					if (searchWord.length() == 0) {
						st = con.prepareStatement("SELECT count(*) as cnt FROM item WHERE category=?");
						st.setInt(1, categoryId);
					} else {
						st = con.prepareStatement(
								"SELECT count(*) as cnt FROM item WHERE name like ? and category=?");
						st.setString(1, "%" + searchWord + "%");
						st.setInt(2, categoryId);
					}
				}
			} else {
				if (searchWord.length() == 0) {
					// 全検索
					st = con.prepareStatement("SELECT count(*) as cnt FROM item");
				} else {
					// 商品名検索
					st = con.prepareStatement("select count(*) as cnt FROM item where name like ?");
					st.setString(1, "%" + searchWord + "%");
				}
			}

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
			st = con.prepareStatement(
					"INSERT INTO item(name,gender,category,item_detail,price,image) VALUES(?,?,?,?,?,?)");

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