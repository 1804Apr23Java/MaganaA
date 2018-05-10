import java.util.Scanner;

import dao.BankAccountDao;
import dao.BankAccountDaoImpl;
import dao.UserInfoDao;
import dao.UserInfoDaoImpl;
import domain.User;

public class Console {

	public static void main(String[] args) {
		startUp();

	}

	public static void startScreenPrompt() {
		System.out.println("Type \"NEW\" to create new account.");
		System.out.println("Type \"LOGIN\" to log as existing user.");
		System.out.print("Input: ");
	}
	
	public static void menu(User user) {
		Scanner sc = new Scanner(System.in);
	
		String answer = "";
		while (!answer.equals("4")) {
		user.displayMenu();
		answer = sc.nextLine();
		
    	BankAccountDao bad1 = new BankAccountDaoImpl();
        switch(answer.charAt(0)) {
            case '0': //displays existing accounts and balances
    			System.out.println("***Retrieving info***\n");
    			user.setAccounts(bad1.getAccountsByUser(user.getUserame()));
            	bad1.getAccountsByUser(user.getUserame());
            	user.displayAccounts();
            	System.out.println();
                break;

            case '1': //create an account
            	System.out.println("What would you like to name your account?");
            	String accountName = sc.nextLine();
            	bad1.addAccount(0.0F,accountName);
            	System.out.println("New Account Created");
                break;

            case '2': //delete an account only if empty
            	user.displayAccounts();
            	System.out.println("Which account would you like to delete?");
            	int account_id = Integer.parseInt(sc.nextLine());
            	if(bad1.checkEmpty(account_id)) {
            		bad1.deleteAccount(account_id);
                	System.out.println("Acct " + account_id + " was deleted");
            	}
            	else
            		System.out.println("Whoops! Withdraw money first.");
                break;

            case '3': //add or withdraw from an account
            	System.out.println("Please come in person");
                break;

            case '4': //log out
            	System.out.println("Thank you for banking with Second National Bank \n We are second, so you can be first!");
            	System.out.println("****************************\n");
            	startUp();
                break;
            default:
		
		}
		}
	}
	

	public static void startUp() {
		UserInfoDao uid = new UserInfoDaoImpl();
		Scanner sc = new Scanner(System.in);

		// start up screen user response
		startScreenPrompt();
		String userInput = sc.nextLine();

		String username = "";
		String password = "pass1";
		String password2 = "pass2";
		boolean isValidUser = false;
		boolean userExists = true;

		//
		// responses to Start menu
		//
		if (userInput.equalsIgnoreCase("New")) {

			//
			// validate user name
			System.out.println("Great! Please Enter a username and password\n");
			while (userExists) {
				System.out.print("Username: ");
				username = sc.nextLine();
				userExists = uid.checkIfUserExists(username);
				if(userExists) {
					System.out.println("Username Already Taken, Please try another");
				}
				
			}

			//
			// loops new password verification
			// might need an escape; could do exception for counter
			for (int i = 3; !password.equals(password2) && i > 0; i--) {
				System.out.print("Password: "); // trim whitespace
				password = sc.nextLine();
				System.out.print("Confirm password: ");
				password2 = sc.nextLine();
				if (!password.equals(password2) && i > 1) {
					System.out.println("Sorry, please re-enter your password");
				}
			}

			//
			// restarts to start up menu
			if (!password.equals(password2)) {
				System.out.println("Account creation failed\n");
				startUp(); // possible exception
			}
			isValidUser = true; // poll the database for user name, return true if not found
			System.out.println("Account being created, Please wait...");
			uid.addNewUser(username, password);
			menu( new User(username, password));

			//mainUser.displayAccounts();
			//System.out.println("");
			//mainUser.displayMenu();

		} else if (userInput.equalsIgnoreCase("login")) {
			
			
			
			for(int i = 3;!isValidUser && i > 0;i--) {
				System.out.println("Okay, Please Enter a username and password\n");
				System.out.print("Username: ");
				username = sc.nextLine();
				System.out.print("Password: ");
				password = sc.nextLine();
				// check to see if login works
				
				System.out.println("You are being logged in, Please wait...");
				isValidUser = uid.loginUser(username, password);
				
				if (!isValidUser && i > 0) {
					System.out.println("Username/password combination not found");
				}
				
			}
			if (!isValidUser) {
				System.out.println("Account not found\n");
				startUp(); // possible exception
			}
			
			BankAccountDao bad = new BankAccountDaoImpl();
			User you = new User(username, password);
			System.out.println("***Retrieving info***\n");
			you.setAccounts(bad.getAccountsByUser(username));
			
			menu(you);
			

		} else {
			System.out.println("Invalid Entry, Please Try again\n");
			System.out.println("**************************\n");
			startUp();
		}
		sc.close();

	}

	
}
