package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class Main {
	
	public static void main(String[] args) {
            
            //pruebas unitarias
            //clase client
            System.out.println("clase client");
            Client C  = new Client("1","t1","maria", "bucaramanga", "31066423");
            Client C1  = new Client("1","t1","juan", "bucaramanga", "31066423");
            Client C2  = new Client("1","t1","abdiel", "bucaramanga", "31066423");
            
            String id;
            ArrayList<Client> clientes = new ArrayList<Client>();
            
            clientes.add(C);
            clientes.add(C1);
            clientes.add(C2);
            
            clientes = ordenar(clientes);
            System.out.println(clientes.get(0).getName());
            System.out.println(clientes.get(1).getName());
            System.out.println(clientes.get(2).getName());

            id = C.getId();
            if( id.equals("1")){
                
                System.out.println("prueba bien");
            }else{
            
                System.out.println("prueba FALLO");
            }
            
            //CLASE ORDER
            System.out.println("clase order");
            
            ArrayList <String> productList = new ArrayList <String>();
            productList.add("menu");
        
            String OrderCode;
            Order O = new Order("3534t", "03-07-2020", "012774", "4566", "activo",productList);
            OrderCode = O.getOrderCode();
            if( OrderCode.equals("3534t")){
                
                System.out.println("prueba bien");
            }else{
            
                System.out.println("prueba FALLO");
            }  
            
            
            //CLASE product
            System.out.println("clase product");
           
            String name;
            Product p = new Product("3534t", "juan", "pedido", 11323.562,"124676");
            name = p.getName();
            if( name.equals("juan")){
                
                System.out.println("prueba bien");
            }else{
            
                System.out.println("prueba FALLO");
            }  
            
            //CLASE restaurante
            System.out.println("clase restaurant");
            String nit;
            
            Restaurant rest = new Restaurant("pedro","3543643","maria");
            
            nit = rest.getNit();
            
            if( nit.equals("3543643")){
                
                System.out.println("prueba bien");
            }else{
            
                System.out.println("prueba FALLO");
            }  
            
            
            
		SystemManage sm = new SystemManage("01");
		
		Scanner sc = new Scanner(System.in);
		showMenu();
		int menu = sc.nextInt();
		while(menu != 0) {
			
			menu = sc.nextInt();
			if(menu !=0) {
				selectionOption(menu);
			}
			else {
				System.exit(0);
			}
		}
		sc.close();
				
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
        String formatDateTime = date.format(format); 
	}
	
	public static void showMenu() {
		cleanScreen();
		System.out.println("1.add restaurant");
		System.out.println("2.add product ");
		System.out.println("3.add client ");
		System.out.println("4.add order");
		System.out.println("5.Salir");
		
		System.out.println("please enter then option you would like to use");
                
	}
	
	public static void cleanScreen() {
		try {
			final String os = System.getProperty("os.name");
			if(os.contains("windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e){
			
		}
	}
	
	public static void selectionOption(int menu) {
		if(menu==1) {
			registerRestaurant();
		}
                if(menu==2) {
			addProduct();
		}
                if(menu==3) {
			addCliente();
		}
                if(menu==4) {
			addOrder();
		}
	}
	
	public static void registerRestaurant() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Name:");
		sc.nextLine();
		System.out.println("Nit:");
		sc.nextLine();
		System.out.println("AdminName:");
		sc.nextLine();
		
	}
        
        public static void addProduct() {
		//aqui se agrega un nuevo producto
                Scanner sc = new Scanner(System.in);
		System.out.println("name:");
		sc.nextLine();
		System.out.println("code:");
		sc.nextLine();
		System.out.println("descripcion:");
		sc.nextLine();

	}
        public static void addOrder() {
		//aqui se agrega un nuevo producto
                Scanner sc = new Scanner(System.in);
		System.out.println("Pedido:");
		sc.nextLine();

	}
         public static void addCliente() {
		//aqui se agrega un nuevo producto
                Scanner sc = new Scanner(System.in);
		System.out.println("name:");
		sc.nextLine();
		System.out.println("direccion:");
		sc.nextLine();
		System.out.println("telefono:");
		sc.nextLine();

	}
        public static ArrayList<Client> ordenar(ArrayList<Client> clientes) {
		
                //Algoritmo de Burbuja
                Client aux;
                int intercambios = 0;
                int comparaciones = 0;
                for(int x = 1; x < clientes.size(); x++)
                {
                    for(int y = 0; y < clientes.size()-1; y++)
                    {
                        comparaciones++;
                        int cs1,cs2;
                        cs1 = (int)clientes.get(x-1).getName().charAt(0);
                        cs2 = (int)clientes.get(x).getName().charAt(0);
                        if( cs1 < cs2 )
                        {
                            aux = clientes.get(x-1);
                            clientes.set(x-1,clientes.get(x));
                            clientes.set(x,aux);
                            intercambios++;
                        }
                    }
                }
            return clientes;
        }
}
