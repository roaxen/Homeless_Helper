package model;

import java.io.Serializable;
import java.util.Arrays;

import jakarta.persistence.*;

/**
 * The persistent class for the aboutus database table.
 * 
 */
@Entity
@NamedQuery(name = "Aboutus.findAll", query = "SELECT a FROM Aboutus a")
public class Aboutus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String apellido1;

	private String apellido2;

	@Lob
	private String descripcion;

	@Lob
	private byte[] imagen;

	private String nombre;

	public Aboutus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Aboutus [id=" + id + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", descripcion="
				+ descripcion + ", imagen=" + Arrays.toString(imagen) + ", nombre=" + nombre + "]";
	}
}