package brookliynFitboxing.main;

import java.util.ArrayList;
import java.util.Scanner;

import brooklynFitboxing.model.Socio;
import brooklynFitboxing.model.SocioBono;
import brooklynFitboxing.model.SocioMensual;

public class GestorSocio {
	static final String[]MENU = {"para añadir un socio",
								"para mostrar el listado",
								"para recargar un bono",
								"para cambiar de plan",
								"para salir de la aplicación"};
	
	static final String []PLAN_ABB = {"2SS","3SS","SI"};
	static final String []PLAN = {"2 sesiones/semana","3 sesiones/semana","sesiones ilimitadas"};

	static final int SIZE_DNI = 4;

	 static final int SIZE_TLFNO = 5;

	static ArrayList<Socio> listaSocio;
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner (System.in);
		
		listaSocio = new ArrayList<Socio>();
		
		System.out.println("** Gestión de socios del Brooklyn Fitboxing **");
		int option = 0;
		
		while (option != 5) {
			
			option = requestMenu();
			
			switch (option) {
			case 1:
				addclient();
				break;
			case 2:
				showList();
				break;
			case 3:
				rechargePlan();
				break;	
			case 4:
				changePlan();
				break;
			case 5:
				System.out.println("\n*** Cerrando Aplicación ***");
				break;
			
			}
		
		}
		sc.close();
		
	}

	private static void changePlan() {
		String dni = requestDni("Introduce el dni del socio de mensualidad", SIZE_DNI); 
		
		Socio socio = searchDni(dni);
		
		if (socio != null) {
			
			if (socio instanceof SocioMensual) {
				String plan = requestPlan();
				((SocioMensual)socio).cambiarPlan(plan); // pasamos el plan como parametro 
				
				System.out.println("Cambio de plan realizado");
			}else {
				System.out.println("El dni no pertenece a un socio Mensual");
			}
		}else {
			System.out.println("Dni no resgitrado en la cartera de cliente");
		}

		
	}

	private static void rechargePlan() {
		String dni = requestDni("Introduce el dni del socio de bono",SIZE_DNI); 
		
		Socio socio = searchDni(dni);
		
		if (socio != null) {
			if (socio instanceof SocioBono) {
				int numSesiones = requestNumss();
				((SocioBono)socio).recargarBono(numSesiones);
				
				System.out.println("¡Bono de sesiones recargado!");
				
			}else {
				
				System.out.println("El dni no pertenece a un socio bono");
			}
		}else {
			System.out.println("Dni no resgitrado en la cartera de cliente");
		}
		
	}

	private static void showList() {
		//Al mostrar el arrayListy preguntar simpre si esta vacio 
		if (listaSocio.isEmpty()) {
			System.out.println("No hay clientes registrados");
			
		}else {
			System.out.println("** Cartera de Socios **");
			//El for each simpre debe ser del tipo de datos que estemos manejando en el arrayList
			for (Socio socio : listaSocio) {
				
				System.out.println("\n"+socio);
			}
			
		}
	
	}

	private static void addclient() {
		
		String dni = requestDni("Introduce el dni del nuevo socio",SIZE_DNI); 
		/*Cuando vamos a usar un metodo para una solo comprobación, 
		 * es preferible solicitar los datos dentro de ese metodo
		*/
		Socio socio = searchDni(dni);
		
		if (socio != null) {
			System.out.println("El " + dni +  " ya esta registrado en la cartera de cliente");
		}else {
			
			String tlfno = requestTlfno();
			
			System.out.println("Introduce el email");
			String email = sc.nextLine();
			
			//Usamos tipo de datos primitivo char porque solo vamos manejar un caracter
			char tipo = requestTipo();
			
			if (tipo == 'B') {
				
				int numSesiones = requestNumss();
				
				System.out.println("!Socio Registrado!");
				
				socio = new SocioBono(dni, tlfno, email, numSesiones);
				
			}else {
				
				String planSs = requestPlan();
				
				System.out.println("Introduce el número de cuenta bancaria");
				String numAccount = sc.nextLine();
				
				System.out.println("!Socio Registrado!");
				
				socio = new SocioMensual(dni, tlfno, email, planSs, numAccount);
			}
			
			listaSocio.add(socio);			
		}
		
	}

	private static String requestPlan() {
		String pl = "";
		boolean nextPl= true;
		
		while (nextPl) {
			
		
			  //Con el primer [for] mostramos las opcines de ambos array
			
			System.out.println("Indica el plan al que se acoge:");
			for (int i = 0; i < PLAN.length; i++) {
				
				System.out.println(PLAN_ABB[i]+" - "+PLAN[i]);
			}
			pl = sc.nextLine().trim();
			
			//En el segundo [for] estamos comprobando lo introducido por le usuario y si es correcto,
			//se le asignamos a la variable que retornamos 
			for (int i = 0; i < PLAN_ABB.length && nextPl; i++) {
				if (pl.equalsIgnoreCase(PLAN_ABB[i])) {
					nextPl = false;
					pl = PLAN[i];
				}
			}
			
			if (nextPl) {
				System.out.println("El valor debe ser 2SS - 3SS - SI");
			}
			
			
		}	
		
		return pl;
	}

	private static int requestNumss() {
		int numSS = 0;
		boolean nextNumss = true;
		
		while (nextNumss) {
			try {	
				System.out.println("Introduce el número de sesiones que quiere cargar");
				numSS = Integer.parseInt(sc.nextLine());
				
				if (numSS == 1 || numSS == 5 || numSS == 10 || numSS == 15 || numSS == 20) {
					
					nextNumss = false;
				}else {
					throw new NumberFormatException();
				}
			}catch (NumberFormatException e) {
				System.out.println("El valor debe ser 1, 5, 10, 15, 20");
			}
			
		}
				
		return numSS;
	}

	private static char requestTipo() {
		char tp = ' ';
		
		while (tp != 'B' && tp != 'M') {
			
			System.out.println("Indica si el socio se va a acoger a bono o a mensualidad (B/M)");
			tp = sc.nextLine().toUpperCase().trim().charAt(0);
			
			if (tp != 'B' && tp != 'M') {
				System.out.println("Debes introducir B o M");
			}
			
		
		}
		return tp;
	}

	private static String requestTlfno() {
		String numTlfno = "";
		
		while (numTlfno.isEmpty() || numTlfno.length() > SIZE_TLFNO) {
			
			System.out.println("Introduce el telefono");
			numTlfno = sc.nextLine().toUpperCase().trim();
			
			
			if (numTlfno.isEmpty() || numTlfno.length() > SIZE_TLFNO) {
				System.out.println("Debes ingresar 12 caracteres numéricos incluyendo el prefijo con +34");
			}
		}
			
		return numTlfno;
	}

	private static Socio searchDni(String dni) {
		Socio socio = null;// socio es null quiere decier que lo ha encontrado en el arrayList
		
		for (int i = 0; i < listaSocio.size()&& socio == null; i++) {
			if (listaSocio.get(i).getDni().equals(dni)) {
				socio = listaSocio.get(i);
				i = listaSocio.size();
				
			}
		}
			
		return socio;
	}

	private static String requestDni(String msj, int sizeDni) {
		String numDni = "";
		
		while (numDni.length() != SIZE_DNI) {
			
			System.out.println(msj);
			numDni = sc.nextLine().toUpperCase().trim();
			
			
			if (numDni.length() != SIZE_DNI) {
				System.out.println("Debes ingresar 8 caracteres numéricos y un 1 caracter alfabético");
			}
		}
			
		return numDni;
	}

	private static int requestMenu() {
		int opt = 0;
		boolean nextOpt = true;
		
		//Usamos variable booleana ya que en el while vamos a controlar las excepciones con NumberFormatException
		while (nextOpt) {
			try {	
				System.out.println("\nIndica la acción que deseas realizar:\n");
				for (int i = 0; i < MENU.length; i++) {
					System.out.println((i+1)+" - " + MENU[i]);
				}
				opt = Integer.parseInt(sc.nextLine());
				
				if (opt >= 1 && opt <= MENU.length) {
					
					nextOpt = false;
				}else {
					throw new NumberFormatException();
				}
			}catch (NumberFormatException e) {
				System.out.println("El valor dede de estar entre 1 y " + MENU.length);
			}
		}
		
		
		return opt;
	}

}
