package model;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class Restaurant {

	private String name; 
	private String nit;
	private String administratorName;
	private ArrayList <Product> products;
	private ArrayList <Client> clients;
	private ArrayList <Order> orders;
	
	public final static String PATH_PRODUCTS = "files/products.csv";
	public final static String PATH_CLIENTS = "files/clients.csv";
	public final static String PATH_ORDERS = "files/orders.csv";

	
	public Restaurant(String name, String nit, String administratorName, Product products, Client clients, Order orders) {
		this.name = name;
		this.nit = nit;
		this.administratorName = administratorName;
		this.products = new ArrayList<>();
		this.clients = new ArrayList<>();
		this.orders = new ArrayList<>();
		deserializeRestaurants();

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
	
	public void addProduct(Product product) {
		this.products.add(product);
		serialize();
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
		serialize();
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
		serialize();
	}
	
	public void updateOrder(Order code) {
		
		
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
		serialize();
	}
	
	//update definitivo
	public void updateClient(String id, String idType, String name, String adress, double phone) {
		 Client c = null;
		 for (int i = 0; i < clients.size(); i++) {
				if(id==clients.get(i).getId()) {
				
					clients.get(i).setAdress(adress);
					clients.get(i).setIdType(idType);
					clients.get(i).setName(name);
					clients.get(i).setPhone(phone);
					 serialize();
					break;
				}
		 }
		 
	}
	
	public void updateOrder(String orderCode, Date date, Date time, String clientCode, String resturantNit) {
		Order o = null;
		for (int i = 0; i < orders.size(); i++) {
			if(orderCode==orders.get(i).getOrderCode()) {
				o = orders.get(i);
				break;
			}
		}
		o.setClientCode(clientCode);
		o.setDate(date);
		o.setTime(time);
		o.setResturantNit(resturantNit);
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
//	private ArrayList <Product> products;
//	private ArrayList <Client> clients;
//	private ArrayList <Order> orders;
	public void serialize() {
		try {
			
			//products
			File fileProducts = new File("serializedProducts/" +this.nit);
			ObjectOutputStream ficheroPro = new ObjectOutputStream(new FileOutputStream(fileProducts));
			ficheroPro.writeObject(products);
			
			//clients
			File fileClients = new File("serializedClients/" +this.nit);
			ObjectOutputStream ficheroCli = new ObjectOutputStream(new FileOutputStream(fileClients));
			ficheroCli.writeObject(clients);
			
			//orders
			File fileOrders = new File("serializedOrders/" +this.nit);
			ObjectOutputStream ficheroOrd = new ObjectOutputStream(new FileOutputStream(fileOrders));
			ficheroOrd.writeObject(orders);
			
			//esto se puede borrar, si funciona debera imprimirse
			System.out.println("the system saved the data from the restaurant : " + name);
			System.out.println();
			ficheroCli.close();
			ficheroOrd.close();
			ficheroPro.close();

		} catch (Exception e1) {
			System.out.println("no se pudo serializar");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserializeRestaurants() {

		System.out.println("loading data ....");
		try {
			File fileProducts = new File("serializedProducts/" +this.nit);
			ObjectInputStream ficheroPro = new ObjectInputStream(new FileInputStream(fileProducts));
			products = (ArrayList<Product>) ficheroPro.readObject();
			
			//clients
			File fileClients = new File("serializedClients/" +this.nit);
			ObjectInputStream ficheroCli = new ObjectInputStream(new FileInputStream(fileClients));
			clients = (ArrayList<Client>) ficheroCli.readObject();
			
			//orders
			File fileOrders = new File("serializedOrders/" +this.nit);
			ObjectInputStream ficheroOrd = new ObjectInputStream(new FileInputStream(fileOrders));
			orders = (ArrayList<Order>) ficheroOrd.readObject();
			
			ficheroCli.close();
			ficheroPro.close();
			ficheroOrd.close();
		} catch (Exception e) {
			System.out.println("No se pudo deserializar");
		}

	}
	
	//creo el reporte de pedidos
	public String generateOrderReport() {
		String msg = "";
		for (int i = 0; i < orders.size(); i++) {
			// tring orderCode, Date date, Date time, String clientCode, String resturantNit
			msg += orders.get(i).getOrderCode() + "," +  orders.get(i).getDate() + "," + 
					"," + orders.get(i).getClientCode() + "," + orders.get(i).getResturantNit() + 
					"," + orders.get(i).getOrderState()+ "\n";
		}
		return msg;
	}
	
	//lo imprimo en un txt --> el csv
	public void saveOrders() throws FileNotFoundException {
		File fileProducts = new File(PATH_ORDERS);
		PrintWriter pw = new PrintWriter(fileProducts);

		String repStr = generateOrderReport();
		pw.print(repStr);
		pw.close();
	}
	
	public void loadOrders() throws FileNotFoundException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(PATH)));
			String line = br.readLine();

			while(line!=null) {	
				
				String[] info = line.split(",");
				
				String ordercode = info[0]; 
				//LocalDateTime date = LocalDateTime.
				String clientcode = info[2];
				String restaurantnit = info[3];
				String orderstate = info[4];
				
				//Order order = new Order(ordercode,date,clientcode,restaurantnit, orderstate);
				//orders.add(order);
				
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
				products.add(product);
				
				line = br.readLine();
			}
			
		}catch(Exception e){
			
		}
	}
	
	public String generateReportClients() {
		
	}
	
	public void saveClients() {
		
	}
	
	public void loadClients() {
		
	}
}


