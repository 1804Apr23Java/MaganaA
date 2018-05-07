package dao;

import java.util.HashMap;

public interface BankAccountDao {

	public HashMap<Integer, Float> getAccountsByUser(String username);
	
	public void addAccount(float accountBalance, String accountName);
	
	public boolean checkEmpty(int account_id);
	
	public boolean deleteAccount(int account_id);
	
	
}
