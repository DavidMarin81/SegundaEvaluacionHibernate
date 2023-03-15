package main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Clientes; 
import util.SessionFactoryUtil;

public class Main {

	public static void main(String[] args) {
		comprobarConexion();
		createObjeto();
		mostrarObjetos();
		modificarClienteConLoad(53170229);
		mostrarObjetos();
		borrarDepartamento(53170229);
		mostrarObjetos();
		
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
			Clientes cliente = new Clientes();
			cliente.setId(53170229);
			cliente.setNombre("David");
			cliente.setApellidos("Marin");
			cliente.setDireccion("Teixugueiras");
			//Guardar la sesion con "sesion.save(objeto)"
			sesion.save(cliente);
			//Sesion.save devuelve el id del objeto en la BBDD
			//Integer depId = (Integer)sesion.save(cliente);
			
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
			Clientes cliente = sesion.get(Clientes.class, id);
			//sesion.get devuelve nulo si no encuentra nada
			if (cliente == null) {
				System.out.println("No existe id: " + id);
			} else {
				System.out.println("Name: " + cliente.getNombre());
				System.out.println("Deptno: " + cliente.getApellidos());
				System.out.println("Location: " + cliente.getDireccion());
			}
			
		}catch(Exception ex) {
			
		}
	}

	//Modificar un objeto (con LOAD -> lanza una excepcion)
	public static void modificarClienteConLoad(int id) {
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		Transaction tx = null;
		
		try (Session sesion = factoria.openSession();) {
			tx = sesion.beginTransaction();
			Clientes cliente = sesion.load(Clientes.class, id);
			cliente.setDireccion("Nueva direccion");

			sesion.saveOrUpdate(cliente);
			tx.commit();
		} catch (Exception ex) {
			System.err.println("Ha habido una exception " + ex);
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		}
	}
	
	//Modificar un objeto (con GET -> devuelve null)
	public static void modificarClienteConGet(int id) {
			SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
			Transaction tx = null;
			
			try (Session sesion = factoria.openSession();) {
				tx = sesion.beginTransaction();
				Clientes cliente = sesion.get(Clientes.class, id);
				
				if(cliente != null) {
					cliente.setApellidos("Nuevo apellido");
					sesion.saveOrUpdate(cliente);
					tx.commit();
				} else {
					System.out.println("No se ha encontrado ningún departamento");
				}

			} catch (Exception ex) {
				System.err.println("Ha habido una exception " + ex);
				if (tx != null) {
					tx.rollback();
				}
				throw ex;
			}
		}
	
	//Borrar un objeto
	public static void borrarDepartamento(int id) {
		Transaction tx = null;
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		
		try (Session sesion = factoria.openSession();){
			tx = sesion.beginTransaction();
			Clientes cliente = sesion.load(Clientes.class, id);
			sesion.delete(cliente);
			
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
	public static void mostrarObjetos() {
		Transaction tx = null;
		SessionFactory factoria = SessionFactoryUtil.getSessionFactory();
		
		try (Session sesion = factoria.openSession();){
			tx = sesion.beginTransaction();
		
			@SuppressWarnings("unchecked")
			List<Clientes> clientes = sesion.createQuery("select c from Clientes c").list();
			
			for (Clientes c : clientes) {
				System.out.println(c);
			}
			
			tx.commit();
		
		} catch (Exception ex) {
			System.err.println("Ha habido una exception " + ex);
			if (tx != null) {
				tx.rollback();
			}
			throw ex;
		}
	}
}
