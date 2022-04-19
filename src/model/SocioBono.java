package brooklynFitboxing.model;

public class SocioBono extends Socio {
		
		
		private int numSesiones;

		public SocioBono(String dni, String telefono, String email, int numSesiones) {
			super(dni, telefono, email);
			this.numSesiones = numSesiones;
		}
		
		public void recargarBono(int num) {
			
			numSesiones += num;
			
		}

		@Override
		public String toString() {
			return "\nSocio Bono: " +
					super.toString() + 
					"\n\tNúmero de Sesiones: " + numSesiones ;
		}
		
		
		
}
