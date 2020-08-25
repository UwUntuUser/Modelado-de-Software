package Negocio.Empleado;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import Negocio.Departamento.BODepartamento;

public class SAEmpleadoIMP implements SAEmpleado {

	@Override
	public int altaEmpleado(TEmpleado te) {
		
		int id = -1;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emeseii");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		String dni = te.getDNI();
		BOEmpleado BOE = null;

		Query q = em.createNamedQuery("BOEmpleado.findByDNI").setParameter("dni", dni);
		
		if (q.getResultList().isEmpty()) { // si no existe 
			BODepartamento d = em.find(BODepartamento.class, te.getIdDep(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
			
			if (d != null && d.getActivo()) {
				if (te.isEsParcial()) {
					TEmpleadoParcial tep = (TEmpleadoParcial) te;
					BOE = new BOEmpleadoParcial(d, tep);
				}
				else {
					TEmpleadoFijo tef = (TEmpleadoFijo) te;
					BOE = new BOEmpleadoCompleto(d, tef);
				}
				d.aniadirEmpleadoDepartamento(BOE);
				em.persist(BOE);
				et.commit();
				id = BOE.getId();
			}
			else {
				et.rollback();
			}
		}
		else {
			BOE = (BOEmpleado) q.getSingleResult();
			if (te.getDNI() != null) {
				BOE.setDni(te.getDNI());
			}
			if (te.getNombre() != null) {
				BOE.setNombre(te.getNombre());
			}
			if (te.getApellido() != null) {
				BOE.setApellido(te.getApellido());
			}
			if (te.getNumSS() != null) {
				BOE.setNumSS(te.getNumSS());
			}
			BOE.setActivo(true);
			if (te.isEsParcial()) {
				try {
					BOEmpleadoParcial btp = (BOEmpleadoParcial) BOE;
					TEmpleadoParcial tp = (TEmpleadoParcial) te;
					btp.setHorasJornada(tp.getHorasJOrnada());
					btp.setSueldoHora(tp.getSueldoHora());
				}
				catch (Exception e) {
					et.rollback();
					em.close();
					emf.close();
					return -1;
				}
			}
			else {
				try {
					BOEmpleadoCompleto btp = (BOEmpleadoCompleto) BOE;
					TEmpleadoFijo tp = (TEmpleadoFijo) te;
					btp.setAntiguedad(tp.getAntiguedad());
					btp.setSueldoMensual(tp.getSueldoMensual());
				}
				catch (Exception e) {
					et.rollback();
					em.close();
					emf.close();
					return -1;
				}
			}
			et.commit();
			id = BOE.getId();
		}
		em.close();
		emf.close();
		return id;
	}

	@Override
	public boolean bajaEmpleado(Integer id) {
		boolean ok = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emeseii");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		BOEmpleado BOE = em.find(BOEmpleado.class, id);
		if (BOE != null && BOE.getActivo()) { // si existe y esta activo
			BOE.setActivo(false);
			et.commit();
			ok = true;
		} else {
			et.rollback();
		}
		em.close();
		emf.close();
		return ok;
	}

	@Override
	public boolean actualizarEmpleado(TEmpleado empleado) {
		boolean ok = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emeseii");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		BOEmpleado BOE = em.find(BOEmpleado.class, empleado.getId(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (BOE != null && BOE.getActivo()) {
			if (empleado.getDNI() != null) {
				BOE.setDni(empleado.getDNI());
			}
			if (empleado.getNombre() != null) {
				BOE.setNombre(empleado.getNombre());
			}
			if (empleado.getApellido() != null) {
				BOE.setApellido(empleado.getApellido());
			}
			if (empleado.getNumSS() != null) {
				BOE.setNumSS(empleado.getNumSS());
			}
			if (empleado.isEsParcial()) {
				try {
					BOEmpleadoParcial btp = (BOEmpleadoParcial) BOE;
					TEmpleadoParcial tp = (TEmpleadoParcial) empleado;
					btp.setHorasJornada(tp.getHorasJOrnada());
					btp.setSueldoHora(tp.getSueldoHora());
				}
				catch (Exception e) {
					et.rollback();
					em.close();
					emf.close();
					return false;
				}
			}
			else {
				try {
					BOEmpleadoCompleto btp = (BOEmpleadoCompleto) BOE;
					TEmpleadoFijo tp = (TEmpleadoFijo) empleado;
					btp.setAntiguedad(tp.getAntiguedad());
					btp.setSueldoMensual(tp.getSueldoMensual());
				}
				catch (Exception e) {
					et.rollback();
					em.close();
					emf.close();
					return false;
				}
			}
			et.commit();
			ok = true;
		}
		else {
			et.rollback();
			ok = false;
		}
		em.close();
		emf.close();
		return ok;
	}

	@Override
	public TEmpleado mostrarEmpleado(Integer id) {
		TEmpleado temp = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emeseii");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		BOEmpleado BOE = em.find(BOEmpleado.class, id, LockModeType.OPTIMISTIC);
		if (BOE == null || !BOE.getActivo())
			et.rollback();
		else {
			if (BOE.getEs_parcial()) {
				BOEmpleadoParcial BOEP = (BOEmpleadoParcial) BOE;
				TEmpleadoParcial tParcial = new TEmpleadoParcial(BOEP.getDni(), BOEP.getNombre(), BOEP.getApellido(),
					BOEP.getNumSS(), true, BOEP.getHorasJornada(), BOEP.getSueldoHora());
				tParcial.setId(id);
				temp = (TEmpleado) tParcial; // todo empleado parcial es un
												// empelado
			} else {
				BOEmpleadoCompleto c = (BOEmpleadoCompleto) BOE;
				TEmpleadoFijo fijo = new TEmpleadoFijo(c.getDni(), c.getNombre(), c.getApellido(), c.getNumSS(),
						 c.getAntiguedad(), c.getSueldoMensual(), true);
				fijo.setId(id);
				temp = (TEmpleado) fijo;
			}
		}
		em.close();
		emf.close();
		return temp;
	}

	@Override
	public ArrayList<TEmpleado> mostrarEmpleados() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emeseii");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query query = em.createNamedQuery("BOEmpleado.findByActivo").setParameter("activo", true);
		Collection<BOEmpleado> BOempleados = query.getResultList();
		ArrayList<TEmpleado> tEmpleados = new ArrayList<TEmpleado>();
		if(!query.getResultList().isEmpty()){
			for(BOEmpleado e : BOempleados){
				TEmpleado t = new TEmpleado();
				if (e.getEs_parcial()) {
					BOEmpleadoParcial BOEP = (BOEmpleadoParcial) e;
					TEmpleadoParcial tParcial = new TEmpleadoParcial(BOEP.getDni(), BOEP.getNombre(), BOEP.getApellido(),
						BOEP.getNumSS(), true, BOEP.getHorasJornada(), BOEP.getSueldoHora());
					tParcial.setId(e.getId());
					t = (TEmpleado) tParcial;
				} else {
					BOEmpleadoCompleto c = (BOEmpleadoCompleto) e;
					TEmpleadoFijo fijo = new TEmpleadoFijo(c.getDni(), c.getNombre(), c.getApellido(), c.getNumSS(),
							 c.getAntiguedad(), c.getSueldoMensual(), true);
					fijo.setId(e.getId());
					t = (TEmpleado) fijo;
				}
				tEmpleados.add(t);
			}
			et.commit();
		}
		else{
			et.rollback();
		}
		em.close();
		emf.close();
		return tEmpleados;
	}


}
