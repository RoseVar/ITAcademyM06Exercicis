package com.vehicles.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehiclesProjectFase3 {

	public static void main(String[] args) {

		//----------------FASE 3 -------------------
		/*Modifica el projecte anterior afegint els m�todes necessaris a Bike, de manera que es pugui afegir rodes
		davanteres i traseres.

		 */
		System.out.println("---- FASE 3 ----");		
		//Modificar el Main anterior, afegint la opci� de Bike o Car:
		//0) Preguntar a l�usuari si vol crear un cotxe o una moto.
		Scanner scan = new Scanner (System.in);
		int type=0;
		boolean controlType=false;
		
		do {
			System.out.println("Vamos a crear un veh�culo. Elije cual quieres:");
			System.out.println("1 - Coche");
			System.out.println("2 - Bici");
			System.out.println("0 - Salir");
			try {
				type = scan.nextInt();
				if (type<0 || type>2) {
					System.out.println("Has de elegir uno de los m�meros del men�.");
					controlType=false;
				} else {
					controlType=true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Lo que has entrado no es un n�mero. ");
				scan.nextLine();
			}
		} while (!controlType);
		
		switch (type) {
			case 0:
				System.out.println("Gracias por usar el servicio.");
				break;
			case 1:
				//1) Demanar a l�usuari la matr�cula, la marca i el seu color. --> m�tode creat que fa 1+2
				//2) Crear el veh�cle amb les dades anteriors.
				Car coche= (Car) crearVehiculo(Car.class);
				System.out.println("Hemos creado la estructura del coche.");
				
				//3) Afegir-li les rodes traseres corresponents, demanant a l�usuari la marca i el diametre.
				//4) Afegir-li les rodes davanteres corresponents, demanant a l�usuari la marca i el diametre.
				afegirrDosRodes("las ruedas traseras", coche);				
				afegirrDosRodes("las ruedas delanteras", coche);
				
				//treiem per consola perque es vegi
				mostrarPerConsola(coche);
				break;
			case 2:
				//1) Demanar a l�usuari la matr�cula, la marca i el seu color. --> m�tode creat que fa 1+2
				//2) Crear el veh�cle amb les dades anteriors.
				Bike bici = (Bike) crearVehiculo(Bike.class);
				System.out.println("Hemos creado la estructura de la bici.");
				
				//3) Afegir-li les rodes traseres corresponents, demanant a l�usuari la marca i el diametre.
				//4) Afegir-li les rodes davanteres corresponents, demanant a l�usuari la marca i el diametre.
				afegirrUnaRoda("la rueda trasera", bici);				
				afegirrUnaRoda("la rueda delantera", bici);	
				
				//treiem per consola perque es vegi
				mostrarPerConsola(bici);
				break;
			default:
				System.out.println("Error en lectura del men�");
				break;
		}
	}
		
	
	/**
	 * M�todo para crear un veh�culo base con matr�cula, marca y color
	 * @param claseACrear se indica si ser� car o bike
	 * @return objeto Vehiculo con los datos pedidos
	 */
	public static Vehicle crearVehiculo(Class claseACrear) {
		Vehicle myVehicle;
		String  plate;
		String brand; 
		String colour;
		Scanner scan = new Scanner (System.in);
		
		Boolean wrongPlate= true;
		//Seg�n lo que sea
		if (claseACrear == Car.class) {
			System.out.println("Vamos a crear un coche.");
		} else if (claseACrear ==Bike.class) {
			System.out.println("Vamos a crear una bici.");
		}
		
		do {
			System.out.println("Dime qu� matr�cula tiene:");
			plate = scan.nextLine();
			if (revisarMatricula(plate)) {
				wrongPlate= false;
			} else {
				System.out.println("La matr�cula tiene que tener 4 n�meros y de 2 a 3 letras, int�ntalo de nuevo");			
			}
		} while (wrongPlate);
		System.out.println("Ahora dime de qu� marca es:");
		brand= scan.nextLine();
		System.out.println("Y por �ltimo el color que tiene:");
		colour= scan.nextLine();
		
		//Seg�n lo que sea
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
	 * M�todo para a�adir 2 ruedas iguales a un objeto coche
	 * @param zonaRodes Zona de las ruedas a a�adir (delantera/trasera)
	 * @param myCar Objecto coche a la que se a�adir�n
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
						+ "Las ruedas de la misma zona tienen que ser iguales para que el coche est� calibrado");
				pairWheels.clear();
			}
		} while (control);				
	}

	
	/**
	 * M�todo para a�adir 1 ruedas a un objeto bici
	 * @param zonaRodes Zona de la rueda a a�adir (delantera/trasera)
	 * @param myBike Objecto bici a la que se a�adir�n
	 */
	private static void afegirrUnaRoda(String zonaRodes, Bike myBike) {
		Wheel wheel;
		wheel= demanarRoda(zonaRodes);		
		myBike.addOneWheel(wheel);
							
	}

	/**
	 * M�tode per demanar les dades i crear una roda
	 * @param quinaRoda missatge que indicar� quina roda ser� la que farem
	 * @return roda amb marca i diametre
	 */
	private static Wheel demanarRoda(String quinaRoda) {
		Wheel prov;
		String brand;
		Double diametre= 0.0;
		Scanner scan = new Scanner (System.in);
		
		System.out.print("Vamos a a�adir "+quinaRoda+". ");
		System.out.println("Dime de qu� marca:");
		brand = scan.nextLine();
		if ("".equals(brand)) {
			System.out.println("No has introducido marca, as� que se pondr� como \"Desconocida\"");
			brand="Desconocida";
		}

		Boolean control= true;
		do {
			System.out.println("Y dime su di�metro (en n�meros):");
			try{
				diametre= scan.nextDouble();
				if (diametre>4 || diametre<0.4) {
					throw new Exception ("El di�metro no est� en los valores permitidos.");
				}
				control= false;
			} catch (InputMismatchException e) {
				System.out.println("Di�metro no puesto en n�meros, int�ntalo de nuevo.");
				scan.nextLine();
			} catch (Exception ex) {
				System.out.println("El valor no est� en el rango de los permitidos (de 0.4 a 4), int�ntalo de nuevo.");
				scan.nextLine();
			}
		}while (control);
		prov= new Wheel(brand, diametre);
		
		return prov;
	}
	
	/**
	 * M�todo para revisar que la matr�cula est� bien formada seg�n enunciado 
	 * (contenga 4 n�meros i dues o tres lletres)
	 * @param plate Matr�cula a revisar
	 * @return true si la matr�cula es correcta 
	 * 			false si la matr�cula es incorrecta
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
		//ser� incorrecta si: contiene simbolos o no contiene 4 digitos o no contiene 2 o 3 letras 
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
	 * M�todo para mostrar por consola los datos del veh�culo creado
	 * @param vehicle a mostrar los datos
	 */
	private static void mostrarPerConsola(Vehicle vehicle) {
		//tipo de vehiculo
		System.out.print("Hemos creado el siguiente veh�culo: ");
		if (vehicle.getClass()==Car.class) {
			System.out.println("un coche.");
		} else if (vehicle.getClass()==Bike.class) {
			System.out.println("una bici.");
		} else {
			System.out.println("Tipo de vehiculo no informado.");
		}
		
		//datos b�sicos: marca, matr�cula y color
		System.out.println("Es de la marca " + vehicle.getBrand()+ ", es de color "+ vehicle.getColor()+
				" y su matr�cula es \""+vehicle.getPlate()+"\"");
		
		//ruedas
		System.out.println("Tiene " + vehicle.getWheels().size() + " ruedas. Sus detalles son:");
		if (vehicle.getClass()==Car.class) {
			System.out.println("  Las ruedas traseras son de la marca "+ vehicle.getWheels().get(0).getBrand() + 
								"y de di�metro "+ vehicle.getWheels().get(0).getDiameter() + ".");
			System.out.println("  Las ruedas delanteras son de la marca "+ vehicle.getWheels().get(2).getBrand() + 
					"y de di�metro "+ vehicle.getWheels().get(2).getDiameter() + ".");
		} else if (vehicle.getClass()==Bike.class) {
			System.out.println("  La rueda trasera es de la marca "+ vehicle.getWheels().get(0).getBrand() + 
					"y de di�metro "+ vehicle.getWheels().get(0).getDiameter() + ".");
			System.out.println("  La rueda trasera es de la marca "+ vehicle.getWheels().get(1).getBrand() + 
					"y de di�metro "+ vehicle.getWheels().get(1).getDiameter() + ".");
		} else {
			System.out.println("  Tipo de vehiculo no informado, ruedas no disponibles.");
		}
	}
}
	
	
