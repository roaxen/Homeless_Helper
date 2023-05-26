package model;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;


/**
 * The persistent class for the empleos database table.
 * 
 */
@Entity
@Table(name="empleos")
@NamedQuery(name="Empleo.findAll", query="SELECT e FROM Empleo e")
public class Empleo implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@Id
	private int id;

	@Lob
	private String descripcion;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Lob
	private byte[] imagen;

	private String localidad;

	private String precio;

	private String titulo;

	@Column(name="tipotrabajo")
	private int tipoempleo;

	public Empleo() {
	}

	public int getId() {
		return this.id;
	}	

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPrecio() {
		return this.precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getTipoempleo() {
		return this.tipoempleo;
	}

	public void setTipoempleo(int tipoempleo) {
		this.tipoempleo = tipoempleo;
	}

	@Override
	public String toString() {
		return "Empleo [id=" + id + ", descripcion=" + descripcion + ", email=" + email + ", fecha=" + fecha
				+ ", imagen=" + Arrays.toString(imagen) + ", localidad=" + localidad + ", precio=" + precio
				+ ", titulo=" + titulo + ", tipoempleo=" + tipoempleo + "]";
	}
}