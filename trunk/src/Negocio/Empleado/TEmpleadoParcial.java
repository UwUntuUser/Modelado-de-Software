package Negocio.Empleado;

public class TEmpleadoParcial extends TEmpleado{

		private double HorasJornada;
		private double Suelo_Hora;
		
		public TEmpleadoParcial(String dni, String nombre,String apellido,Integer SS, boolean activo,double horas,double sueldo) {
			super(nombre, apellido,dni,SS,activo);
			this.HorasJornada=horas;
			this.Suelo_Hora=sueldo;
		}
		public TEmpleadoParcial(String dni, String nombre,String apellido,Integer SS, boolean activo,double horas,double sueldo, int idDep) {
			super(nombre, apellido,dni,SS,activo, idDep);
			this.HorasJornada=horas;
			this.Suelo_Hora=sueldo;
		}
		public TEmpleadoParcial(int id, String dni, String nombre,String apellido,Integer SS, boolean activo,double horas,double sueldo) {
			super(nombre, apellido,dni,id,SS,activo);
			this.HorasJornada=horas;
			this.Suelo_Hora=sueldo;
		}
		public double getSueldoHora()
		{
			return this.Suelo_Hora;
		}
		public double getHorasJOrnada()
		{
			return this.HorasJornada;
		}

		public String toString(){
			String s="ID Empleado: "+this.getId()+" Nombre: "+this.getNombre()+" Apellido: "+this.getApellido()+ " DNI: "+this.getDNI()+ " Num SS : "+this.getNumSS()+" Horas Jornada: "+this.getHorasJOrnada()+" Sueldo Hora: "+this.getSueldoHora()+"\n";
			return s;
		}
		
}
