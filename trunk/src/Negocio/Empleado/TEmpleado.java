package Negocio.Empleado;

public class TEmpleado {
	
	private String DNI;
	private String Nombre;
	private String Apellido;
	private Integer id;
	private Integer NumSS;
	private boolean esParcial;
	private int idDep;
	public int getIdDep() {
		return idDep;
	}
	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}
	private boolean activo;
	
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumSS() {
		return NumSS;
	}
	public void setNumSS(Integer numSS) {
		NumSS = numSS;
	}
	public boolean isEsParcial() {
		return esParcial;
	}
	public void setEsParcial(boolean esParcial) {
		this.esParcial = esParcial;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getNombre()
	{
		return this.Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido()
	{
		return this.Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public TEmpleado(){}
	public TEmpleado(String nombre,String apellido, String DNI,Integer id,Integer NumSS,boolean a)
	{
		this.DNI=DNI;
		this.Nombre=nombre;
		this.Apellido=apellido;
		this.id=id;
		this.NumSS=NumSS;
		this.activo=a;
	}
	
	public TEmpleado(String nombre,String apellido, String DNI,Integer NumSS,boolean a)
	{
		this.DNI=DNI;
		this.Nombre=nombre;
		this.Apellido=apellido;
		this.NumSS=NumSS;
		this.activo=a;
	}
	public TEmpleado(String nombre,String apellido, String DNI,Integer NumSS,boolean a, int idDep)
	{
		this.DNI=DNI;
		this.Nombre=nombre;
		this.Apellido=apellido;
		this.NumSS=NumSS;
		this.activo=a;
		this.idDep = idDep;
	}
	public String toString(){
		String s="ID Empleado: "+this.getId()+" Nombre: "+this.getNombre()+" Apellido: "+this.getApellido()+ " DNI: "+this.getDNI()+ " Num SS : "+this.getNumSS()+"\n";
		return s;
	}
	

}
