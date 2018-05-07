package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.ConnectionUtil;

public class UserInfoDaoImpl implements UserInfoDao {

	private String filename = "connectioninfo.properties";

	public boolean checkIfUserExists(String username) {

		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT USERNAME FROM LOGIN_INFO WHERE username = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				return true;

			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void addNewUser(String username, String password) {

		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "INSERT INTO LOGIN_INFO VALUES(?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.executeQuery();

			conn.close();
  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean loginUser(String username, String password) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "SELECT PASSWORD FROM LOGIN_INFO WHERE username = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("PASSWORD").equals(password)) {

					return true;

				}
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
