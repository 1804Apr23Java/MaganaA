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
		
		//download data
		//
		//
		//
		
		
		user.displayMenu();
		int answer;
		try {
		answer = sc.nextInt();
		}
		catch(Exception e) {
			//download data
			System.out.println("Invalid Input");
			user.displayMenu();
			answer = sc.nextInt();
		}
        switch(answer) {
            case 0: //displays existing accounts and balances
            	user.displayAccounts();
            	System.out.println();
                break;

            case 1: //create an account
            	user.addAccount();
                break;

            case 2: //delete an account only if empty
            	user.displayAccounts();
            	System.out.println("Which account would you like to delete?");
            	sc.nextInt();
                break;

            case 3: //add or withdraw from an account
            	user.display(1);
                break;

            case 4: //log out
            	System.out.println("Thank you for banking with Second National Bank \n We are second, so you can be first!");
            	startUp();
                break;
            default:
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
			sc.close();
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
			
			sc.close();
			BankAccountDao bad = new BankAccountDaoImpl();
			
			menu( new User(username, password));
			
			
			

		} else {
			System.out.println("Invalid Entry, Please Try again\n");
			System.out.println("**************************\n");
			startUp();
		}
		sc.close();

	}

	
}
