package model;

public class Client {

	private String id;
	private String idType;
	private String name;
	private String adress;
	private double phone;
	
	public Client(String id, String idType, String name, String adress, double phone) {
		this.id = id;
		this.idType = idType;
		this.name = name;
		this.adress = adress;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public double getPhone() {
		return phone;
	}

	public void setPhone(double phone) {
		this.phone = phone;
	}


}
	
