package ui;

import java.util.Scanner;
import model.*;


import model.Client;
import model.Order;
import model.Product;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int menu=-1;
		String name;
		String nit;
		String administratorName;
		Client clients;
		Order orders;
		Product product;
		
		
		while(menu != 0) {
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
			menu = sc.nextInt();
			if(menu !=0) {
				selectionOption(menu);
			}
		}
		sc.close();
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
		switch(menu) {
		
		case 1:
			addRestaurant (name, nit, administratorName, products, clients, orders);
			break;
		}
	}
	
	public void registerRestaurant() {
		System.out.println("please enter the name of the restaurant");
	}
}
