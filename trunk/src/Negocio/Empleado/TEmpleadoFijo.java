package Negocio.Empleado;

public class TEmpleadoFijo extends TEmpleado{

		private double sueldoMensual;
		private double antiguedad;
		
		
		public TEmpleadoFijo(String dni, String nombre,String apellido,Integer SS,double antiguedad,double sueldo,boolean a) {
			super(nombre, apellido,dni,SS,a);
			this.antiguedad=antiguedad;
			this.sueldoMensual=sueldo;
		}
		public TEmpleadoFijo(String dni, String nombre,String apellido,Integer SS,double antiguedad,double sueldo,boolean a, Integer idDep) {
			super(nombre, apellido,dni,SS,a, idDep);
			this.antiguedad=antiguedad;
			this.sueldoMensual=sueldo;
		}
		public TEmpleadoFijo(int id, String dni, String nombre,String apellido,Integer SS,double antiguedad,double sueldo,boolean a) {
			super(nombre, apellido,dni,id,SS,a);
			this.antiguedad=antiguedad;
			this.sueldoMensual=sueldo;
		}
		public double getAntiguedad()
		{
			return this.antiguedad;
		}
		public double getSueldoMensual()
		{
			return this.sueldoMensual;
		}
		public String toString(){
			String s="ID Empleado: "+this.getId()+" Nombre: "+this.getNombre()+" Apellido: "+this.getApellido()+ " DNI: "+this.getDNI()+ " Num SS : "+this.getNumSS()+" Sueldo Mensual: "+this.getSueldoMensual()+" Antiguedad: "+this.getAntiguedad()+"\n";
			return s;
		}
}
