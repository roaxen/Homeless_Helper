package model;

import java.io.Serializable;
import jakarta.persistence.*;

/**
 * The persistent class for the tipolugar database table.
 * 
 */
@Entity
@NamedQuery(name = "Tipolugar.findAll", query = "SELECT t FROM Tipolugar t")
public class Tipolugar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipolugar")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTipolugar;

	private String descripcion;
	public Tipolugar() {
	}

	public int getIdTipolugar() {
		return this.idTipolugar;
	}

	public void setIdTipolugar(int idTipolugar) {
		this.idTipolugar = idTipolugar;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Tipolugar [idTipolugar=" + idTipolugar + ", descripcion=" + descripcion + "]";
	}
}