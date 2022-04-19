package dam.eje2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class TablaPuntos {

	static HashMap<String, Integer> listapuntos;
	static Scanner sc;
	static final int XREPOS = 10;

	public static void main(String[] args) {
		sc = new Scanner (System.in);
		
		listapuntos = new HashMap<String, Integer>();
		
		String matricula ="";
		
		String next="";
		boolean nextCar = true;
	
		
		while (nextCar) {
			
			matricula = solicitarString("Indica la matrácula del vehíuculo repostado:");
						
			if (!listapuntos.containsKey(matricula)) {
				
					nuevaTarjeta(matricula);
				
					System.out.println("¿Desea Continuar?");
					next = sc.nextLine().toUpperCase();
					
					if (next.contentEquals("SI")) {
						
						nextCar = true;
					}else {
						nextCar = false;
						System.out.println("\n*** Listado de tarjetas *** ");
						
						showlista();
					}
			}else {
				
				acumulaPuntos(matricula);	
				
				System.out.println("�Desea Continuar?");
				next = sc.nextLine().toUpperCase();
				
				if (next.contentEquals("SI")) {
					
					nextCar = true;
				}else {
					nextCar = false;
					System.out.println("\n***Listado de tarjetas *** ");
					
					showlista();
					
				}
				
			}
			
			
			
		}
			
		sc.close();
		
	}

	

	private static void showlista() {
		System.out.println("*** Listado de tarjeta ***");
		TreeMap<String, Integer>listaOrdenada = new TreeMap<String, Integer>(listapuntos);
		
		int contTarjeta10 = 0;
		for(String matricula  : listaOrdenada.keySet()) {
			
			System.out.println(matricula + "  -  "  + listaOrdenada.get(matricula) + " puntos" );
			
			if (listaOrdenada.get(matricula) >= 10) {
				contTarjeta10++;
			}
		
		}
		System.out.println("Cantidad de tarjeta con 10 o más puntos: " + contTarjeta10);
		
	
		
	}



	private static void acumulaPuntos(String matricula) {
		 int puntos = 0;
		 int litros = 0;
		 int acumula = 0;
		 
		 litros = soliciaraEntero("Indica la cantidad  de litros repostado:");
		 	
		 if (litros > 0) {
			
			 if (puntos < listapuntos.get(matricula)) {
				
				 puntos = litros / XREPOS;
				 
				acumula = puntos + listapuntos.get(matricula);
				
				listapuntos.put(matricula, acumula);
				
				System.out.println("ACUMULACI�N DE PUNTOS, ACUMULADOS EN ESTE REPOSTAJE: " + puntos
						+ " PUNTOS ");
			}
			 
		}
		
		
		
	}



	private static void nuevaTarjeta(String matricula) {
		int litros = 0;
		int puntos = 0;
		litros = soliciaraEntero("Indica la cantidad  de litros repostado:");
		
		if (litros > 0 ) {
			
			puntos = litros / XREPOS;
			
			System.out.println("\nNueva tarjeta, Acumula en este repostaje : " + puntos + "\n");
		}
		
		listapuntos.put(matricula, puntos);
		
	}

	private static int soliciaraEntero(String msj) {
		int litros=0;
		
		System.out.println(msj);
		litros = Integer.parseInt(sc.nextLine());
		
		return litros;
	}

	private static String solicitarString(String msj) {
		String m ="";
		
		System.out.println(msj);
		m = sc.nextLine();
		
		return m;
	}

	
	
		
		
	
		
	

}
