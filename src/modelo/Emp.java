package modelo;
// Generated 21:11:36, 10 de xan. de 2023 by Hibernate Tools 5.6.14.Final

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Emp generated by hbm2java
 */
public class Emp implements java.io.Serializable {

	private Integer empno;
	private Departamento departamento;
	private Emp emp;
	private String ename;
	private String job;
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
	private Set accounts = new HashSet(0);
	private Set emps = new HashSet(0);

	public Emp() {
	}

	public Emp(Departamento departamento, Emp emp, String ename, String job, Date hiredate, BigDecimal sal, BigDecimal comm,
			Set accounts, Set emps) {
		this.departamento = departamento;
		this.emp = emp;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.accounts = accounts;
		this.emps = emps;
	}

	public Integer getEmpno() {
		return this.empno;
	}

	public void setEmpno(Integer empno) {
		this.empno = empno;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Emp getEmp() {
		return this.emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return this.hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public BigDecimal getSal() {
		return this.sal;
	}

	public void setSal(BigDecimal sal) {
		this.sal = sal;
	}

	public BigDecimal getComm() {
		return this.comm;
	}

	public void setComm(BigDecimal comm) {
		this.comm = comm;
	}

	public Set getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set accounts) {
		this.accounts = accounts;
	}

	public Set getEmps() {
		return this.emps;
	}

	public void setEmps(Set emps) {
		this.emps = emps;
	}

}
