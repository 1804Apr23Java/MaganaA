package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;

import util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao {
	
	private String filename = "connectioninfo.properties";

	@Override
	public  HashMap<Integer, Float> getAccountsByUser(String username) {
		PreparedStatement pstmt = null;
		HashMap<Integer, Float> accounts = new HashMap<Integer, Float>();

		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "select bank_account_id, account_balance from bank_account where bank_account_id = (select bank_account_id from user_info where username = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("Does it go here?");
				int bank_id = rs.getInt("BANK_ACCOUNT_ID");
				float balance = rs.getFloat("ACCOUNT_BALANCE");
				accounts.put(bank_id, balance);
			}
			

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return accounts;
		
	}

	@Override
	public void addAccount(float accountBalance, String accountName) {

		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		int bank_account_id = 0;
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {

			String sql = "INSERT INTO BANK_ACCOUNT (BANK_ACCOUNT_NAME, ACCOUNT_BALANCE) VALUES(?,?)";
			String sql2 = "SELECT BANK_ACCOUNT_ID FROM BANK_ACCOUNT WHERE BANK_ACCOUNT_NAME = ?";
			String sql3 = "INSERT INTO USER_INFO(USERNAME, BANK_ACCOUNT_ID) VALUES (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountName);
			pstmt.setFloat(2, accountBalance);
			pstmt.executeQuery();
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, accountName);
			ResultSet rs2 = pstmt2.executeQuery();
		
			if(rs2.next()) {
				bank_account_id = rs2.getInt("BANK_ACCOUNT_ID");
			}
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setString(1, accountName);
			pstmt3.setInt(2, bank_account_id);

			pstmt.executeQuery();
			

			conn.close();
  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ;
	}

	@Override
	public boolean deleteAccount(int account_id) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		boolean deleted = false;
		try (Connection conn = ConnectionUtil.getConnectionFromFile(filename)) {
			//savepoint
			String sql1 = "DELETE FROM USER_INFO WHERE BANK_ACCOUNT_ID = ?";
			String sql2 = "DELETE FROM BANK_ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt2 = conn.prepareStatement(sql2);
			pstmt.setInt(1, account_id);
			pstmt2.setInt(1,account_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					deleted = true;

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
		return deleted;
	}

	@Override
	public boolean checkEmpty(int account_id) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnectionFromFile(filename);
			String sql = "SELECT ACCOUNT_BALANCE FROM BANK_ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				float account_balance = rs.getFloat("ACCOUNT_BALANCE");
				if (account_balance == 0)
					conn.close();
					return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
