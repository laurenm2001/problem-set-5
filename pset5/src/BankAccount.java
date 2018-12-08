import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {

	//private static long generatedAccountNumber = 100000001;
	
	private long accountNumber;
	private double balance;
	private User user;
	private String close;

	public BankAccount(String account) {
		this.accountNumber = Long.parseLong(account.substring(0, 9));
		
		this.user = new User(
			Integer.parseInt(account.substring(9, 13)),
			account.substring(28, 48),
			account.substring(48, 63),
			account.substring(63, 71),
			Long.parseLong(account.substring(71, 81)),
			account.substring(81, 111),
			account.substring(111, 141),
			account.substring(141, 143),
			account.substring(143, 148),
			account.substring(148, 149));
		this.balance = Double.parseDouble(account.substring(13, 28));	
			
	}
	
	public long getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public User getUser() {
		return user;
	}
	public String getClose() {
		return close;
	}
	
	
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/*public void setClose(String close) {
		this.close = close;
	}*/
	
	public int deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			setBalance(getBalance()+amount);
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			setBalance(getBalance()-amount);
			
			return 2;
		}
	}
	
	public int transfer(int amount, BankAccount accreceive) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return -1;
		}  else {
			this.withdraw(amount);
			accreceive.deposit(amount);
			return 1;
		}
	}
	@Override
	public String toString() {
		return String.format("%09d%04d%-15.2f%-20s%-15s%-8s%10d%-30s%-30s%-2s%-5s%s", accountNumber, user.getPIN(), balance, user.getLastName(),  user.getFirstName(), user.getDOB(), user.getTelephone(), user.getAddress(), user.getCity(), user.getState(), user.getPostalCode(), user.getOpen());
	}
	

	
}
