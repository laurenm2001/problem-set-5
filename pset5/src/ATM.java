/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ATM {
	
	private BankAccount account;
	private Database database;
	private BankAccount destination;
	
	public ATM() throws FileNotFoundException, IOException{
		this.destination = null;
		this.database = new Database("accounts-db.txt");
	}
	public BankAccount getBankAccount() {
		return account;
	}
	public ATM(BankAccount account) {
		this.account = account;
	}
	public ATM(Database database) {
		this.database = database;
	}
	public static String rPAD(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}
	public void Menu() throws IOException{
		Scanner in = new Scanner(System.in);
		Scanner textscan= new Scanner(System.in);
		int pin = 0;
		long acc = 0;
		int returnval = 0;
		int firstchoice = 0;
		System.out.println("Welcome to APCSA ATM, the ATM of choice for the UCVTS community. Would you like to:\n	[1] Open an account\n	[2] Log into an existing account\n	[3] Close an account ");
		while(firstchoice != 1 || firstchoice !=2 || firstchoice != 3) {
		firstchoice = in.nextInt();
		if(firstchoice == 2) {
			System.out.print("Account # : ");
			acc = in.nextLong();
			account = database.getAccount(acc);
			
			System.out.print("Pin # : ");
			pin = in.nextInt();

			while(account == null || pin != account.getUser().getPIN() || acc != account.getAccountNumber() || account.getUser().getOpen().equals("N")){
				System.out.println("Please input the right Account Number and Pin");
				System.out.print("Account # : ");
				acc = in.nextLong();
				account = database.getAccount(acc);
				
				System.out.print("Pin # : ");
				pin = in.nextInt();
			}
				int option = 0;
				while(option != 6) {
				System.out.println
						("[1] Deposit\n" + 
						"[2] Withdraw\n" + 
						"[3] View Balance\n" + 
						"[4] Transfer funds\n" +
						"[5] Update info\n" +
						"[6] Exit\n");
				
					option = in.nextInt();
					if(option == 1) {
						System.out.println("How much do you want to deposit?");
						double amount = in.nextDouble();
						returnval = account.deposit(amount);
						if(returnval == 1){
							System.out.println(account.getBalance());
						}else {
							System.out.println("You have to deposit an amount greater than 0");
						}
					}else if(option == 2) {
						System.out.println("How much do you want to withdraw?");
						int amount = in.nextInt();
						returnval = account.withdraw(amount);
						if (returnval == 2) {
				
							System.out.println(account.getBalance());}
						else {
							System.out.println("You cannot withdraw more than your account holds and you must withdraw an amount greater than 0.");
						}
					}else if(option == 3) {
						System.out.println(account.getBalance());
					}else if(option == 4) {
						System.out.println("Enter account number to receive funds");
						long accreceive = in.nextLong();
						destination = database.getAccount(accreceive);
						System.out.println("Enter an amount");
						int amount = in.nextInt();
						if(destination != null) {
							returnval = account.transfer(amount, destination);
							if(returnval == 1) {
								System.out.println("Your balance: " + account.getBalance());
							}
						}else {
							System.out.println("Invalid account");
							}
					}else if(option == 5) {
						System.out.println("What would you like to update?");
						System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						int newOpt = 0;
						while(newOpt != 7) {
						newOpt = in.nextInt();
						if(newOpt == 1){
							System.out.println("Please enter PIN");
							int pinA = in.nextInt();
							if(pinA == account.getUser().getPIN()) {
								System.out.println("Please enter new PIN");
								int newPin = in.nextInt();
								account.getUser().setPIN(newPin);
								System.out.println("Choose another option now");
								System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");

							}else {
								System.out.println("I cannot make this change, you have entered the wrong PIN\nPlease choose a different option");
								System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
							}
						}else if(newOpt == 2){
							System.out.println("New Phone:");	
							int newtel = in.nextInt();
							account.getUser().setTelephone(newtel);
							System.out.println("Choose another option now");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}else if(newOpt == 3){
							String newadd;
							do {
								System.out.println("New Address:");
								newadd = in.nextLine();
								
							} while (newadd.length() > 30);
							account.getUser().setAddress(newadd);
							System.out.println("Choose another option now");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}else if(newOpt == 4){
							String newcity = in.nextLine();
							do {
								System.out.println("New City:");
								newcity = in.nextLine();
								
							} while (newcity.length() > 30);
							account.getUser().setCity(newcity);
							System.out.println("Choose another option now");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}if(newOpt == 5){
							String newstate = in.nextLine();
							do {
								System.out.println("New State:");
								newstate = in.nextLine();
								
							} while (newstate.length() > 2);
							account.getUser().setState(newstate);
							System.out.println("Choose another option now");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}if(newOpt == 6){
							String newpost = in.nextLine();
							do {
								System.out.println("New Postal Code:");
								newpost = in.nextLine();
								
							} while (newpost.length() > 5);
							account.getUser().setPostalCode(newpost);
							System.out.println("Choose another option now");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}else if(newOpt == 7) {
							System.out.println("Your files were Updated!");
						}
						else{
							System.out.println("That is not an option");
							System.out.println("[1] PIN: "+ account.getUser().getPIN()+"\n[2] Phone #: " + account.getUser().getTelephone() + "\n[3] Address: " + account.getUser().getAddress() + "\n[4] City: " + account.getUser().getCity() +"\n[5] State: " + account.getUser().getState() +"\n[6] Postal Code: " + account.getUser().getPostalCode() + "\n[7] Return to Main Menu");
						}
						}
					}else if (option == 6) {
						System.out.println("Goodbye!");
						database.updateAccount(account, destination);
						break;
					}else {
						System.out.println("Please input one of the 6 options");
					}
				}
			
		}else if(firstchoice == 1) {
			
			String firstname;
			String telephone;
			String address;
			String city;
			String state;
			String postalcode;
			String dob;
			String pinnum;
			String lastname;
			String balance = "0";
			System.out.println("Welcome to APCSA Bank! Please enter the following information to start your account!");
			
			
			do {
				
				System.out.println("Please input your last name. It shouldnt be more than 20 characters\nLast Name: ");
				String lname = textscan.nextLine();
				lastname = lname;
			} while (lastname.length() > 20);
			lastname = rPAD(lastname, 20);
			
			
			
			do {
				
				System.out.println("Please input your first name. It shouldnt be more than 15 characters\nFirst Name: ");
				firstname = textscan.nextLine();
				
			} while (firstname.length() > 15);
			firstname = rPAD(firstname, 15);
			
			do {
				
				System.out.println("Please input your phone number. It shouldnt be more than 10 characters\nPhone #: ");
				telephone = textscan.nextLine();
				
			} while (telephone.length() > 10);
			telephone = rPAD(telephone, 10);
			
			do {
				
				System.out.println("Please input your address. It shouldnt be more than 30 characters\nAddress: ");
				address = textscan.nextLine();
				
			} while (address.length() > 30);
			address = rPAD(address, 30);
			do {
				
				System.out.println("Please input your city. It shouldnt be more than 30 characters\nCity: ");
				city = textscan.nextLine();
				
			} while (city.length() > 30);
			city = rPAD(city, 30);
			do {
				
				System.out.println("Please input your state abbreviation. It should be exactly 2 characters\nState: ");
				state = textscan.nextLine();
				
			} while (state.length() != 2);
			state = rPAD(state, 2);
			do {
				
				System.out.println("Please input your postal code. It should be exactly 5 characters\nPostal Code: ");
				postalcode = textscan.nextLine();
				
			} while (postalcode.length() != 5);
			postalcode = rPAD(postalcode, 5);
			do {
				
				System.out.println("Please input your date of birth. It should be formatted YYYYMMDD\nDOB: ");
				dob = textscan.nextLine();
				
			} while (dob.length() != 8);
			dob = rPAD(dob, 8);
			do {
				
				System.out.println("Please input your pin number. It should be 4 characters exactly\nPin #: ");
				pinnum = textscan.nextLine();
				
			} while (pinnum.length() != 4);
			pinnum = rPAD(pinnum, 4);
			balance = rPAD(balance, 15);
			
			long accountnumber = database.getMaxAccountNumber() + 1;
			
			BankAccount newacc = new BankAccount(accountnumber + pinnum + balance + lastname + firstname + dob + telephone + address + city + state + postalcode + "Y");
			database.updateAccount(newacc, destination);
			
			System.out.println("Your account number is: " + accountnumber);
				
		}else if(firstchoice == 3) {
			System.out.println("What is your account that you would like to be closing?");
			System.out.print("Account # : ");
			acc = in.nextLong();
			account = database.getAccount(acc);
			
			System.out.print("Pin # : ");
			pin = in.nextInt();

			while(account == null || pin != account.getUser().getPIN() || acc != account.getAccountNumber()){
				System.out.println("Please input the right Account Number and Pin");
				System.out.print("Account # : ");
				acc = in.nextLong();
				account = database.getAccount(acc);
				
				System.out.print("Pin # : ");
				pin = in.nextInt();
			}
			account.getUser().setOpen("N");
			System.out.println("Your account, " + account.getAccountNumber() + "is closed. Goodbye!");
			database.updateAccount(account, destination);
			break;
		}else {
			System.out.println("Please input one of the 3 options");
			
		}
		}
		in.close();
		textscan.close();
		
	}

}
