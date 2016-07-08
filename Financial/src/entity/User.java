package entity;

public class User {
	private int ID = 0;
	private String name;
	private String password;
	private String mail;
	private String image;
	public User(){
		
	}
	
	public User(int ID){
		this.ID=ID;
	}
	
	public User(String name){
		this.name=name;
	}

	public User(int ID, String password) {
		this.ID=ID;
		this.password=password;
	}
	
	public User(String name, String password) {		
		this.name=name;
		this.password=password;
	}

	public User(int ID, String name,String password) {
		this.ID=ID;
		this.name=name;
		this.password=password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
