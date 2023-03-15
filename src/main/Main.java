package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

//import modelo.Departamento;
//import modelo.Dept;
//import modelo.Empleados;
import util.SessionFactoryUtil;

public class Main {

	public static void main(String[] args) {
		comprobarConexion();
	}
	
	//Método para comprobar la conexion con la BBDD
	public static void comprobarConexion() {
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		try (Session sesion = factoria.openSession();){
			System.out.println("Se ha establecido la conexion");
			
		}catch(Exception ex) {
			System.out.println("Se ha producido una excepcion");
		}
	}
	
	//Crear objeto
	public static void createObjeto() {
		Transaction tx = null;
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		
		try (Session sesion = factoria.openSession();){
			tx = sesion.beginTransaction();
			
			//Crear el objeto y setearle los datos
			
			//Guardar la sesion con "sesion.save(objeto)"
			/*Integer deptId = (Integer)sesion.save(dept);
				o 
			  sesion.save(dept);*/
			
			tx.commit();
			
		} catch(Exception ex) {
			System.err.println("Ha habido una exception " + ex);
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		}
		
	}

	//Buscar un objeto
	public static void findObjeto(int id) {
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		try(Session sesion = factoria.openSession();){
			//Dept dept = sesion.get(Dept.class, id);
			
			/*if (dept == null) {
				System.out.println("No existe id: " + id);
			} else {
				System.out.println("Name: " + dept.getDname());
				System.out.println("Deptno: " + dept.getDeptno());
				System.out.println("Location: " + dept.getLoc());
			}*/
			
		}catch(Exception ex) {
			
		}
	}

	//Modificar un objeto
	public static void updateDepartamento(int id) {

		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();

		Transaction tx = null;
		try (Session sesion = factoria.openSession();) {

			tx = sesion.beginTransaction();
			//Departamento dept = sesion.load(Departamento.class, id);

			/*dept.setDname("Recursos Humanos 2");
			dept.setLoc("Oviedo");*/

			//sesion.saveOrUpdate(dept);

			tx.commit();
		} catch (Exception ex) {
			System.err.println("Ha habido una exception " + ex);
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		}

	}
	
	//Borrar un objeto
	public static void deleteDepartamento(int id) {
		Transaction tx = null;
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		
		try (Session sesion = factoria.openSession();){
			tx = sesion.beginTransaction();
			//Dept dept = sesion.load(Dept.class, id);
			
			//sesion.delete(dept);
			tx.commit();
	
		} catch (Exception ex) {
			System.err.println("Ha habido una exception " + ex);
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		}
	}

	//Mostrar objetos
	/*public List<Empleados> mostrarEmpleados() {
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		Session sesion = factoria.openSession();
		
		@SuppressWarnings("unchecked")
		List<Empleados> empleados = sesion.createQuery("select d from Empleados d").list();
		
		for (Empleados emps : empleados) {
			System.out.println(emps);
		}
		
		sesion.close();
		
		return empleados;
	}*/
}
