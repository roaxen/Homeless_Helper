package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the tipoempleo database table.
 * 
 */
@Entity
@NamedQuery(name="Tipoempleo.findAll", query="SELECT t FROM Tipoempleo t")
public class Tipoempleo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	public Tipoempleo() {
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
	
	@Override
	public String toString() {
		return "Tipoempleo [id=" + id + ", descripcion=" + descripcion + "]";
	}
}