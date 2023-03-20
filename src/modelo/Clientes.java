package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
//@Table
public class Clientes {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String direccion;
	private Detalles_Clientes detalles_clientes;
	private List<Pedidos> pedidos;
	
	public Clientes() {
		
	}
	
	public Clientes(String nombre, String apellidos, String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
	}
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	//@Column
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	//@Column
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	//Se quita la cascada para que se borren los detalles pero se mantenga el cliente
	@OneToOne(mappedBy="clientes")
	@JoinColumn
	public Detalles_Clientes getDetalles_clientes() {
		return detalles_clientes;
	}

	public void setDetalles_clientes(Detalles_Clientes detalles_clientes) {
		this.detalles_clientes = detalles_clientes;
	}
	
	
	@OneToMany(mappedBy="cliente")
	public List<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}

	//Método para recoger los pedidos
	public void agregarPedidos(Pedidos pedido) {
		if(pedidos == null) {
			pedidos = new ArrayList<>();
			pedidos.add(pedido);
			//this hace referencia al cliente donde nos encontramos
			pedido.setCliente(this);
		}
	}

	@Override
	public String toString() {
		return "Clientes [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ "]";
	}
	
	
	
	
}
