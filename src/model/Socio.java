package brooklynFitboxing.model;

public class Socio {

	protected String dni;
	protected String telefono;
	protected String email;

	public Socio(String dni, String telefono, String email) {
		this.dni = dni;
		this.telefono = telefono;
		this.email = email;
	}
	
	

	public String getDni() {
		return dni;
	}



	@Override
	public String toString() {
		return 		"\n\tDni: " + dni + 
					"\n\tTeléfono: " + telefono + 
					"\n\tEmail: " + email ;
	}
	
	
	
}
