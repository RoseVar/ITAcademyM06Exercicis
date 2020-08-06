package com.vehicles.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehiclesProjectFase1separado {

	public static void main(String[] args) {

		// -------------FASE 1-----------------
		System.out.println("----FASE 1----");		
		
		//1) Demanar a l�usuari la matr�cula del cotxe, la marca i el seu color.
		String  plate;
		String brand; 
		String colour;
		Scanner scan = new Scanner (System.in);
		
		System.out.println("Vamos a crear un coche. Dime qu� matr�cula tiene:");
		plate = scan.nextLine();
		System.out.println("Ahora dime de qu� marca es:");
		brand= scan.nextLine();
		System.out.println("Y por �ltimo el color que tiene:");
		colour= scan.nextLine();
		
		//2) Crear el cotxe amb les dades anteriors.
		Car myCar= new Car(plate, brand, colour);
		
		//3) Afegir-li dues rodes traseres demanant a l�usuari la marca i el diametre.
		afegirrDosRodes("rueda trasera", myCar);
		
		//4) Afegir-li dues rodes davanteres demanant a l�usuari la marca i el diametre.
		afegirrDosRodes("rueda delantera", myCar);
		
		//Saco por pantalla para que se vea
		System.out.println("Se ha creado el coche con matr�cula: "+ plate + ", de marca "+ brand +
				" y de color " + colour+".");
		System.out.println("Una de las ruedas traseras es marca "+ myCar.wheels.get(0).getBrand() + " y di�metro " +
							myCar.wheels.get(0).getDiameter());
		System.out.println("La otra es marca "+ myCar.wheels.get(1).getBrand() + " y di�metro " +
				myCar.wheels.get(1).getDiameter());		
		System.out.println("Una de las ruedas delanteras es marca "+ myCar.wheels.get(2).getBrand() + " y di�metro " +
							myCar.wheels.get(2).getDiameter());
		System.out.println("Y la otra marca "+ myCar.wheels.get(3).getBrand() + " y di�metro " +
				myCar.wheels.get(3).getDiameter());		
	}

	/**
	 * M�todo para a�adir 2 ruedas a un objeto coche, pidi�ndolas por separado
	 * @param zonaRodes Zona de las ruedas a a�adir (delantera/trasera)
	 * @param myCar Objecto coche a la que se a�adir�n
	 */
	private static void afegirrDosRodes(String zonaRodes, Car myCar) {
		Wheel wheelT1;
		Wheel wheelT2;
		List<Wheel> pairWheels= new ArrayList<Wheel>();
		Boolean control= true;
		do {
			wheelT1= demanarRoda("primera", zonaRodes);
			pairWheels.add(wheelT1);
			wheelT2= demanarRoda("segunda", zonaRodes);
			pairWheels.add(wheelT2);			
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
	 * M�tode per demanar les dades i crear una roda
	 * @param quinaRoda missatge que indicar� quina roda ser� la que farem
	 * @return roda amb marca i diametre
	 */
	private static Wheel demanarRoda(String cual, String quinaRoda) {
		Wheel prov;
		String brand;
		Double diametre= 0.0;
		Scanner scan = new Scanner (System.in);
		
		System.out.print("Vamos a a�adir la "+cual +" "+quinaRoda+". ");
		System.out.println("Dime de qu� marca ser�:");
		brand = scan.nextLine();
		if ("".equals(brand)) {
			System.out.println("No has introducido marca, as� que se pondr� como \"Desconocida\"");
			brand="Desconocida";
		}
		System.out.println("Y dime su di�metro (en n�meros):");
		Boolean control= true;
		do {
			try{
				diametre= scan.nextDouble();
				control= false;
			} catch (InputMismatchException e) {
				System.out.println("Di�metro no v�lido, int�ntalo de nuevo (acepta formato num�rico).");
				scan.nextLine();
			}
		}while (control);
		prov= new Wheel(brand, diametre);
		
		return prov;
	}
}
	
	
