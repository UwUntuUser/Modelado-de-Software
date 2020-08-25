package Negocio.Competicion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.*;

import Negocio.Empleado.BOEmpleado;
import Negocio.Empleado.BOEmpleadoCompeticion;
import Negocio.Empleado.BOEmpleadoCompeticionId;
import Negocio.Empleado.TEmpleado;

public class SACompeticionIMP implements SACompeticion {

	@Override
	public Integer CrearCompeticion(TCompeticion competicion) {
		int id = -1;
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();

		EntTran.begin();
		String nombre = competicion.getNombre();

		Query query = EntMan.createNamedQuery("Negocio.Competicion.findByName").setParameter("nombre", competicion.getNombre());
		
		// REACTIVACION
		
		if (!query.getResultList().isEmpty()) { 
			BOCompeticion comp = (BOCompeticion) query.getSingleResult();
			if (!comp.isActivo()) {
				comp.setActivo(true);
				EntTran.commit();
				id = comp.getId();
			} else
				EntTran.rollback();
		} 
		
		// CREACION
		
		else {
			BOCompeticion comp = new BOCompeticion();
			comp.setNombre(nombre);
			comp.setActivo(true);
			EntMan.persist(comp);
			EntTran.commit();
			id = comp.getId();
		}
		EntMan.close();
		EntManFac.close();
		return id;
	}

	@Override
	public boolean eliminarCompeticion(Integer id) {
		boolean ok = false;
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();
		EntTran.begin();
		BOCompeticion comp = EntMan.find(BOCompeticion.class, id);
		if (comp != null && comp.isActivo() && !comp.existenEmpleados()) {
				comp.setActivo(false);
				ok = true;
				EntTran.commit();
		}
		else{
			EntTran.rollback();
		}
		EntMan.close();
		EntManFac.close();
		return ok;
	}

	@Override
	public boolean actualizarCompeticion(TCompeticion competicion) {
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();

		boolean mod = false;

		EntTran.begin();
		BOCompeticion comp = EntMan.find(BOCompeticion.class, competicion.getIdCompeticion(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (comp != null && comp.isActivo()) {
			Query query = EntMan.createNamedQuery("Negocio.Competicion.encontrarPorId").setParameter("id", competicion.getIdCompeticion());
			if (!query.getResultList().isEmpty()) {
				comp.setNombre(competicion.getNombre());
				EntTran.commit();
				mod = true;
			} else {
				EntTran.rollback();
			}
		} else {
			EntTran.rollback();
		}
		EntMan.close();
		EntManFac.close();
		return mod;
	}

	@Override
	public TCompeticion leerCompeticion(Integer id) {
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();

		EntTran.begin();
		BOCompeticion BOcomp = null;
		TCompeticion Tcomp = null;
		BOcomp = EntMan.find(BOCompeticion.class, id, LockModeType.OPTIMISTIC);
		if (BOcomp != null && BOcomp.isActivo()) {
			Tcomp = new TCompeticion();
			Tcomp.setNombre(BOcomp.getNombre());
			Tcomp.setIdCompeticion(BOcomp.getId());
			Tcomp.setActivo(BOcomp.isActivo());

			ArrayList<TEmpleado> listEmpleados = new ArrayList<>();
			for (BOEmpleado emp : BOcomp.getEmpleados()) {
				if (emp.getActivo()) {
					TEmpleado aux = new TEmpleado(emp.getNombre(), emp.getApellido(), emp.getDni(), emp.getId(),
							emp.getNumSS(), emp.getActivo());
					listEmpleados.add(aux);
				}
			}
			Tcomp.setEmpleados(listEmpleados);
			EntTran.commit();
		} else
			EntTran.rollback();

		EntMan.close();
		EntManFac.close();

		return Tcomp;
	}

	@Override
	public ArrayList<TCompeticion> leerTodosCompeticion() {
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();

		EntTran.begin();
		Query query = EntMan.createNamedQuery("Negocio.Competicion.findByActivo").setParameter("activo", true);

		Collection<BOCompeticion> BOCompeticiones = query.getResultList();
		ArrayList<TCompeticion> tCompeticionses = new ArrayList<TCompeticion>();

		if (!query.getResultList().isEmpty()) {
			for (BOCompeticion com : BOCompeticiones) {
				if (com.isActivo()) {
					TCompeticion competicion = new TCompeticion();
					competicion.setActivo(true);
					competicion.setIdCompeticion(com.getId());
					competicion.setNombre(com.getNombre());
					ArrayList<TEmpleado> listEmpleados = new ArrayList<>();
					for (BOEmpleado emp : com.getEmpleados()) {
						if (emp.getActivo()) {
							TEmpleado aux = new TEmpleado(emp.getNombre(), emp.getApellido(), emp.getDni(), emp.getId(),
									emp.getNumSS(), emp.getActivo());
							listEmpleados.add(aux);
						}
					}
					competicion.setEmpleados(listEmpleados);
					tCompeticionses.add(competicion);
				}
			}
			EntTran.commit();
		} else
			EntTran.rollback();

		EntMan.close();
		EntManFac.close();
		return tCompeticionses;
	}

	@Override
	public boolean AniairEmpleadoCompeticion(int compid, int emp) {
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();

		boolean ok = false;
		EntTran.begin();
		
		BOCompeticion BOC = EntMan.find(BOCompeticion.class, compid, LockModeType.OPTIMISTIC);
		BOEmpleado BOE = EntMan.find(BOEmpleado.class, emp, LockModeType.OPTIMISTIC);
		
		BOEmpleadoCompeticionId id = new BOEmpleadoCompeticionId(BOE.getId(),BOC.getId());
		BOEmpleadoCompeticion ec = EntMan.find(BOEmpleadoCompeticion.class, id);
		if (BOC == null || BOE == null || !BOE.getActivo() || !BOC.isActivo()) {
			EntTran.rollback();
		} 
		else 
		{
			if(ec == null) // no existe la relacion entre empleado y competicion
			{
				BOC.aniadirEmpleados(BOE);
				BOEmpleadoCompeticion nueva = new BOEmpleadoCompeticion(id,0.2*BOE.calcularNomina());
				nueva.setEmpleado(BOE);
				nueva.setCompeticion(BOC);
				nueva.setActivo(true);
				BOE.aniadirCompeticion(nueva);
				BOC.aniadirEmpleado(nueva);
				
				EntMan.persist(nueva);
				EntTran.commit();
				ok=true;
			}
			else
			{
				ec.setActivo(true);
				ec.setPremio(0.2*BOE.calcularNomina());
				BOC.aniadirEmpleados(BOE);
				EntTran.commit();
				ok=true;
			}
		}

		EntMan.close();
		EntManFac.close();
		return ok;
	}

	@Override
	public boolean EliminarEmpleadoCompeticion(int comp, int emp) {
		EntityManagerFactory EntManFac = Persistence.createEntityManagerFactory("emeseii");
		EntityManager EntMan = EntManFac.createEntityManager();
		EntityTransaction EntTran = EntMan.getTransaction();
		EntTran.begin();

		boolean ok = false;
		
		BOEmpleadoCompeticionId id = new BOEmpleadoCompeticionId(emp,comp);
		BOEmpleadoCompeticion ec = EntMan.find(BOEmpleadoCompeticion.class, id, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		
		if(ec!= null && ec.getActivo())
		{
			BOCompeticion BOC = EntMan.find(BOCompeticion.class, comp);
			BOC.eliminarEmpleado(emp);
			ec.setActivo(false);
			EntTran.commit();
			ok=true;
		}
		else
			EntTran.rollback();

		EntMan.close();
		EntManFac.close();
		return ok;
	}

}
