package Negocio.Departamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Negocio.Competicion.BOCompeticion;
import Negocio.Competicion.TCompeticion;
import Negocio.Empleado.BOEmpleado;
import Negocio.Empleado.TEmpleado;

public class SADepartamentoIMP implements SADepartamento {

	@Override
	public int altaDepartamento(TDepartamento td) {
		int id = -1;
		try 
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();
			
			String nombre = td.getNombre();
            Query query = (Query) EntMan.createNamedQuery("departamento.encontrarPorNombre").setParameter("nombre",
                    nombre);
            if (!query.getResultList().isEmpty()) {
                BODepartamento BOD = (BODepartamento) query.getSingleResult();
                if (!BOD.getActivo()) {
                    BOD.setActivo(true);
                    EntTran.commit();
                    id = BOD.getIdDep();
                } else {
                    EntTran.rollback();
                }
            } else {
                BODepartamento dep = new BODepartamento();
                dep.setActivo(true);
                dep.setNombre(td.getNombre());
                EntMan.persist(dep);
                EntTran.commit();
                id = dep.getIdDep();
            }
            EntMan.close();
			EntManFac.close();
		} catch (PersistenceException e) {
			id = -2;
		}
		
		return id;
	}

	@Override
	public boolean bajaDepartamento(int id) {
		boolean ok = false;
		boolean abortar = false;
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();

			BODepartamento dep = EntMan.find(BODepartamento.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (dep.equals(null) || !dep.getActivo()) {
				ok = false;
				EntTran.rollback();
			} else {

				Collection<BOEmpleado> empleados = dep.getEmpleados();
				if (empleados.isEmpty()) {
					dep.setActivo(false);
					EntTran.commit();
					ok = true;
				} else {
					Iterator<BOEmpleado> iter = empleados.iterator();
					while (!ok && iter.hasNext() && !abortar) {
						BOEmpleado empleado = iter.next();
						EntMan.lock(empleado, LockModeType.OPTIMISTIC); // nuevo, bloqueo de empleados
						if (empleado.getActivo()) {
							EntTran.rollback();
							abortar = true;
						}
					}
					if (!abortar) {
						dep.setActivo(false);
						EntMan.persist(dep);
						EntTran.commit();
					}
					
				}
			}
			EntMan.close();
			EntManFac.close();
		}
		catch (PersistenceException e){}
		
		return ok;
	}

	@Override
	public TDepartamento mostrarDepartamento(int id) {
		BODepartamento BOdep = null;
		TDepartamento Tdep = null;
		try {
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();

			BOdep = EntMan.find(BODepartamento.class, id, LockModeType.OPTIMISTIC);
			if (BOdep == null || !BOdep.getActivo()) {
				EntTran.rollback();
			} else {
				Tdep = new TDepartamento();
				Tdep.setActivo(true);
				Tdep.setNombre(BOdep.getNombre());
				Tdep.setId(BOdep.getIdDep());
				ArrayList<TEmpleado> listEmpleados = new ArrayList<>();
				for (BOEmpleado emp : BOdep.getEmpleados()) {
					TEmpleado aux = new TEmpleado(emp.getNombre(), emp.getApellido(), emp.getDni(), emp.getId(),
							emp.getNumSS(), emp.getActivo());
					listEmpleados.add(aux);
				}
				Tdep.setEmpleados(listEmpleados);
				EntTran.commit();
			}
			EntMan.close();
			EntManFac.close();
			
		} catch (PersistenceException e) {}
		
		return Tdep;
	}

	@Override
	public ArrayList<TDepartamento> mostrarDepartamentos() {
		ArrayList<TDepartamento> departamentos = new ArrayList<TDepartamento>();
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();

			
			Query query = (Query) EntMan.createNamedQuery("departamento.mostrarTodos").setParameter("activo", true);
	        Collection<BODepartamento> depa = query.getResultList();
	        if (!query.getResultList().isEmpty()) 
	        {
	            for (BODepartamento dep : depa) {
	                TDepartamento departamento = new TDepartamento();
	                departamento = this.mostrarDepartamento(dep.getIdDep());
	                departamentos.add(departamento);
	            }

	            EntTran.commit();

	        } else {
	            EntTran.rollback();
	        }
	        EntMan.close();
			EntManFac.close();
		} 
		catch (PersistenceException e){}
		
		return departamentos;
	}

	@Override
	public boolean actualizarDepartamento(TDepartamento departamento) {
		boolean ok = false;
		BODepartamento BOD = null;
		TDepartamento Tdep = null;
		
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();

			BOD = EntMan.find(BODepartamento.class, departamento.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (BOD == null || !BOD.getActivo()) 
			{

				EntTran.rollback();
			} 
			else 
			{
				BOD.setNombre(departamento.getNombre());
				EntMan.persist(BOD);
				EntTran.commit();
				ok = true;
			}
			EntMan.close();
			EntManFac.close();
		}
		catch (PersistenceException e){}
	
		return ok;
	}

	@Override
	public boolean AniadirEmpleadoDepartamento(TEmpleado empleado) {
		boolean ok = false;
		boolean repetido = false;
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();
			// existe el departamento y el empleado
			BODepartamento depAux = EntMan.find(BODepartamento.class, empleado.getIdDep(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			BOEmpleado empAux = EntMan.find(BOEmpleado.class, empleado.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (depAux == null || empAux == null || !depAux.getActivo()) 
			{
				EntTran.rollback();
			} 
			else if (empAux.getActivo() == false)
			{
				EntTran.rollback();
			}
			else
			{
				if(!depAux.getEmpleados().contains(empAux))
				{
					ok = true;
					empAux.setDepartamento(depAux);
					depAux.aniadirEmpleadoDepartamento(empAux);
					EntTran.commit();
				} 
			}
			EntMan.close();
			EntManFac.close();
		}
		catch (PersistenceException e) {}
		
		return ok;
	}

	@Override
	public boolean EliminarEmpleadoDepartamento(int dep, TEmpleado emp) {
		boolean ok = false;
		boolean repetido = false;
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();
			// existe el departamento y el empleado
			BODepartamento depAux = EntMan.find(BODepartamento.class, dep, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			BOEmpleado empAux = EntMan.find(BOEmpleado.class, emp.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			if (depAux == null || empAux == null) 
			{
				EntTran.rollback();
			}
			else 
			{
				if(!depAux.getBOEmpleado().contains(empAux))
				{
					EntTran.rollback();
				}
				else 
				{
					ok = true;
					empAux.setDepartamento(null);
					depAux.eliminarEmpleado(empAux);
					EntTran.commit();
				}
			}
			EntMan.close();
			EntManFac.close();
		}
		catch (PersistenceException e){}
		
		return ok;
	}

	@Override
	public int CalcularNominaDepartamento(int depid) 
	{
		int nominaTotal = -1;
		boolean ok = true;
		try
		{
			EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
			EntityManager EntMan = EntManFac.createEntityManager();
			EntityTransaction EntTran = EntMan.getTransaction();
			EntTran.begin();
			//Comprobamos que existe departamento
			TDepartamento dep = mostrarDepartamento(depid);
			if(dep == null)
			{
				EntTran.rollback();
			}
			else
			{
				BODepartamento depAux = EntMan.find(BODepartamento.class, dep.getId(), LockModeType.OPTIMISTIC);
				if(depAux == null)
				{
					EntTran.rollback();
				}
				else
				{
					ArrayList<TEmpleado> empleadosDep = dep.getEmpleados();
					//Comprobamos que los empleados existen en el departamento
					for(int i = 0; i < empleadosDep.size(); i++)
					{
							//Ver si es Parcial o Fij
							TEmpleado emp = empleadosDep.get(i);
							BOEmpleado empAux = EntMan.find(BOEmpleado.class, emp.getId(), LockModeType.OPTIMISTIC);
							if (empAux.getActivo()) {
								nominaTotal += empAux.calcularNomina();
							}
					}
					if(ok)
					{
						EntTran.commit();
					}
				}
			}
			
			EntMan.close();
			EntManFac.close();
		}
		catch (PersistenceException e){}
		
		return nominaTotal;
	}

}
