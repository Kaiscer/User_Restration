package brooklynFitboxing.model;

public class SocioMensual extends Socio {
	
		
		private String plan;
		private String numCuenta;
		
		public SocioMensual(String dni, String telefono, String email, String plan, String numCuenta) {
			super(dni, telefono, email);
			this.plan = plan;
			this.numCuenta = numCuenta;
		}
		
		// Al invocar este metodo en main le pasamos el plan como parametro en el casting 
		public void cambiarPlan(String plan) {
				
			this.plan = plan ; // con this diferenciamos plan de la variable plan metodo set
									
		}
		

		@Override
		public String toString() {
			return  "\nSocio Mensual; "+ 
						super.toString() + 
						"\n\tPlan: " +  plan + 
						"\n\tNúmero de cuenta: " + numCuenta ;
		}
		
		
		
		
		
}
