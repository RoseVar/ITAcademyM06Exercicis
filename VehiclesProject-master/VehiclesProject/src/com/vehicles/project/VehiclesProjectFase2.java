package com.vehicles.project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VehiclesProjectFase2 {

	public static void main(String[] args) {

		//----------------FASE 2-----------------
		//Millorar el codi anterior comprovant la informaci� necesaria alhora de crear els objectes. S�ha de tenir en
		//compte:
		System.out.println("----FASE 2----");		
		
		//Demanar a l�usuari la matr�cula del cotxe, la marca i el seu color.
		//1) Una matr�cula ha de tenir 4 n�meros i dues o tres lletres  --> Se crea m�todo revisarMatricula()
		String  plate;
		String brand; 
		String colour;
		Scanner scan = new Scanner (System.in);
		
		Boolean wrongPlate= true;
		do {
			System.out.println("Vamos a crear un coche. Dime qu� matr�cula tiene:");
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
		
		//Crear el cotxe amb les dades anteriors.
		Car myCar= new Car(plate, brand, colour);
		
		//Afegir-li dues rodes traseres demanant a l�usuari la marca i el diametre.
		//2) Un diametre de la roda ha de ser superior a 0.4 i inferior a 4--> millorat al m�tode demanarRoda()
		afegirrDosRodes("ruedas traseras", myCar);
		
		//Afegir-li dues rodes davanteres demanant a l�usuari la marca i el diametre.
		//2) Un diametre de la roda ha de ser superior a 0.4 i inferior a 4--> millorat al m�tode demanarRoda()
		afegirrDosRodes("ruedas delanteras", myCar);
		
		//Saco por pantalla para que se vea
		System.out.println("Se ha creado el coche con matr�cula: "+ plate + ", de marca "+ brand +
				" y de color " + colour+".");
		System.out.println("Las ruedas traseras son marca "+ myCar.wheels.get(0).getBrand() + " y di�metro " +
							myCar.wheels.get(0).getDiameter());
		System.out.println("Las ruedas traseras son marca "+ myCar.wheels.get(2).getBrand() + " y di�metro " +
							myCar.wheels.get(2).getDiameter());
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
	 * M�tode per demanar les dades i crear una roda
	 * @param quinaRoda missatge que indicar� quina roda ser� la que farem
	 * @return roda amb marca i diametre
	 */
	private static Wheel demanarRoda(String quinaRoda) {
		Wheel prov;
		String brand;
		Double diametre= 0.0;
		Scanner scan = new Scanner (System.in);
		
		System.out.print("Vamos a a�adir las "+quinaRoda+". ");
		System.out.println("Dime de qu� marca ser�n:");
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
				//si no est� dentro del rango lanzo una expeci�n
				if (diametre>4 || diametre<0.4) {
					throw new Exception ("El di�metro no est� en los valores permitidos.");
				}
				control= false;
			} catch (InputMismatchException e) {
				System.out.println("Di�metro no puesto en n�meros, int�ntalo de nuevo.");
				scan.nextLine();
			//capturo la excepcion del rango 	
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
		//revisamos si el caracter n�mero, letra u otro 
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
}
	
	
