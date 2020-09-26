package model;

public class Restaurant {

	private String name; 
	private String nit;
	private String administratorName;

	public Restaurant(String name, String nit, String administratorName) {
		this.name = name;
		this.nit = nit;
		this.administratorName = administratorName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getAdministratorName() {
		return administratorName;
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
}