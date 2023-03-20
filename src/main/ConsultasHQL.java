package main;

import java.io.DataOutputStream;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import modelo.Departamento;
import util.SessionFactoryUtilEmpresa;

public class ConsultasHQL {
	public static void main(String[] args) {
		SessionFactory sessionFactory = SessionFactoryUtilEmpresa.getSessionFactory();

		Session session = sessionFactory.openSession();
		
		{
			System.out.println("----------- Nombres de los de Departamentos-----------");
			List<String> deptList = session.createQuery(" select d.dname FROM Departamento d ").list();

			for (String nombre : deptList) {
				System.out.println("Nombre: " + nombre);
			}
		}

		{
			System.out.println("----------- Nombre, puesto y salario de todos los empleados -----------");
			List<Object[]> empleados = session.createQuery(" select e.ename, e.job, e.sal FROM Emp e ").list();

			for (Object[] datos : empleados) {
				System.out.println("DATOS -> Nombre: " + datos[0] + " - Puesto: " + datos[1] + " - Salario: " + datos[2]);
			}
		}
		
		{
			System.out.println("----------- La media del salario de todos los empleados -----------");
			Double media = (Double) session.createQuery(" select AVG(e.sal) FROM Emp e ").uniqueResult();

			System.out.println("El salario medio es de " + media);
		}
		
		{
			System.out.println("----------- Los trabajadores que tengan un salario mayor a la media -----------");
			List<String> empleados = session.createQuery(" select e.ename FROM Emp e WHERE sal > (select AVG(e.sal) FROM Emp e)").list();

			for (String nombre : empleados) {
				System.out.println("Nombre: " + nombre);
			}
		}
		
		{
			System.out.println("----------- El departamento con un determinado id con parametros nominales y posicionales -----------");
			int id = 10;
			Departamento departamento = (Departamento)session.createQuery(" select d FROM Departamento d WHERE d.id = :id").setParameter("id", id).uniqueResult();

			System.out.println("El departamento con id " + id + " = " + departamento.toString());
		}
		
		//Parametros nominales y posicionales + lista parametrizada
		
		{
			int idDepartamento=10;
			System.out.println(
					"----------- Los departamentos con un determinado id con parámetros nominales -----------");
			List<Departamento> depts = session.createQuery(" select d from Departamento d where d.deptno =:identificadorDeptno")
					.setParameter("identificadorDeptno", idDepartamento).list();
				
			for (Departamento dept : depts) {
				System.out.println(dept);
			}
			
		}
		
		{
			int idDepartamento=10;
			System.out.println(
					"----------- Los departamentos con un determinado id con 2 parámetros posicionales -----------");
			List<Departamento> depts = session.createQuery(" select d from Departamento d where deptno =?0 and d.dname=?1")
					.setParameter(0, idDepartamento).setParameter(1, "ACCOUNTING").list();
			
			for (Departamento dept : depts) {
				System.out.println(dept);
			}
			
		}
		
		{
			System.out.println("----------- Numero de empleados por departamento -----------");
			List<Object[]> datos = session.createQuery(" select "
					+ "e.departamento.deptno, count(e.empno) FROM Emp e WHERE e.departamento.deptno IS NOT NULL group by e.departamento.deptno").list();

			for (Object[] emp : datos) {
				System.out.println("Departamento = " + emp[0] + " -> Num de Empleados = " + emp[1]);
			}
		}
		

		session.close();
		sessionFactory.close();
	}

}
