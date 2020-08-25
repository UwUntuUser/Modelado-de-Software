package Negocio.FactoriaNegocio;

import Negocio.Competicion.SACompeticionIMP;
import Negocio.Departamento.SADepartamentoIMP;
import Negocio.Empleado.SAEmpleadoIMP;
import Negocio.Pase.SAPaseIMP;
import Negocio.Proyeccion.SAProyeccionImp;
import Negocio.Venta.SAVentaImp;

public abstract class FactoriaSA {

	private static FactoriaSA Instance= null;
	
	public synchronized static FactoriaSA getInstance(){
		if(Instance == null){
			Instance = new FactoriaSAimp();
		}
		return Instance;
	}

	public abstract SAPaseIMP generateSAPase();

	public abstract SAProyeccionImp generateSAProyeccion();

	public abstract SAVentaImp generateSAVenta();
	
	public abstract SAEmpleadoIMP generateSAEmpleado();
	
	public abstract SADepartamentoIMP generateSADepartamento();
	
	public abstract SACompeticionIMP generateSACompeticion();
	
}