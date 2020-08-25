package Negocio.FactoriaNegocio;

import Negocio.Competicion.SACompeticionIMP;
import Negocio.Departamento.SADepartamentoIMP;
import Negocio.Empleado.SAEmpleadoIMP;
import Negocio.Pase.SAPaseIMP;
import Negocio.Proyeccion.SAProyeccionImp;
import Negocio.Venta.SAVentaImp;

public class FactoriaSAimp extends FactoriaSA {
	
	public SAPaseIMP generateSAPase() {
		return new SAPaseIMP();
	}

	public SAProyeccionImp generateSAProyeccion() {
		return new SAProyeccionImp();
	}

	public SAVentaImp generateSAVenta() {
		return new SAVentaImp();
	}

	@Override
	public SAEmpleadoIMP generateSAEmpleado() {
		return new SAEmpleadoIMP();
	}

	@Override
	public SADepartamentoIMP generateSADepartamento() {
		return new SADepartamentoIMP();
	}

	@Override
	public SACompeticionIMP generateSACompeticion() {
		return new SACompeticionIMP();
	}
}