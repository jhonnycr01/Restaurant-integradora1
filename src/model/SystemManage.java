package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SystemManage {

	private String id;
	private ArrayList <Restaurant> restaurants;
//wekete
	public SystemManage(String id) {
		this.id=id;
		this.restaurants =  new ArrayList<>();
		deserializeRestaurants();
	}
	
	public void addRestaurant(String name, String nit, String administratorName, Product products, Client clients, Order orders) {
		Restaurant r = new Restaurant(name, nit, administratorName, products, clients, orders);
		restaurants.add(r);
		serializeRestaurants();
	}

	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(ArrayList<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	public void updateRestaurant(String name, String nit, String administratorName) {
		Restaurant r= null;
		for (int i = 0; i < restaurants.size(); i++) {
			if(nit==restaurants.get(i).getNit()) {
				r = restaurants.get(i);
				r.setAdministratorName(administratorName);
				r.setName(administratorName);
				break;
			}
		}
		
	
	}
	
	public void serializeRestaurants() {
		try {

			File f = new File("serialized/" +this.id);
			ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream(f));
			fichero.writeObject(restaurants);
			//esto se puede borrar, si funciona debera imprimirse
			System.out.println("the system saved the data from the club : " + id);
			System.out.println();
			fichero.close();

		} catch (Exception e1) {
			System.out.println("no se pudo serializar");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeRestaurants() {

		System.out.println("loading data ....");
		try {
			File f = new File("serialized/" + this.id);

			ObjectInputStream fichero_recuperado = new ObjectInputStream(new FileInputStream(f));
			restaurants = (ArrayList<Restaurant>) fichero_recuperado.readObject();

			fichero_recuperado.close();
		} catch (Exception e) {
			System.out.println("No se pudo deserializar");
		}

	}
	
}

