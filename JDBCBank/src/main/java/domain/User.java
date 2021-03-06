package domain;
import java.util.HashMap;


public class User {
	
	private String userName;
	private String password;
	private HashMap<Integer,Float> accounts; //Name of account and Balance
	//might not need these, just for upload
	static int userID = 1;
	static int bankAccountID = 1001;
	
		
	public User(){
		super();
	}
	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
		this.accounts = new HashMap<Integer,Float>();
		//increment our counter java-side in place of sequences
		/*userID++;
		bankAccountID++;*/
		
		 
	}
	
	/*
	 * Take user info and push info to database
	 */
	protected void updateDatabase() {
		
	}
	
	public void displayAccounts()
	{
		System.out.println("Accounts : Balance");
		//uniform stars
		System.out.println("******************\n");
		for (int i : this.accounts.keySet()) {
			System.out.println("Acct "+i+ " : "+ this.accounts.get(i));
		}
	}
	
	public void setAccounts(HashMap<Integer,Float> accounts)
	{
		this.accounts = accounts;
	}
	
	public void displayMenu() {
		System.out.println("\nSecond National Bank Menu");
		System.out.println("0: View account(s)");
		System.out.println("1: Create account");
		System.out.println("2: Delete account");
		System.out.println("3: Add/Withdraw from account");
		System.out.println("4: logout");
		System.out.print("Please enter a number: ");
		
		
		 
	}
	public String getUserame() {
		return this.userName;
	}

}
