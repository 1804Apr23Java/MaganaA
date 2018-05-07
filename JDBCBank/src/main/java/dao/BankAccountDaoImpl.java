package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao {

	@Override
	public  HashMap<String, Integer> getAccountsByUser() {
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

}
