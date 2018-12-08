public class User {
	private String firstName;
	private String lastName;
	private int pin;
	private String dob;
	private long telephone;
	private String address;
	private String city;
	private String state;
	private String postalCode;
	private String open;
	
	
	
	public User(int pin, String lastName, String firstName, String dob, long telephone, String address, String city, String state, String postalCode, String open) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.dob = dob;
		this.telephone = telephone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.setOpen(open);
	}
	
	
	
	public int getPIN() {
		return pin;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	
	public String getDOB() {
		return dob;
	}

	public long getTelephone() {
		return telephone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPIN(int pin) {
		this.pin = pin;
	}
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public void setDOB(String dob) {
		this.dob = dob;
	}
	
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public String getOpen() {
		return open;
	}



	public void setOpen(String open) {
		this.open = open;
	}
}
