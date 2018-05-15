package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import base.MyDBManager;
import beans.MyCategoryDataBeans;

public class MyCategoryDAO {

	public static MyCategoryDAO getInstance() {
		return new MyCategoryDAO();
	}

	public static MyCategoryDataBeans getCategory(int categoryId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {

			con = MyDBManager.getConnection();
			st = con.prepareStatement(
					"SELECT * FROM category WHERE id = ?");
			st.setInt(1, categoryId);

			ResultSet rs = st.executeQuery();

			MyCategoryDataBeans category = new MyCategoryDataBeans();
			if (rs.next()) {
				category.setId(rs.getInt("id"));
				category.setCategoryName(rs.getString("category_name"));
				category.setGenderId(rs.getInt("gender"));
			}

			return category;
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
