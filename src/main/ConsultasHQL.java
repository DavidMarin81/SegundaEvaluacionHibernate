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
		
		{
			System.out.println("----------- Nombres de los departamentos sin empleados, ordenados por nombre -----------");
			List<String> nombres = session.createQuery(" select d.dname FROM Departamento d WHERE size(d.emps) = 0 ORDER BY d.dname").list();

			for (String departamento : nombres) {
				System.out.println("Departamento = " + departamento);
			}
		}
		
		{
			System.out.println("----------- Nombres de los departamentos y de los empleados que tienen al menos 2 empleados. Se debe ordenar por nombre del departamento -----------");
			List<Object[]> nombres =session.createQuery(" select d.dname, e.ename FROM Departamento d join d.emps e WHERE size(d.emps) > 2 ORDER BY d.dname").list();

			for (Object[] noms : nombres) {
				System.out.println("Departamento = " + noms[0] + " -> Empleado: " + noms[1]);
			}
		}
		
		{
			System.out.println("----------- Q3: Los ids de los empleados y el nº de cuentas por empleado -----------");
			List<Object[]> deptList = session
					.createQuery("  select e.empno,  count(a) FROM Emp e left join e.accounts a group by e.empno").list();
					//.createQuery("  select e.empno,  size(e.accounts) FROM Emp e").list();
			for (Object[] filas : deptList) {
				System.out.println("Id emp: " + filas[0] + " Nº de cuentas: " + filas[1]);
			}
		}

		{
			System.out.println("----------- Q4: Los ids de los empleados y el saldo de sus cuentas -----------");
			List<Object[]> deptList = session
					.createQuery("  select e.empno,  sum(a.amount) FROM Emp e join e.accounts a group by e.empno")
					.list();

			for (Object[] filas : deptList) {
				System.out.println("Id emp: " + filas[0] + " Saldo cuenta(s): " + filas[1]);
			}
		}
		
		{
			System.out.println("----------- Q5: El identificador de cada cuenta con el identificador del movimiento donde la cuenta es la cuenta origen -----------");
			List<Object[]> cuentasIds = session
					.createQuery("  select c.accountOrigen.accountno,"
							+ " c.accountMovId from AccMovement c")
					.list();

			for (Object[] filas : cuentasIds) {
				System.out.println("Id account: " + filas[0] + " Id mov: " + filas[1]);
			}
		}
		
		{
			System.out.println("----------- Q6: El nº de movimientos por cada cuenta origen -----------");
			List<Object[]> datos = session
					.createQuery("select c.accountno, size(c.accMovementsOrigen) from Account c")
					// Otra posibilidad: .createQuery("select c.accountno, count(o) from "
					//+" Account c left join c.accMovementsOrigen o group by c.accountno")
					
					//La siguiente consulta no se corresponde con el enunciado porque las
					//cuentas que no tienen movimientos no aparecen: 
					//.createQuery("select c.accountOrigen.accountno, count(c.accountMovId) "
//							+ "from AccMovement c group by c.accountOrigen.accountno")
				
					.list();

			for (Object[] filas : datos) {
				System.out.println("Id account: " + filas[0] + " Nº.mov: " + filas[1]);
			}
		}
		
		{
			System.out.println("----------- Q7. El nombre de cada empleado con el de su jefe. Ha de aparecer el nombre del empleado aunque no tenga jefe\r\n"
					+ "		 -----------");
			List<Object[]> datos = session
					.createQuery("select e.ename, j.ename from Emp e left join e.emp j ")
					
					.list();

			for (Object[] filas : datos) {
				System.out.println("Emp name: " + filas[0] + " Jefe name: " + filas[1]);
			}

		}
		
		session.close();
		sessionFactory.close();
	}

}
