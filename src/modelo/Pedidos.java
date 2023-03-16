package modelo;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table
public class Pedidos {
	
	private int id;
	private Date fecha; //Ver si hay que importa data.sql en vez de util
	private String formaPago;
	private Clientes cliente;
	
	public Pedidos(Date fecha, String formaPago) {
		this.fecha = fecha;
		this.formaPago = formaPago;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Column
	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	@ManyToOne
	@JoinColumn
	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", fecha=" + fecha + ", formaPago=" + formaPago + "]";
	}
	

}
