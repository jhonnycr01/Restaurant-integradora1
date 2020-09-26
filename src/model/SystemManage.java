package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SystemManage {

	private String id;
	
	private ArrayList <Restaurant> restaurants;
	private ArrayList <Product> products;
	private ArrayList <Client> clients;
	private ArrayList <Order> orders;
	
	public final static String PATH_RESTAURANTS = "files/restaurants.csv";
	public final static String PATH_PRODUCTS = "files/products.csv";
	public final static String PATH_CLIENTS = "files/clients.csv";
	public final static String PATH_ORDERS = "files/orders.csv";
	
	public SystemManage(String id) {
		this.id=id;
		this.restaurants =  new ArrayList<>();
		this.products = new ArrayList<>();
		this.clients = new ArrayList<>();
		this.orders = new ArrayList<>();
		deserializeRestaurants();
		//deserializeRestaurants();
	}
	
	public ArrayList<Restaurant> getRestaurants() {
		return restaurants;
	}
	
	public ArrayList <Product> getProducts(){
		return products;
	}
	
	public ArrayList <Client> getClients(){
		return clients;
	}
	
	public ArrayList <Order> getOrders(){
		return orders;
	}
	
	public void addRestaurant(Restaurant restaurant){
		restaurants.add(restaurant);
		serializeRestaurants();
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
		serializeProducts();
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
		serializeClients();
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
		serializeOrders();
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
	
	public void updateOrder(Order code) {
		
		
	}
	
	public void updateProduct(String id, String name, String description, double cost, String nit) {
		Product p = null;
		for (int i = 0; i < products.size(); i++) {
			if(id==products.get(i).getCode()) {
				p = products.get(i);
				break;
			}
		}
		p.setCost(cost);
		p.setDescription(description);
		p.setName(name);
		p.setNit(nit);
		serializeProducts();
	}
	
	public void updateClient(String id, String idType, String name, String adress, String phone) {
		 Client c = null;
		 for (int i = 0; i < clients.size(); i++) {
				if(id==clients.get(i).getId()) {
				
					clients.get(i).setAdress(adress);
					clients.get(i).setIdType(idType);
					clients.get(i).setName(name);
					clients.get(i).setPhone(phone);
					serializeClients();
					break;
				}
		 }
		 
	}
	
	public void updateOrder(String orderCode, LocalDateTime date, String clientCode, String resturantNit) {
		Order o = null;
		for (int i = 0; i < orders.size(); i++) {
			if(orderCode==orders.get(i).getOrderCode()) {
				o = orders.get(i);
				break;
			}
		}
		o.setClientCode(clientCode);
		o.setDate(date);
		o.setResturantNit(resturantNit);
	}
	
	public boolean validateProduct(Product product) {
		boolean answer=false;
		for(int i=0; i<products.size(); i++) {
			if(products.get(i)==product) {
				answer=true;
			}
		}
		return answer;
	}
	
	public void updateOrderState(String code) {
		Order or=null;
		or = searchOrderByCode(code);
		
		if(or.getOrderState().equalsIgnoreCase("request")) {
			or.setOrderState("in progress");
		}
		else if(or.getOrderState().equalsIgnoreCase("in progress")) {
			or.setOrderState("sent");
		}
		else if(or.getOrderState().equalsIgnoreCase("sent")) {
			or.setOrderState("delivered");
		}
	}
		
	
	public Order searchOrderByCode(String code) {
		Order or=null;
		
		for (int i = 0; i < orders.size(); i++) {
			if(code.equalsIgnoreCase(orders.get(i).getOrderCode())) {
				or = orders.get(i);
				break;
			}
		}
			return or;
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
			File f = new File("serialized/restaurants" + this.id);

			ObjectInputStream fichero_recuperado = new ObjectInputStream(new FileInputStream(f));
			restaurants = (ArrayList<Restaurant>) fichero_recuperado.readObject();

			fichero_recuperado.close();
		} catch (Exception e) {
			System.out.println("No se pudo deserializar");
		}

	}
	
	public void serializeProducts() {
		try {
			File fileProducts = new File("serialized/products" +this.id);
			ObjectOutputStream ficheroPro = new ObjectOutputStream(new FileOutputStream(fileProducts));
			ficheroPro.writeObject(products);
			ficheroPro.close();
		} catch (IOException e) {
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeProducts() {
		try {
			File fileProducts = new File("serialized/products" +this.id);
			ObjectInputStream ficheroPro = new ObjectInputStream(new FileInputStream(fileProducts));
			products = (ArrayList<Product>) ficheroPro.readObject();
			ficheroPro.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
	
	public void serializeOrders() {
		try{
			File fileOrders = new File("serialized/orders" +this.id);
			ObjectOutputStream ficheroOrd = new ObjectOutputStream(new FileOutputStream(fileOrders));
			ficheroOrd.writeObject(orders);
			ficheroOrd.close();
		}
		catch (IOException e){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializaOrders() {
		try{ 
			File fileOrders = new File("serialized/orders" +this.id);
			ObjectInputStream ficheroOrd = new ObjectInputStream(new FileInputStream(fileOrders));
			orders = (ArrayList<Order>) ficheroOrd.readObject();
			ficheroOrd.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
	
	public void serializeClients() {
		try {
			File fileClients = new File("serialized/clients" +this.id);
			ObjectOutputStream ficheroCli = new ObjectOutputStream(new FileOutputStream(fileClients));
			ficheroCli.writeObject(clients);
			ficheroCli.close();
		}
		catch (IOException e ){
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeClients() {
		try {
			 File fileClients = new File("serializedClients/" +this.id);
			 ObjectInputStream ficheroCli = new ObjectInputStream(new FileInputStream(fileClients));
			 clients = (ArrayList<Client>) ficheroCli.readObject();
			 ficheroCli.close();
		}
		catch(IOException | ClassNotFoundException cnfe) {
			
		}
	}
		
	
	public String generateOrderReport() {
		String msg = "";
		for (int i = 0; i < orders.size(); i++) {
			msg += orders.get(i).getOrderCode() + "," +  orders.get(i).getDate() + "," + 
					"," + orders.get(i).getClientCode() + "," + orders.get(i).getResturantNit() + 
					"," + orders.get(i).getOrderState();
			for(int j = 0;j<orders.get(i).getProductList().size(); j++) {
				String product = orders.get(i).getProductList().get(j);
				String[] info = product.split(" ");
				msg += info[0] + "," + info[1] + "\n";
			}
		}
		return msg;
	}
	
	public void saveOrders() throws FileNotFoundException {
		File fileOrders = new File(PATH_ORDERS);
		PrintWriter pw = new PrintWriter(fileOrders);

		String repStr = generateOrderReport();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadOrders() throws FileNotFoundException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_ORDERS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(",");
				
				String ordercode = info[0]; 
				String date = info[1];
				String clientcode = info[2];
				String restaurantnit = info[3];
				String orderstate = info[4];
				
				Order order = new Order(ordercode,date,clientcode,restaurantnit, orderstate);
				//addOrder(order);
				
				line = br.readLine();
			}
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportProducts() {
		String msg = "";
		for (int i = 0; i < products.size(); i++) {
			// tring orderCode, Date date, Date time, String clientCode, String resturantNit
			msg += products.get(i).getCode() + "," +  products.get(i).getName() + "," + 
					"," + products.get(i).getDescription() + "," + products.get(i).getCost() + 
					"," + products.get(i).getNit() + "\n";
		}
		return msg;
	}
	
	public void saveProducts() throws FileNotFoundException {
		File fileProducts = new File(PATH_PRODUCTS);
		PrintWriter pw = new PrintWriter(fileProducts);

		String repStr = generateReportProducts();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadProducts() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_PRODUCTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String info[] = line.split(",");
				String code = info[0];
				String name = info[1];
				String description = info[2];
				double cost = Double.parseDouble(info[3]);
				String nit = info[4];
				
				Product product = new Product(code,name,description,cost,nit);
				addProduct(product);
				
				line = br.readLine();
			}
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportClients() {
		String msg = "";
		for (int i = 0; i < clients.size(); i++) {
			// tring orderCode, Date date, Date time, String clientCode, String resturantNit
			msg += clients.get(i).getId() + "," +  clients.get(i).getIdType() + "," + 
					"," + clients.get(i).getName() + "," + clients.get(i).getAdress() + 
					"," + clients.get(i).getPhone() + "\n";
		}
		return msg;
	}
	
	public void saveClients() throws FileNotFoundException {
		File fileClients = new File(PATH_CLIENTS);
		PrintWriter pw = new PrintWriter(fileClients);
		
		String repStr = generateReportProducts();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadClients() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_PRODUCTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(",");
				String id = info[0];
				String idtype = info[1];
				String name = info[2];
				String address = info[3];
				String phone = info[4];
				
				Client client = new Client(id,idtype,name,address,phone);
				addClient(client);
				line = br.readLine();
			}
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportRestaurants() {
		String msg = "";
		for (int i = 0; i < restaurants.size(); i++) {
			// tring orderCode, Date date, Date time, String clientCode, String resturantNit
			msg += restaurants.get(i).getNit() + "," +  restaurants.get(i).getAdministratorName() + "," + 
					"," + restaurants.get(i).getAdministratorName() + "\n";
		}
		return msg;
	}
	
	public void saveRestaurants() throws FileNotFoundException {
		File fileRestaurants = new File(PATH_RESTAURANTS);
		PrintWriter pw = new PrintWriter(fileRestaurants);
		
		String repStr = generateReportRestaurants();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadRestaurants() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH_RESTAURANTS)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(",");
				
				String name = info[0];
				String nit = info[1];
				String administrator = info[2];
				
				Restaurant restaurant = new Restaurant(name, nit, administrator);
				addRestaurant(restaurant);
				
				line = br.readLine();
			}
			
		}catch(Exception e){
			
		}
	}
}
