package dao;

public interface UserInfoDao {

	public boolean checkIfUserExists(String username);
	public boolean loginUser(String username, String password);
	public void addNewUser(String username, String password);
	
}
