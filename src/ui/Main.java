package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.*;

public class Main {
	
	public static void main(String[] args) {
		
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
		System.out.println("5.update restaurant");
		System.out.println("6.update product ");
		System.out.println("7.update client ");
		System.out.println("8.update order");
		System.out.println("9.update order state");
		System.out.println("10.");
		
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
}
