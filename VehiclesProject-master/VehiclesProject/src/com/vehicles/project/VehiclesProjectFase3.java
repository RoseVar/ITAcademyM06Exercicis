package com.vehicles.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehiclesProjectFase3 {

	public static void main(String[] args) {

		//----------------FASE 3 -------------------
		/*Modifica el projecte anterior afegint els mètodes necessaris a Bike, de manera que es pugui afegir rodes
		davanteres i traseres.

		 */
		System.out.println("---- FASE 3 ----");		
		//Modificar el Main anterior, afegint la opció de Bike o Car:
		//0) Preguntar a l’usuari si vol crear un cotxe o una moto.
		Scanner scan = new Scanner (System.in);
		int type=0;
		boolean controlType=false;
		
		do {
			System.out.println("Vamos a crear un vehículo. Elije cual quieres:");
			System.out.println("1 - Coche");
			System.out.println("2 - Bici");
			System.out.println("0 - Salir");
			try {
				type = scan.nextInt();
				if (type<0 || type>2) {
					System.out.println("Has de elegir uno de los múmeros del menú.");
					controlType=false;
				} else {
					controlType=true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Lo que has entrado no es un número. ");
				scan.nextLine();
			}
		} while (!controlType);
		
		switch (type) {
			case 0:
				System.out.println("Gracias por usar el servicio.");
				break;
			case 1:
				//1) Demanar a l’usuari la matrícula, la marca i el seu color. --> mètode creat que fa 1+2
				//2) Crear el vehícle amb les dades anteriors.
				Car coche= (Car) crearVehiculo(Car.class);
				System.out.println("Hemos creado la estructura del coche.");
				
				//3) Afegir-li les rodes traseres corresponents, demanant a l’usuari la marca i el diametre.
				//4) Afegir-li les rodes davanteres corresponents, demanant a l’usuari la marca i el diametre.
				afegirrDosRodes("las ruedas traseras", coche);				
				afegirrDosRodes("las ruedas delanteras", coche);
				
				//treiem per consola perque es vegi
				mostrarPerConsola(coche);
				break;
			case 2:
				//1) Demanar a l’usuari la matrícula, la marca i el seu color. --> mètode creat que fa 1+2
				//2) Crear el vehícle amb les dades anteriors.
				Bike bici = (Bike) crearVehiculo(Bike.class);
				System.out.println("Hemos creado la estructura de la bici.");
				
				//3) Afegir-li les rodes traseres corresponents, demanant a l’usuari la marca i el diametre.
				//4) Afegir-li les rodes davanteres corresponents, demanant a l’usuari la marca i el diametre.
				afegirrUnaRoda("la rueda trasera", bici);				
				afegirrUnaRoda("la rueda delantera", bici);	
				
				//treiem per consola perque es vegi
				mostrarPerConsola(bici);
				break;
			default:
				System.out.println("Error en lectura del menú");
				break;
		}
	}
		
	
	/**
	 * Método para crear un vehículo base con matrícula, marca y color
	 * @param claseACrear se indica si será car o bike
	 * @return objeto Vehiculo con los datos pedidos
	 */
	public static Vehicle crearVehiculo(Class claseACrear) {
		Vehicle myVehicle;
		String  plate;
		String brand; 
		String colour;
		Scanner scan = new Scanner (System.in);
		
		Boolean wrongPlate= true;
		//Según lo que sea
		if (claseACrear == Car.class) {
			System.out.println("Vamos a crear un coche.");
		} else if (claseACrear ==Bike.class) {
			System.out.println("Vamos a crear una bici.");
		}
		
		do {
			System.out.println("Dime qué matrícula tiene:");
			plate = scan.nextLine();
			if (revisarMatricula(plate)) {
				wrongPlate= false;
			} else {
				System.out.println("La matrícula tiene que tener 4 números y de 2 a 3 letras, inténtalo de nuevo");			
			}
		} while (wrongPlate);
		System.out.println("Ahora dime de qué marca es:");
		brand= scan.nextLine();
		System.out.println("Y por último el color que tiene:");
		colour= scan.nextLine();
		
		//Según lo que sea
		if (claseACrear == Car.class) {
			myVehicle= new Car(plate, brand, colour);
		} else if (claseACrear ==Bike.class) {
			myVehicle= new Bike(plate, brand, colour);
		} else {
			myVehicle=null;
		}
		return myVehicle;
	}
					

	/**
	 * Método para añadir 2 ruedas iguales a un objeto coche
	 * @param zonaRodes Zona de las ruedas a añadir (delantera/trasera)
	 * @param myCar Objecto coche a la que se añadirán
	 */
	private static void afegirrDosRodes(String zonaRodes, Car myCar) {
		Wheel wheel;
		List<Wheel> pairWheels= new ArrayList<Wheel>();
		Boolean control= true;
		do {
			wheel= demanarRoda(zonaRodes);
			pairWheels.add(wheel);
			pairWheels.add(wheel);			
			try{
				myCar.addTwoWheels(pairWheels);
				control=false;
			} catch (Exception e) {
				System.out.println("Las ruedas no coinciden, prueba de nuevo. "
						+ "Las ruedas de la misma zona tienen que ser iguales para que el coche esté calibrado");
				pairWheels.clear();
			}
		} while (control);				
	}

	
	/**
	 * Método para añadir 1 ruedas a un objeto bici
	 * @param zonaRodes Zona de la rueda a añadir (delantera/trasera)
	 * @param myBike Objecto bici a la que se añadirán
	 */
	private static void afegirrUnaRoda(String zonaRodes, Bike myBike) {
		Wheel wheel;
		wheel= demanarRoda(zonaRodes);		
		myBike.addOneWheel(wheel);
							
	}

	/**
	 * Mètode per demanar les dades i crear una roda
	 * @param quinaRoda missatge que indicará quina roda será la que farem
	 * @return roda amb marca i diametre
	 */
	private static Wheel demanarRoda(String quinaRoda) {
		Wheel prov;
		String brand;
		Double diametre= 0.0;
		Scanner scan = new Scanner (System.in);
		
		System.out.print("Vamos a añadir "+quinaRoda+". ");
		System.out.println("Dime de qué marca:");
		brand = scan.nextLine();
		if ("".equals(brand)) {
			System.out.println("No has introducido marca, así que se pondrá como \"Desconocida\"");
			brand="Desconocida";
		}

		Boolean control= true;
		do {
			System.out.println("Y dime su diámetro (en números):");
			try{
				diametre= scan.nextDouble();
				if (diametre>4 || diametre<0.4) {
					throw new Exception ("El diámetro no está en los valores permitidos.");
				}
				control= false;
			} catch (InputMismatchException e) {
				System.out.println("Diámetro no puesto en números, inténtalo de nuevo.");
				scan.nextLine();
			} catch (Exception ex) {
				System.out.println("El valor no está en el rango de los permitidos (de 0.4 a 4), inténtalo de nuevo.");
				scan.nextLine();
			}
		}while (control);
		prov= new Wheel(brand, diametre);
		
		return prov;
	}
	
	/**
	 * Método para revisar que la matrícula esté bien formada según enunciado 
	 * (contenga 4 números i dues o tres lletres)
	 * @param plate Matrícula a revisar
	 * @return true si la matrícula es correcta 
	 * 			false si la matrícula es incorrecta
	 */
	private static boolean revisarMatricula(String plate) {
		char[] plateInputs= plate.toCharArray();
		boolean rightFormat= true;
		boolean otherSymbol= false;
		int numLetters=0;
		int numDigits=0;
		
		for (Character c: plateInputs){
			if (Character.isDigit(c)) {
				numDigits++;
			} else if (Character.isLetter(c)) {
				numLetters++;
			} else {
				otherSymbol= true;
			}
		}
		//será incorrecta si: contiene simbolos o no contiene 4 digitos o no contiene 2 o 3 letras 
		if (otherSymbol) {
			rightFormat= false;
		} else if (numLetters<2 || numLetters>3) {
			rightFormat=false;
		} else if (numDigits!=4){
			rightFormat= false;
		}
		 return rightFormat;
		
	}
	
	/**
	 * Método para mostrar por consola los datos del vehículo creado
	 * @param vehicle a mostrar los datos
	 */
	private static void mostrarPerConsola(Vehicle vehicle) {
		//tipo de vehiculo
		System.out.print("Hemos creado el siguiente vehículo: ");
		if (vehicle.getClass()==Car.class) {
			System.out.println("un coche.");
		} else if (vehicle.getClass()==Bike.class) {
			System.out.println("una bici.");
		} else {
			System.out.println("Tipo de vehiculo no informado.");
		}
		
		//datos básicos: marca, matrícula y color
		System.out.println("Es de la marca " + vehicle.getBrand()+ ", es de color "+ vehicle.getColor()+
				" y su matrícula es \""+vehicle.getPlate()+"\"");
		
		//ruedas
		System.out.println("Tiene " + vehicle.getWheels().size() + " ruedas. Sus detalles son:");
		if (vehicle.getClass()==Car.class) {
			System.out.println("  Las ruedas traseras son de la marca "+ vehicle.getWheels().get(0).getBrand() + 
								"y de diámetro "+ vehicle.getWheels().get(0).getDiameter() + ".");
			System.out.println("  Las ruedas delanteras son de la marca "+ vehicle.getWheels().get(2).getBrand() + 
					"y de diámetro "+ vehicle.getWheels().get(2).getDiameter() + ".");
		} else if (vehicle.getClass()==Bike.class) {
			System.out.println("  La rueda trasera es de la marca "+ vehicle.getWheels().get(0).getBrand() + 
					"y de diámetro "+ vehicle.getWheels().get(0).getDiameter() + ".");
			System.out.println("  La rueda trasera es de la marca "+ vehicle.getWheels().get(1).getBrand() + 
					"y de diámetro "+ vehicle.getWheels().get(1).getDiameter() + ".");
		} else {
			System.out.println("  Tipo de vehiculo no informado, ruedas no disponibles.");
		}
	}
}
	
	
