package modelo;

import javax.persistence.*;

@Entity
@Table
public class Detalles_Clientes {
	
	private int id;
	private String web, tfno, comentarios;
	
	public Detalles_Clientes() {
	}

	public Detalles_Clientes(String web, String tfno, String comentarios) {
		super();
		this.web = web;
		this.tfno = tfno;
		this.comentarios = comentarios;
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
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
	@Column
	public String getTfno() {
		return tfno;
	}

	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	@Column
	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Detalles_Clientes [id=" + id + ", web=" + web + ", tfno=" + tfno + ", comentarios=" + comentarios + "]";
	}
	
	
	
	

}
